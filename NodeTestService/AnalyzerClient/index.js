var Client = require('node-rest-client').Client;


var AnalyzerClient = {
    client: new Client(),
    publish: function (token, item, cb) {

        var args = {
            data: item,
            headers: {
                "token": token,
                "Content-Type": "application/json"
            }
        };

        this.client.post("http://localhost:8080/WebService/analyzer/news", args, cb);
    },

    getNewsEntries: function (token, cb) {
        var args = {
            headers: {
                "token": token,
                "Content-Type": "application/json"
            }
        };

        this.client.get("http://localhost:8080/WebService/analyzer/news", args, cb);
    },
};


module.exports = AnalyzerClient;