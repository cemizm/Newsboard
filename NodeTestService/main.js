var RSSReader = require('./RSSReader/index.js');
var CrawlerCient = require('./CrawlerClient/index.js');
var AnalyzerClient = require('./AnalyzerClient/index.js');
var md5 = require('md5');

var sources = [
    {
        url: "https://www.nasa.gov/rss/dyn/breaking_news.rss",
        getEntry: function (item) {
            return {
                id: md5(item.guid),
                date: item.pubdate,
                content: item.summary,
                image: item.enclosures[0].url,
                title: item.title,
                source: "Nasa Breaking News",
                url: item.link
            }
        }
    },
    {
        url: "http://feeds.n24.de/n24/wirtschaft_boerse",
        getEntry: function (item) {
            const regex = /<.*?>/g;

            return {
                id: md5(item.link),
                date: item.pubdate,
                content: item.summary.replace(regex, " "),
                image: item.image.url,
                title: item.title,
                source: "N24 Wirtschaft und Börse",
                url: item.link
            }
        }
    },
    {
        url: "http://www.mt.de/_export/mt/rss/minden/index.rss",
        getEntry: function (item) {
            return {
                id: md5(item.link),
                date: item.pubdate,
                content: item.summary,
                image: item.enclosures[0].url,
                title: item.title,
                source: "Mindener Tageblatt",
                url: item.link
            }
        }
    },
    {
        url: "http://www.mt.de/_export/mt/rss/regionales/index.rss",
        getEntry: function (item) {
            return {
                id: md5(item.link),
                date: item.pubdate,
                content: item.summary,
                image: item.enclosures[0].url,
                title: item.title,
                source: "Mindener Tageblatt - Regionales",
                url: item.link
            }
        }
    }
];

var analyzers = [
    {
        token: "56589lt5app3bmvoggxt46ajor6z5vfgpnmpk50wbdnuexywrk9"
    },
    {
        token: "da39a3ee5e6b4b0d5255bfef95601890afd80709"
    },
];

console.log("Publishing News Entries:");
sources.forEach(function (source) {
    var reader = new RSSReader();
    reader.read(source, function (item) {
        const regex = /&#...;/g;

        var entry = source.getEntry(item);

        entry.content = entry.content.replace(regex, "");
        entry.title = entry.title.replace(regex, "");

        CrawlerCient.publish('da39a3ee5e6b4b0d5255bfef95601890afd80709',
            entry,
            function (data, response) {
                if (response.statusCode != 200) return console.log("Error: " + response.statusCode);

                console.log("published: " + item.title)
            });
    });
});

console.log("Publishing Analyzer Results:");
analyzers.forEach(function (analyzer) {
    AnalyzerClient.getNewsEntries(analyzer.token, function (items) {
        items.forEach(function (entry) {

            var result = {
                "value": getRandomInt(-100, 100),
                "date": (new Date()).toJSON(),
                "sentenceResults": []
            };

            var start = 0;
            var sentences = entry.content.split(".");
            sentences.forEach(function (sentence) {
                result.sentenceResults.push(
                    {
                        "charStart": start,
                        "charEnd": start + sentence.length,
                        "value": getRandomInt(-100, 100)
                    });
                start += sentence.length + 1;
            });
            AnalyzerClient.publish(analyzer.token, entry.id, result, function (data, response) {
                if (response.statusCode != 200) return console.log("Error: " + response.statusCode);

                console.log("published: " + result.newsId);
            });
        });
    });
});

function getRandomInt(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min)) + min;
}