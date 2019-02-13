

class AnalyzerResult:
    """Represents the result of the sentiment analysis

    * Each text will get an overall score.
    * Some texts may get additionally a sentence-level score.

    Sentiment scores are currently ints: [-100, +100]
    """
    def __init__(self, json = {}):
        self.data = json

    def get_score(self):
        """
        :return: the overall sentiment score
        :rtype: int
        """
        return self.data.get('value', None)

    def set_score(self, score):
        """
        set the overall score
        :param score (int)
        """
        self.data['value'] = score

    def get_sentences(self):
        """
        :return: list of dictionary: for each sentence the indexes of the sentence in the given text
            and its score (float). sentences will appear in the same order
            as in the given text.
        :rtype: list({"charStart": int, "charEnd": int, "value": int}, {"charStart": int, "charEnd": int, "value": int}, ...)
        """
        return self.data.get('sentenceResults', [])

    def add_sentence(self, start, end, score):
        """
        add the given sentence and its score
        :param start: start index of sentence (int)
        :param end: end index of sentence (int)
        :param score: Sentiment score (int).
        """
        if not 'sentenceResults' in self.data:
            self.data['sentenceResults'] = []

        self.data['sentenceResults'].append({
            "charStart": start,
            "charEnd": end,
            "value": score
        })

    def get_data(self):
        """
        :return: returns the json serialized data
        :rtype: json object
        """
        return self.data