var FeedParser = require('feedparser')
    , request = require('request');

var md5 = require('md5');

var Client = require('node-rest-client').Client;

var client = new Client();

var req = request('https://www.nasa.gov/rss/dyn/breaking_news.rss')
    , feedparser = new FeedParser();

req.on('error', function (error) {
    // handle any request errors
});
req.on('response', function (res) {
    var stream = this;

    if (res.statusCode != 200) return this.emit('error', new Error('Bad status code'));

    stream.pipe(feedparser);
});


feedparser.on('error', function (error) {
    // always handle errors
});
feedparser.on('readable', function () {
    // This is where the action is!
    var stream = this
        , meta = this.meta // **NOTE** the "meta" is always available in the context of the feedparser instance
        , item;

    while (item = stream.read()) {
        var obj = {
            id: md5(item.guid),
            date: item.pubdate,
            content: item.summary,
            image: item.enclosures[0].url,
            title: item.title,
            source: item.source.title,
            url: item.link
        }

        var args = {
            data: obj,
            headers: {
                "token": "da39a3ee5e6b4b0d5255bfef95601890afd80709",
                "Content-Type": "application/json"
            }
        };

        client.post("http://localhost:8080/WebService/crawler/news", args, function (data, response) {
            console.log(data.toString());
        });
    }
});