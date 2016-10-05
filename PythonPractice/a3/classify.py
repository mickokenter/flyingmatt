"""
Assignment 3. Implement a Multinomial Naive Bayes classifier for spam filtering.

You'll only have to implement 3 methods below:

train: compute the word probabilities and class priors given a list of documents labeled as spam or ham.
classify: compute the predicted class label for a list of documents
evaluate: compute the accuracy of the predicted class labels.

"""

from collections import defaultdict
import glob
import math
import os



class Document(object):
    """ A Document. Do not modify.
    The instance variables are:

    filename....The path of the file for this document.
    label.......The true class label ('spam' or 'ham'), determined by whether the filename contains the string 'spmsg'
    tokens......A list of token strings.
    """

    def __init__(self, filename=None, label=None, tokens=None):
        """ Initialize a document either from a file, in which case the label
        comes from the file name, or from specified label and tokens, but not
        both.
        """
        if label: # specify from label/tokens, for testing.
            self.label = label
            self.tokens = tokens
        else: # specify from file.
            self.filename = filename
            self.label = 'spam' if 'spmsg' in filename else 'ham'
            self.tokenize()

    def tokenize(self):
        self.tokens = ' '.join(open(self.filename).readlines()).split()


class NaiveBayes(object):

    def get_word_probability(self, label, term):
        """
        Return Pr(term|label). This is only valid after .train has been called.

        Params:
          label: class label.
          term: the term
        Returns:
          A float representing the probability of this term for the specified class.

        >>> docs = [Document(label='spam', tokens=['a', 'b']), Document(label='spam', tokens=['b', 'c']), Document(label='ham', tokens=['c', 'd'])]
        >>> nb = NaiveBayes()
        >>> nb.train(docs)
        >>> nb.get_word_probability('spam', 'a')
        0.25
        >>> nb.get_word_probability('spam', 'b')
        0.375
        """
        ###TODO
        return self.condprob[term][label]
        pass

    def get_top_words(self, label, n):
        """ Return the top n words for the specified class, using the odds ratio.
        The score for term t in class c is: p(t|c) / p(t|c'), where c'!=c.

        Params:
          labels...Class label.
          n........Number of values to return.
        Returns:
          A list of (float, string) tuples, where each float is the odds ratio
          defined above, and the string is the corresponding term.  This list
          should be sorted in descending order of odds ratio.

        >>> docs = [Document(label='spam', tokens=['a', 'b']), Document(label='spam', tokens=['b', 'c']), Document(label='ham', tokens=['c', 'd'])]
        >>> nb = NaiveBayes()
        >>> nb.train(docs)
        >>> nb.get_top_words('spam', 2)
        [(2.25, 'b'), (1.5, 'a')]
        """
        ###TODO
        score = defaultdict(lambda: 0)
        Cla = [cla for cla in self.Cla if cla != label]
        for voc in self.Voc:
            TTct = 0
            for cla in Cla:
                TTct += self.condprob[voc][cla]
            score[voc] = self.condprob[voc][label] / TTct
        return sorted([(v,k) for k, v in score.items()], key = lambda p: score[p[1]], reverse = True)[:n]
        pass

    def train(self, documents):
        """
        Given a list of labeled Document objects, compute the class priors and
        word conditional probabilities, following Figure 13.2 of your
        book. Store these as instance variables, to be used by the classify
        method subsequently.
        Params:
          documents...A list of training Documents.
        Returns:
          Nothing.
        """
        ###TODO
        self.prior = defaultdict(lambda: 0)
        self.condprob = defaultdict(lambda: defaultdict(lambda: 0))
        
        self.Cla = list()
        self.Voc = list()
        for d in documents:
            self.Cla.append(d.label)
            self.Voc = self.Voc + d.tokens
        self.Cla = list(set(self.Cla))
        self.Voc = list(set(self.Voc))
        
        doc_len = len(documents)
        for cla in self.Cla:
            T_ct = defaultdict(lambda: 0)
            doc_cla = [d for d in documents if d.label == cla]
            dc_len = len(doc_cla)
            self.prior[cla] = dc_len / doc_len
            t = list()
            for d_c in doc_cla:
                t = t + d_c.tokens
            for voc in self.Voc:
                T_ct[voc] = t.count(voc)
            TTct = sum([v+1 for v in list(T_ct.values())])
            for voc in self.Voc:
                self.condprob[voc][cla] = (T_ct[voc] + 1) / TTct
        pass

    def classify(self, documents):
        """ Return a list of strings, either 'spam' or 'ham', for each document.
        Params:
          documents....A list of Document objects to be classified.
        Returns:
          A list of label strings corresponding to the predictions for each document.
        """
        ###TODO
        lst = list()
        for d in documents:
            scores = defaultdict(lambda: 0)
            tmp_lst = list(set([voc for voc in d.tokens if voc in self.Voc]))
            for cla in self.Cla:
                scores[cla] = math.log10(self.prior[cla])
                for v in tmp_lst:
                    scores[cla] = scores[cla] + math.log10(self.condprob[v][cla])
            lst.append(max(list(scores.keys()), key = lambda k: scores[k]))
        return lst
        pass

def evaluate(predictions, documents):
    """ Evaluate the accuracy of a set of predictions.
    Return a tuple of three values (X, Y, Z) where
    X = percent of documents classified correctly
    Y = number of ham documents incorrectly classified as spam
    X = number of spam documents incorrectly classified as ham

    Params:
      predictions....list of document labels predicted by a classifier.
      documents......list of Document objects, with known labels.
    Returns:
      Tuple of three floats, defined above.
    """
    ###TODO
    i, x, Y, Z = (0, 0, 0, 0)
    while i < len(predictions):
        if predictions[i] == documents[i].label:
            x += 1
        elif documents[i].label == 'ham':
            Y += 1
        else:
            Z += 1
        i += 1
    return (x/len(predictions), Y, Z)
    pass

def main():
    """ Do not modify. """
    if not os.path.exists('train'):  # download data
       from urllib.request import urlretrieve
       import tarfile
       urlretrieve('http://cs.iit.edu/~culotta/cs429/lingspam.tgz', 'lingspam.tgz')
       tar = tarfile.open('lingspam.tgz')
       tar.extractall()
       tar.close()
    train_docs = [Document(filename=f) for f in glob.glob("train/*.txt")]
    print('read', len(train_docs), 'training documents.')
    nb = NaiveBayes()
    nb.train(train_docs)
    test_docs = [Document(filename=f) for f in glob.glob("test/*.txt")]
    print('read', len(test_docs), 'testing documents.')
    predictions = nb.classify(test_docs)
    results = evaluate(predictions, test_docs)
    print('accuracy=%.3f, %d false spam, %d missed spam' % (results[0], results[1], results[2]))
    print('top ham terms: %s' % ' '.join('%.2f/%s' % (v,t) for v, t in nb.get_top_words('ham', 10)))
    print('top spam terms: %s' % ' '.join('%.2f/%s' % (v,t) for v, t in nb.get_top_words('spam', 10)))

if __name__ == '__main__':
    main()
