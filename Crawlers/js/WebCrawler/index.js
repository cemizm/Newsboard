var RSSReader = require('./RSSReader');
var CrawlerClient = require('../CrawlerClient');
var MTParser = require('./MindenerTageblatt');

var config = require('./config.json');

var sources = [
    new MTParser("http://www.mt.de/_export/mt/rss/minden/index.rss"),
    new MTParser("http://www.mt.de/_export/mt/rss/regionales/index.rss"),
];

var client = new CrawlerClient(config.host, config.token);

sources.forEach(function (source) {
    var reader = new RSSReader();

    reader.read(source.url, function (item) {

        source.parse(item, function(entry){
            var filter = true;

            config.keywords.forEach(function(keyword){
                if(entry.title.indexOf(keyword) >= 0 ||
                   entry.content.indexOf(keyword) >= 0)
                {
                    filter = false;
                }
            });

            //if(filter)
            //    return;

            client.publish(entry,
                function (error, response) {
                    if (error) return console.error("Crawler Error (" + error.code + "): " + error.message);

                    console.log("published: " + entry.title)
                });
        });

    });

});
