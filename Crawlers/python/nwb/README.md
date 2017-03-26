# KI-Newsboard - Crawler client python library 

Client library to interact with the KI-Newsboard. 
 
## Prerquisites
Install following python packages in order to use Crawler Client API.

```bash
pip install requests
```

## Usage

```python
import nwb

client = nwb.CrawlerClient("http://path.to/newsboard/rest-api/", "YOUR TOKEN GOES HERE");
                          
entry = {
  "id": "",                     # global unique ID (e.g. URL to hash)
  "date": "",                   # publish date of entry (format: YYYY-MM-DDThh:mm:ssZ)
  "content": "",                # content of entry
  "image": "",                  # URL to image
  "title": "",                  # title of entry
  "source": "",                 # source of entry (e.g. "Mindener Tageblatt")
  "url": ""                     # url to the entry
};

try:
    client.publish(entry)
except Exception as e:
    print(e)

```