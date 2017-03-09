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
            client.publish(entry,
                function (data, response) {
                    if (response.statusCode != 200) return console.log("Crawler Error (" + response.statusCode + "): " + data.message);

                    console.log("Crawler published: " + entry.title)
                });
        });
    });
});
