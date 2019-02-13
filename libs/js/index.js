var Client = require('node-rest-client').Client;


function NewsboardClient(host, token) {
    return {
        client: new Client(),
        host: host,
        token: token,

        publishResult: function (newsId, item, cb) {

            var args = {
                data: item,
                headers: {
                    "token": this.token,
                    "Content-Type": "application/json"
                }
            };

            this.client.post(this.host + "/NewsBoard/WebService/analyzer/news/" + newsId, args, cb);
        },

        getNewsEntries: function (cb) {
            var args = {
                headers: {
                    "token": this.token,
                    "Content-Type": "application/json"
                }
            };

            this.client.get(this.host + "/NewsBoard/WebService/analyzer/news", args, cb);
        },

        publishNewsEntry: function (item, cb) {

            var args = {
                data: item,
                headers: {
                    "token": this.token,
                    "Content-Type": "application/json"
                }
            };

            this.client.post(this.host + "/NewsBoard/WebService/crawler/news", args, cb);
        }
    }
};


module.exports = NewsboardClient;