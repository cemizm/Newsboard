# KI-Newsboard - Crawler client javascript library

Client library to interact with the KI-Newsboard. 
 
## Prerquisites
Install npm packages in order to use Crawler Client API.

```bash
npm install
```

## Usage

```javascript

var CrawlerClient = require('path/to/CrawlerClient');

var client = new CrawlerClient("http://path.to/newsboard/rest-api/", "YOUR TOKEN GOES HERE");

var entry = {
    id: "",                     // global unique ID (e.g. URL to hash)
    date: "",                   // publish date of entry (format: YYYY-MM-DDThh:mm:ssZ)
    content: "",                // content of entry
    image: "",                  // URL to image
    title: "",                  // title of entry
    source: "",                 // source of entry (e.g. "Mindener Tageblatt")
    url: ""                     // url to the entry
};

client.publish(entry, function(error, result) {
    
    if(error != null) {
        //error handling...
        console.log(error.code + ": " + error.message);
    }
    else {
        // sucessfully published 
    }
});

```