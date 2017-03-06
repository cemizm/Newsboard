const path = require('path');
const spawn = require('child_process').spawn;

var mkdirp = require('mkdirp');

var winston = require('winston');
require('winston-daily-rotate-file');

var config = require('./config.json');

var mainLogger = getLogger(config.loglevel, "main");

mkdirp.sync(config.logdir);

function getLogger(level, filename) {
    return new (winston.Logger)({
        level: level,
        transports: [
            new (winston.transports.Console)(),
            new (winston.transports.DailyRotateFile)({
                filename: path.join(config.logdir, filename) + ".",
                datePattern: 'yyyy-MM-dd.log',
            })
        ]
    });
}

function timerFactory(service) {
    mainLogger.log("info", service.name + ": creating service");

    var level = service.loglevel;
    if (!level)
        level = config.loglevel;

    var logger = getLogger(level, service.name);

    var options = {};
    if (service.options) {
        if (service.options.path)
            options.cwd = service.options.path;

        if (service.options.envvars)
            options.env = service.options.envvars;

        if (service.options.shell)
            options.shell = service.options.shell;
    }

    var handler = function () {
        mainLogger.log("info", service.name + ": executing service");

        var func = spawn(service.command, service.args, options);

        mainLogger.log("info", service.name + ": running with pid: " + func.pid);

        func.stdout.on('data', function (data) {
            logger.log("info", data.toString());
        });

        func.stderr.on('data', function (data) {
            logger.log("error", data.toString());
        });

        func.on('error', function (message) {
            mainLogger.log("error", service.name + ": " + message, code);
        });

        func.on('exit', function (code) {
            mainLogger.log("info", service.name + ": service exited with code: %d", code);

            if (service.periodic)
                setTimeout(handler, 1000 * service.interval);
        });
    };

    return handler;
}

mainLogger.log('info', "Initializing Crawler/Analyzer Host");

var serviceHandlers = [];

config.services.forEach(function (service) {
    serviceHandlers.push(timerFactory(service));
});

serviceHandlers.forEach(function (serviceHandler) {
    serviceHandler();
});

