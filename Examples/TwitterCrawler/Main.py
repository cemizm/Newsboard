import sys, datetime
import got, nwb
import CrawlerConfig as cfg

def getTweets(keyword):
    date = datetime.datetime.now() + datetime.timedelta(days=-cfg.settings['pastdays']);
    tweetCriteria = got.manager.TweetCriteria().setQuerySearch(keyword).setSince(date.strftime('%Y-%m-%d'))
    return got.manager.TweetManager.getTweets(tweetCriteria)

client = nwb.Client(cfg.settings['host'], cfg.settings['token'])

for keyword in cfg.settings['keywords']:
    tweets = getTweets(keyword)
    for tweet in tweets:
        entry = {
            "id": "tw-" + tweet.id,
            "date": tweet.date.strftime('%Y-%m-%dT%H:%M:%SZ'),
            "content": tweet.text,
            "source": "Twitter - " + tweet.username,
            "image": tweet.image,
            "url": tweet.permalink
        }

        client.publish_news(entry)

print 'TwitterCrawler entries published'



