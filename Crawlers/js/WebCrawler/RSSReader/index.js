var FeedParser = require('feedparser')
    , request = require('request');


function RSSReader() {
    var self = this;

    this.parser = new FeedParser();

    this.read = function (url, cb) {
        this.callback = cb;

        request(url).pipe(this.parser);
    };

    this.parse = function (stream) {
        var item;
        while (item = stream.read()) {
            this.callback(item);
        }
    };

    this.parser.on('readable', function () {
        self.parse(this)
    });
};


module.exports = RSSReader;