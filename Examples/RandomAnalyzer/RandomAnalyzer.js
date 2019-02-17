var NewsboardClient = require('newsboardclient');

var client = new NewsboardClient("http://newsboard.payara:8080", process.env.TOKEN_ANALYZER);

client.getNewsEntries(function (items) {
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

        client.publishResult(entry.id, result, function (data, response) {
            if (response.statusCode != 200) return console.log("Analyzer Result Error (" + response.statusCode + "): " + data.message);
            console.log("Analyzer Result published: " + entry.title);
        });
    });

    console.log("RandomAnalyzer entries analyzed")
});

function getRandomInt(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min)) + min;
}