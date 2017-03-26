var Client = require('node-rest-client').Client;

var CrawlerClient = function (baseUrl, token) {
    var api = {
        baseUrl: baseUrl,
        token: token,
        client: new Client(),
    };

    api.publish = function (item, cb) {
        var args = {
            data: item,
            headers: {
                "token": this.token,
                "Content-Type": "application/json"
            }
        };

        this.client.post(baseUrl + "/crawler/news", args, function (body, response) {
            var error = null;

            if (response.statusCode != 200) {
                error = {
                    code: response.statusCode,
                    message: body.message
                };
            }

            cb(error, body);
        });
    };

    return api;
}

module.exports = CrawlerClient;