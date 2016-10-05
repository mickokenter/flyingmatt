"""
Assignment 5: K-Means. See the instructions to complete the methods below.
"""

from collections import Counter
import gzip
import math

import numpy as np


class KMeans(object):

    def __init__(self, k=2):
        """ Initialize a k-means clusterer. Should not have to change this."""
        self.k = k

    def cluster(self, documents, iters=10):
        """
        Cluster a list of unlabeled documents, using iters iterations of k-means.
        Initialize the k mean vectors to be the first k documents provided.
        After each iteration, print:
        - the number of documents in each cluster
        - the error rate (the total Euclidean distance between each document and its assigned mean vector), rounded to 2 decimal places.
        See Log.txt for expected output.
        The order of operations is:
        1) initialize means
        2) Loop
          2a) compute_clusters
          2b) compute_means
          2c) print sizes and error
        """
        ###TODO
        self.clusters = []
        for doc in documents[:self.k]:
            norm = self.sqnorm(doc)
            self.clusters.append([doc, [doc], norm])
        i = 0
        while i < iters:
            self.compute_clusters(documents)
            self.compute_means()
            print(self.error(documents))
            print([len(clu[1]) for clu in self.clusters])
            i += 1
        pass

    def compute_means(self):
        """ Compute the mean vectors for each cluster (results stored in an
        instance variable of your choosing)."""
        ###TODO
        for cluster in self.clusters:
            cluster[0] = Counter(cluster[0])
            for doc in cluster[1]:
                cluster[0].update(doc)
        pass

    def compute_clusters(self, documents):
        """ Assign each document to a cluster. (Results stored in an instance
        variable of your choosing). """
        ###TODO
        n = 0
        clusters_len = len(self.clusters)
        while n < clusters_len:
            self.clusters[n][1] = []
            n += 1
        for doc in documents:
            i = 0
            min_distance = 299792458
            cluster_count = 0
            for cluster in self.clusters:
                d = self.distance(doc, cluster[0], cluster[2])
                if d < min_distance:
                    cluster_count = i
                    min_distance = d
                i += 1
            self.clusters[cluster_count][1].append(doc)
        pass

    def sqnorm(self, d):
        """ Return the vector length of a dictionary d, defined as the sum of
        the squared values in this dict. """
        ###TODO
        result = 0
        for value in list(d.values()):
            result += value ** 2
        return result
        pass

    def distance(self, doc, mean, mean_norm):
        """ Return the Euclidean distance between a document and a mean vector.
        See here for a more efficient way to compute:
        http://en.wikipedia.org/wiki/Cosine_similarity#Properties"""
        ###TODO
        if len(doc) * len(mean) != 0:
            keys = set(list(doc.keys())) | set(list(mean.keys()))
            dis_sq = 0
            for k in keys:
                dis_sq += (doc[k] - mean[k]) ** 2
            return math.sqrt(dis_sq)
        else:
            return 299792458
        pass

    def error(self, documents):
        """ Return the error of the current clustering, defined as the total
        Euclidean distance between each document and its assigned mean vector."""
        ###TODO
        error = 0
        for cluster in self.clusters:
            for doc in cluster[1]:
                error += self.distance(doc, cluster[0], cluster[2])
        return error
        pass

    def print_top_docs(self, n=10):
        """ Print the top n documents from each cluster. These are the
        documents that are the closest to the mean vector of each cluster.
        Since we store each document as a Counter object, just print the keys
        for each Counter (sorted alphabetically).
        Note: To make the output more interesting, only print documents with more than 3 distinct terms.
        See Log.txt for an example."""
        ###TODO
        i = 0
        for cluster in self.clusters:
            print("Cluster %d" % i)
            doc_distance = [(d, self.distance(d, cluster[0], cluster[2])) for d in cluster[1]]
            sorted_doc = sorted(doc_distance, key = lambda t: t[1])[:n]
            for doc, dist in sorted_doc:
                s = [str(t) for t in doc if doc[t] != 0]
                print(" ".join(s))
            i += 1
        pass


def prune_terms(docs, min_df=3):
    """ Remove terms that don't occur in at least min_df different
    documents. Return a list of Counters. Omit documents that are empty after
    pruning words.
    >>> prune_terms([{'a': 1, 'b': 10}, {'a': 1}, {'c': 1}], min_df=2)
    [Counter({'a': 1}), Counter({'a': 1})]
    """
    ###TODO
    counter = Counter()
    for doc in docs:
        counter.update(list(doc.keys()))
    terms = [t for t in counter if counter[t] < min_df]
    i = 0
    while i < len(docs):
        doc = docs[i]
        for term in list(doc.keys()):
            if term in terms:
                del doc[term]
    return docs
    pass

def read_profiles(filename):
    """ Read profiles into a list of Counter objects.
    DO NOT MODIFY"""
    profiles = []
    with gzip.open(filename, mode='rt', encoding='utf8') as infile:
        for line in infile:
            profiles.append(Counter(line.split()))
    return profiles


def main():
    profiles = read_profiles('profiles.txt.gz')
    print('read', len(profiles), 'profiles.')
    profiles = prune_terms(profiles, min_df=2)
    km = KMeans(k=10)
    km.cluster(profiles, iters=20)
    km.print_top_docs()

if __name__ == '__main__':
    main()
