import copy
import numpy as np


class Cromozome:
    def __init__(self, weights, fitness):
        self._cromozome = weights
        self._fitness = fitness

    def get_Cromozome(self):
        return self._cromozome

    def get_Fitness(self):
        return self._fitness

    def set_Cromozome(self, Cromozome):
        self._cromozome = Cromozome

    def set_Fitness(self, Fitness):
        self._fitness = Fitness

    Cromozome = property(get_Cromozome, set_Fitness, None, "No's docstring")
    Fitness = property(get_Fitness, set_Fitness, None, "No's docstring")


class EvolutiveAlgorithm:
    def __init__(self, features,res, numberOfGen, popNr, learningRate):
        self._learningRate = learningRate
        self._x = features
        self._y = res
        self._noGen = numberOfGen
        self._popSize = popNr
        self._population = self.__initialize()



    def __initialize(self):
        population = []
        for crom in range(0, self._popSize):
            weights = [0 for i in range(0, len(self._x[0]) + 1)]
            c = Cromozome(weights, None)
            population.append(c)  # adauga cromozom
        return population

    def __aplySigmoid(self, x):
        sg = 1.0 / (1.0 + np.math.exp(0.0 - x))
        return sg

    def __calculate(self, curent, cromoz):
        s = 0.0
        for i in range(0, len(curent)):
            s += cromoz[i] * curent[i]
        s += cromoz[len(curent)]
        return s

    def start(self):

        self._evaluate()
        g = 0
        acc = 0
        while g <= self._noGen:
            print("Generatia: ", g)
            p1 = self.__selectParent()
            p2 = self.__selectParent()
            children = self.__crossOver(p1, p2)
            d1 = self.__mutate(children[0])
            d2 = self.__mutate(children[1])
            fitnessd1 = self.__fitness(d1)
            fitnessd2 = self.__fitness(d2)
            worst = self.__getWorst()
            best = self.__getBestFrom2(d1, d2)
            self._population.remove(worst)
            self._population.append(best)
            print(self.__getBest().get_Fitness())
            g += 1
            acc = self.__getBest().get_Fitness()
        return self.__getBest()

    def __selectParent(self):
        self._population.sort(key=lambda x: x.get_Fitness())
        random2 = np.random.rand()
        if random2 <= 0.7:
            return self._population[0]
        else:
            random1 = np.random.randint(self._popSize)
        return self._population[random1]

    def __crossOver(self, p1, p2):
        cromp1 = p1.get_Cromozome()
        cromp2 = p2.get_Cromozome()
        weights1 = []
        weights2 = []
        i = 0
        c1 = copy.copy(cromp2)  # initializare copii cu parintii
        c2 = copy.copy(cromp1)
        while i < len(c1) // 2:  # mutatie copii
            c1[i] = cromp1[i]
            c2[i] = cromp2[i]
            i += 1

        crom1 = Cromozome(c1, None)
        crom2 = Cromozome(c2, None)
        return [crom1, crom2]

    def __mutate(self, child):
        c = child.get_Cromozome()
        predicted = []
        n = len(self._x)
        randomaux = np.random.randint(1, len(c))
        for k in range(0, randomaux):
            random = np.random.randint(len(c))
            randsign = np.random.randint(2)
            if (randsign == 0):
                c[random] = c[random] + np.random.rand()
            else:
                c[random] = c[random] - np.random.rand()
        return child

    def __fitness(self, d):  # este acuratetea
        fitness = 0
        crom = d.get_Cromozome()

        nr = [0, 0, 0]
        predictedLabels = []
        for i in range(len(self._x)):
            predictedValue = self.__aplySigmoid(self.__calculate(self._x[i], crom))
            lbl = ""
            if predictedValue <= 0.65:
                predictedLabels.append("normal")
                lbl = "normal"
                nr[0] += 1
            else:
                if 0.65 < predictedValue <= 0.93:
                    predictedLabels.append("suspect")
                    lbl = "suspect"
                    nr[1] += 1
                if 0.93 < predictedValue <= 1.00:
                    predictedLabels.append("patologic")
                    lbl = "patologic"
                    nr[2] += 1

        print("clasificari", nr)
        acuratete = self._accuracy(predictedLabels)
        print("fitness", acuratete)
        fitness = acuratete
        d.set_Fitness(fitness)
        return fitness

    def __getWorst(self):
        self._population.sort(key=lambda x: x.get_Fitness())
        return self._population[0]

    def __getBest(self):
        self._population.sort(key=lambda x: x.get_Fitness())
        return self._population[len(self._population) - 1]

    def _evaluate(self):
        for i in range(0, self._popSize):
            self.__fitness(self._population[i])

    def __getBestFrom2(self, c1, c2):
        if c1.get_Fitness() > c2.get_Fitness():
            return c1
        else:
            return c2

    def _accuracy(self, predictedLabels):
        noMatches = 0
        out = 0
        nr = [0, 0, 0]
        for i in range(len(predictedLabels)):
            if self._y[i] == 0:
                out = "normal"
            if self._y[i] == 0.5:
                out = "suspect"
            if self._y[i] == 1:
                out = "patologic"
            if predictedLabels[i] == out:
                if out == "normal":
                    nr[0] += 1
                if out == "suspect":
                    nr[1] += 1
                if out == "patologic":
                    nr[2] += 1
                noMatches += 1
        print("Corect", nr)
        print("-----------  ")

        return noMatches / len(predictedLabels) * 100