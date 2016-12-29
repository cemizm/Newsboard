var AnalyzerClient = require('./AnalyzerClient/index.js');

var analyzers = [
    {
        token: "56589lt5app3bmvoggxt46ajor6z5vfgpnmpk50wbdnuexywrk9"
    },
    {
        token: "da39a3ee5e6b4b0d5255bfef95601890afd80709"
    },
    {
        token: "dn63vosi7z8nibej9ovl5wmipxkxrubdcuf4qu72qpkacerk9"
    },
];

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
                if (response.statusCode != 200) return console.log("Analyzer Result Error (" + response.statusCode + "): " + data.message);

                console.log("Analyzer Result published: " + entry.title);
            });
        });
    });
});

function getRandomInt(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min)) + min;
}