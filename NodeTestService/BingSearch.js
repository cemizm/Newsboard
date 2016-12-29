var Bing = require('node-bing-api')({accKey: "7fd745b1b14e4b4f838ed43d1b127062"});
var CrawlerCient = require('./CrawlerClient/index.js');

var md5 = require('md5');
const url = require('url');


Bing.news("'fachhochschule bielefeld'", {
        originalImg: true,
        count: 20,
    },
    function (error, res, body) {
        if (error) return console.log(error);

        body.value.forEach(function (item) {
            var uri = url.parse(item.url, true);

            var img = item.image && item.image.thumbnail ? item.image.thumbnail.contentUrl : null;

            if(item.image && item.image.contentUrl)
                img = item.image.contentUrl;

            var entry = {
                id: md5(uri.query.r),
                date: item.datePublished,
                content: item.description,
                image: img,
                title: item.name,
                source: item.provider.length > 0 ? item.provider[0].name : null,
                url: uri.query.r
            };
            CrawlerCient.publish('oo3wwlnic6jsu9h17p5h4cxrirzz7kcje9tn3e9gw1w09t3xr',
                entry,
                function (data, response) {
                    if (response.statusCode != 200) return console.log("Crawler Error (" + response.statusCode + "): " + data.message);

                    console.log("Crawler published: " + entry.title)
                });
        });
    }
)
;