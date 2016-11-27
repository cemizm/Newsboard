var Client = require('node-rest-client').Client;


var CrawlerClient = {
    client: new Client(),
    publish: function (token, item, cb) {

        var args = {
            data: item,
            headers: {
                "token": token,
                "Content-Type": "application/json"
            }
        };

        this.client.post("http://localhost:8080/WebService/crawler/news", args, cb);
    }
};


module.exports = CrawlerClient;