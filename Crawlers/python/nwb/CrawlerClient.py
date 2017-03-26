import requests, sys

class CrawlerClient:
    def __init__(self, host, token):
        self.host = host
        self.headers = {
            "token": token,
            "Content-Type": "application/json"
        }

    def publish(self, entry):
        resp = requests.post(self.host + "/crawler/news", json=entry, headers=self.headers)
        if resp.status_code != 200:
            raise ValueError('{}: {}'.format(resp.status_code, resp.json()["message"]))


if __name__ == "__main__":
    client = CrawlerClient("http://localhost:8080/NewsBoard/WebService", "da39a3ee5e6b4b0d5255bfef95601890afd80709")
    entry =  {
        "id": "123",
        "date": "1984-06-13T18:25:00Z",
        "content": "Example Content",
        "image": "https://placehold.it/500x500",
        "title": "Example Title",
        "source": "Example Source",
        "url": "http://heise.de"
    }
    client.publish(entry)