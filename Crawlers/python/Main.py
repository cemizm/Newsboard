import sys, datetime
import got, nwb
import CrawlerConfig as cfg

def getTweets(keyword):
    date = datetime.datetime.now() + datetime.timedelta(days=-cfg.settings['pastdays']);
    tweetCriteria = got.manager.TweetCriteria().setQuerySearch(keyword).setSince(date.strftime('%Y-%m-%d'))
    return got.manager.TweetManager.getTweets(tweetCriteria)

def eprint(*args, **kwargs):
    print(*args, file=sys.stderr, **kwargs)

client = nwb.CrawlerClient(cfg.settings['host'], cfg.settings['token'])

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

        try:
            client.publish(entry)
            print("published:{}".format(entry['content']))
        except Exception as e:
            eprint(e)



