var request = require('request');
var cheerio = require('cheerio');

var MTParser = function(url){
    var parser = {
        url: url,
    };

    parser.parse = function(item, cb){

        this.getEntry(item.link, function(entry){

            entry.source = "Mindener Tageblatt";
            entry.url = item.link;
            entry.date = item.pubdate

            cb(entry);
        });

    }

    parser.getEntry = function(url, cb) {
        request(url, function(error, response, html) {
            if(error)
                return;

            var $ = cheerio.load(html);

            var entry = {};

            entry.id = "mt-" + $(".article-detail").data("article-id");
            entry.title = $(".article-detail header h1").text();
            entry.image = "http://www.mt.de" + $(".article-detail header img").attr("src");

            entry.content = "";
            $(".article-detail p.em_text").each(function(i, elem){
                entry.content += $(this).text();
            });

            cb(entry);
        });
    }

    return parser;
};

module.exports = MTParser;