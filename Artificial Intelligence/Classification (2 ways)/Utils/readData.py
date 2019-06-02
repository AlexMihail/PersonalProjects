import csv
from cmath import sqrt
from numpy import double, math

import numpy

def get_matrix_from_file(in_file):
    """
    Function to retrieve data from file
    :param in_file: input file
    :return: a matrix of features
    """
    result = numpy.array(list(csv.reader(open(in_file, "rt"), delimiter=","))).astype("float")


    return result.tolist()


def get_features(matrix):
    l = []
    features = []
    for res in matrix:
        l = res[:len(res)-1]
        features.append(l)

    features = numpy.array(features)
    return features

def get_result(matrix):
    l = []
    result = []
    for res in matrix:
        l = res[-1]
        if l == 1:
            l=0
        elif l == 2:
            l = 0.5
        elif l == 3 :
            l = 1
        result.append(l)

    result = numpy.array(result)
    return result

def get_training_data(matrix):
    num = len(matrix)*0.8

    return matrix[:int(num)]

def get_test_data(matrix):
    training = get_training_data(matrix)
    return matrix[len(training):]


def normalizare(matrix):


    suma = []
    media = []
    patrate = []
    dev = []

    nrAtribute = len(matrix[0])
    for i in range(nrAtribute):
        suma.append(0)
        media.append(0)
        patrate.append(0)
        dev.append(0)



    for i in range(len(matrix)):
        for j in range(nrAtribute):
            suma[j] += matrix[i][j]


    for q in range(nrAtribute):
        media[q] = suma[q] / len(matrix)


    for k in range(len(matrix)):
        for l in range(nrAtribute):
            patrate[l] += (matrix[k][l] - media[l]) * (matrix[k][l] - media[l])



    for d in range(nrAtribute):
        dev[d] = (sqrt(patrate[d] / (len(matrix) - 1)).real*100)/100



    rez = []
    for p in range(len(matrix)):
        new = []
        for at in range(nrAtribute):
            val = (matrix[p][at] - media[at]) / dev[at]
            val = round(val, 2)
            new.append(val)
        rez.append(new)

    return rez


