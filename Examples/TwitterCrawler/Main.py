import sys, datetime, os
import got, nwb
import CrawlerConfig as cfg

def getTweets(keyword):
    date = datetime.datetime.now() + datetime.timedelta(days=-cfg.settings['pastdays']);
    tweetCriteria = got.manager.TweetCriteria().setQuerySearch(keyword).setSince(date.strftime('%Y-%m-%d'))
    return got.manager.TweetManager.getTweets(tweetCriteria)

client = nwb.Client("http://newsboard.payara:8080", os.environ['TOKEN_CRAWLER'])

for keyword in cfg.settings['keywords']:
    tweets = getTweets(keyword)
    for tweet in tweets:
        entry = nwb.models.NewsEntry()

        entry.set_id("tw-" + tweet.id)
        entry.set_date(tweet.date.strftime('%Y-%m-%dT%H:%M:%SZ'))
        entry.set_source("Twitter - " + tweet.username)
        entry.set_image(tweet.image)
        entry.set_url(tweet.permalink)
        entry.set_content(tweet.text)
        try:
                client.publish_news(entry)
        except ValueError as err:
                print(err)

print('TwitterCrawler entries published')



