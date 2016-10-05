from agents import Agent
from agents import Agent_single_sklearn

from sklearn.linear_model import LogisticRegression
from sklearn.naive_bayes import BernoulliNB
from sklearn.svm import SVC

class Agent_xqiu12(Agent):
    def choose_the_best_classifier(self, X_train, y_train, X_val, y_val):
        bnb = BernoulliNB()
        lr = LogisticRegression()
        svc = SVC(kernel='poly', probability=True, degree=4, random_state=0)
        classifiers = [bnb, lr, svc]
        choosen = 0
        error_t = 0
        for i in range(len(classifiers)):
            classifiers[i].fit(X_train, y_train)
            if classifiers[i].classes_[0] == 'Excellent':
                exl = 0
            else:
                exl = 1
            error = 0
            num_products = X_val.shape[0]
            for p in range(num_products):
                prob = classifiers[i].predict_proba(X_val[p])[0][exl]
                if y_val[p] == 'Excellent':
                    if prob < 0.51:
                        error += 1
                else:
                    if prob >= 0.51:
                        error += 1
            if i == 0:
            	error_t = error
            if error < error_t:
            	error_t = error
            	choosen = i
        return classifiers[choosen]
