import requests
from .models import *

class Client:
    def __init__(self, host, token):
        self.host = host + "/NewsBoard/WebService"
        self.headers = {
            "token": token,
            "Content-Type": "application/json"
        }

    def get_newsentries(self):
        """
        loads all unanalyzed news entries for given token
        :return:
        """
        resp = requests.get(self.host + "/analyzer/news", headers=self.headers)

        data = resp.json()
        if resp.status_code != 200:
            raise ValueError('Remote Error ({}): {}'.format(resp.status_code, data["message"]))

        entries = []
        for entry in data:
            entries.append(NewsEntry(entry));

        return entries

    def publish_result(self, id, result):
        """
        publishes an analyzer result for the given news id
        :param id: id of the news
        :param result: analyzer result for the news
        """
        resp = requests.post(self.host + "/analyzer/news/" + id, headers=self.headers, json=result.get_data())

        if resp.status_code != 200:
            raise ValueError('Remote Error ({}): {}'.format(resp.status_code, resp.json()["message"]))

    def publish_news(self, entry):
        """
        publishes a news entry
        :param entry: news entry to publish
        """
        resp = requests.post(self.host + "/crawler/news/", headers=self.headers, json=entry.get_data())

        if resp.status_code != 200:
            raise ValueError('Remote Error ({}): {}'.format(resp.status_code, resp.json()["message"]))