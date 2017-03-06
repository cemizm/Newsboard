# Crawler & Analyzer Host
Host service for periodically triggering analyzers and crawlers. 

## Configuration

```javascript
{
  "loglevel": "info",                           // loglevel for logging (info, error, verbose)
  "logdir": "./logs",                           // relative or absolute path to logging directory 
  "services": [                                 // array of crawler/analyzer to execute
    {
      "name": "RssCrawler",                     // name of service used for log file and entries
      "loglevel": "info",                       // (optional) loglevel for service
      "periodic": true,                         // execute periodacally
      "interval": 300,                          // interval of execution in seconds (if periodacally)
      "command": "npm",                         // command to execute
      "args": [                                 // array of arguments to pass 
        "run",
        "rss"
      ],
      "options": {                              // (optional) options for command
        "path": "../NodeTestService",           // (optional) working directory of command
        "envvars": {                            // (optional) key/value pair to set as environment variables
          "key": "value",                       
          "key2": "value"
        },
        "shell": false                          // (optional) execute process in shell 
      }
    },
    {
      "name": "Test2",
      "periodic": true,
      "interval": 15,
      "command": "npm",
      "args": [
        "run",
        "bing"
      ]
    }
  ]
}
```