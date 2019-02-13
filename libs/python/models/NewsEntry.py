
class NewsEntry:
    """Represents a news entry of the newsboard
    """
    def __init__(self, json = {}):
        self.data = json

    def get_id(self):
        """
        :return: the id
        :rtype: str
        """
        return self.data.get('id', None)

    def set_id(self, id):
        """
        sets the id of the news entry
        :param id: the id to set (str)
        """
        self.data['id'] = id

    def get_date(self):
        """
        :return: the pubdate
        :rtype: datetime
        """
        return self.data.get('date', None)

    def set_date(self, date):
        """
        sets the pub date of the news entry
        :param date: the date to set (datetime)
        """
        self.data['date'] = date

    def get_source(self):
        """
        :return: source of news entry
        :rtype: str
        """
        return self.data.get('source', None)

    def set_source(self, source):
        """
        sets the source of the news entry
        :param source: the source to set (str)
        """
        self.data['source'] = source

    def get_url(self):
        """
        :return: url of
        :rtype: str
        """
        return self.data.get('url', None)

    def set_url(self, url):
        """
        sets the url of the news entry
        :param url: the url to set (str)
        """
        self.data['url'] = url

    def get_title(self):
        """
        :return: title of news entry
        :rtype: str
        """
        return self.data.get('title', None)

    def set_title(self, title):
        """
        sets the title of the news entry
        :param title: the title to set (str)
        """
        self.data['title'] = title

    def get_content(self):
        """
        :return: content (plaintext) of news entry
        :rtype: str
        """
        return self.data.get('content', None)

    def set_content(self, content):
        """
        sets the content of the news entry
        :param content: the content to set (str)
        """
        self.data['content'] = content

    def get_image(self):
        """
        :return: full url to the image
        :rtype: str
        """
        return self.data.get('image', None)

    def set_image(self, image):
        """
        sets the image of the news entry
        :param image: the url to the image to set (str)
        """
        self.data['image'] = image

    def get_data(self):
        """
        :return: returns the json serialized data
        :rtype: json object
        """
        return self.data