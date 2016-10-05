""" Assignment 2
"""
import abc
from collections import defaultdict
import math

import index


def idf(term, index):
    """ Compute the inverse document frequency of a term according to the
    index. IDF(T) = log10(N / df_t), where N is the total number of documents
    in the index and df_t is the total number of documents that contain term
    t.

    Params:
      terms....A string representing a term.
      index....A Index object.
    Returns:
      The idf value.

    >>> idx = index.Index(['a b c a', 'c d e', 'c e f'])
    >>> idf('a', idx) # doctest:+ELLIPSIS
    0.477...
    >>> idf('d', idx) # doctest:+ELLIPSIS
    0.477...
    >>> idf('e', idx) # doctest:+ELLIPSIS
    0.176...
    """
    ###TODO
    df_t = 0
    N = len(index.documents)
    for doc in index.documents:
        if term.lower() in doc:
            df_t += 1
    return math.log10( N / df_t)
    pass


class ScoringFunction:
    """ An Abstract Base Class for ranking documents by relevance to a
    query. """
    __metaclass__ = abc.ABCMeta

    @abc.abstractmethod
    def score(self, query_vector, index):
        """
        Do not modify.

        Params:
          query_vector...dict mapping query term to weight.
          index..........Index object.
        """
        return


class RSV(ScoringFunction):
    """
    See lecture notes for definition of RSV.

    idf(a) = log10(3/1)
    idf(d) = log10(3/1)
    idf(e) = log10(3/2)
    >>> idx = index.Index(['a b c', 'c d e', 'c e f'])
    >>> rsv = RSV()
    >>> rsv.score({'a': 1.}, idx)[1]  # doctest:+ELLIPSIS
    0.4771...
    """

    def score(self, query_vector, index):
        ###TODO
        rsv = defaultdict(lambda: 0)
        rsv_index = 1
        for doc in index.documents:
            for vec in query_vector:
                if vec.lower() in doc:
                    rsv[rsv_index] = idf(vec, index) * query_vector[vec]
            rsv_index += 1
        return rsv
        pass

    def __repr__(self):
        return 'RSV'


class BM25(ScoringFunction):
    """
    See lecture notes for definition of BM25.

    log10(3) * (2*2) / (1(.5 + .5(4/3.333)) + 2) = log10(3) * 4 / 3.1 = .6156...
    >>> idx = index.Index(['a a b c', 'c d e', 'c e f'])
    >>> bm = BM25(k=1, b=.5)
    >>> bm.score({'a': 1.}, idx)[1]  # doctest:+ELLIPSIS
    0.61564032...
    """
    def __init__(self, k=1, b=.5):
        self.k = k
        self.b = b

    def score(self, query_vector, index):
        ###TODO
        bm = defaultdict(lambda: 0)
        sum_len = sum([len(doc) for doc in index.documents])
        for vec in query_vector:
            idft = idf(vec, index)
            bm_index = 1
            for doc in index.documents:
                if vec.lower() in doc:
                    bm[bm_index] = bm[bm_index] + idft * ((self.k + 1) * doc.count(vec.lower()) / (self.k * ((1 - self.b) + self.b * len(doc) / (sum_len / len(index.documents))) + doc.count(vec.lower()))) * query_vector[vec]
                bm_index += 1
        return bm
        pass

    def __repr__(self):
        return 'BM25 k=%d b=%.2f' % (self.k, self.b)


class Cosine(ScoringFunction):
    """
    See lecture notes for definition of Cosine similarity.  Be sure to use the
    precomputed document norms (in index), rather than recomputing them for
    each query.

    >>> idx = index.Index(['a a b c', 'c d e', 'c e f'])
    >>> cos = Cosine()
    >>> cos.score({'a': 1.}, idx)[1]  # doctest:+ELLIPSIS
    0.792857...
    """
    def score(self, query_vector, index):
        ###TODO
        sc = defaultdict(lambda: 0)
        for vec in query_vector:
            lens = len([doc for doc in index.documents if vec.lower() in doc])
            idft = math.log10(len(index.documents) / lens)
            sc_index = 1
            for doc in index.documents:
                if vec.lower() in doc:
                    sc[sc_index] += (1 + math.log10(doc.count(vec.lower()))) * idft * query_vector[vec]
                sc_index += 1
        for s in sc:
            sc[s] = sc[s] / index.doc_norms[s]
        return sc
        pass

    def __repr__(self):
        return 'Cosine'
