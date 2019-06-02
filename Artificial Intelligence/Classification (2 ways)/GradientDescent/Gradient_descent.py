from numpy.ma import exp

from numpy.random import random
import numpy as np

def sigmoid(x):
    sg = 1.0 / (1.0 + np.math.exp(0.0 - x))
    return sg


def update_coef(coef,learning_rate,feat,res):
    v_computed = []

    for ex in feat:
        v_computed.append(sigmoid(predict(coef,ex)))

    for i in range(0, len(coef)):
        gradient = 0.0
        for j in range(0, len(feat)):
            exi = feat[j]
            gradient += (v_computed[j] - res[j]) * exi[i]

        coef[i] = coef[i] - learning_rate*gradient

    return coef


def predict(coef,ex):
    computed = 0.0
    for i in range(0,len(ex)):
        computed += coef[i]*ex[i]
    computed += coef[-1]
    return computed


def main_gradient(features,res ,epch, learning_rate, testFeatures, resultTest):

    coef = random(len(features[0]))

    for e in range(epch):
        update_coef(coef,learning_rate,features,res)

    predictedLabels = []
    predictedValue = 0
    nr = [0, 0, 0]
    for i in range(len(testFeatures)):
        predictedValue = sigmoid(predict(coef,testFeatures[i]))
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
        print(i, "predicted:", predictedValue, 'type:', lbl, resultTest[i])

    acuratete = accuracy(predictedLabels,resultTest)
    print("Acuratete: ", acuratete)



def accuracy(predictedLabels,y):
    noMatches = 0
    out = 0
    nr = [0, 0, 0]
    for i in range(len(predictedLabels)):
        if y[i] == 0:
            out = "normal"
        if y[i] == 0.5:
            out = "suspect"
        if y[i] == 1:
            out = "patologic"
        if predictedLabels[i] == out:
            if out == "normal":
                nr[1] += 1
            if out == "suspect":
                nr[0] += 1
            if out == "patologic":
                nr[2] += 1

            noMatches += 1

    print(noMatches)

    return noMatches / len(predictedLabels) * 100