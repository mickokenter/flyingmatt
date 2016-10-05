from agents import Agent

class Agent_xqiu12(Agent):
    def will_buy(self, value, price, prob):
        if (prob > 0.5):
            return (price/value <= 0.8)
        elif (prob < 0.49):
            return (price/value <= 0.2)
        else:
            return True