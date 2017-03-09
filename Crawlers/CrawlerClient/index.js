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

        try {
            this.client.post(baseUrl + "/crawler/news", args, function (response, data) {
                if (response.statusCode != 200) {
                    return cb(item, {
                        code: response.statusCode,
                        message: data.message
                    });
                }

                cb(item, null, response);
            });
        }catch(err){
            cb(item, err);
        }
    };

    return api;
}

module.exports = CrawlerClient;