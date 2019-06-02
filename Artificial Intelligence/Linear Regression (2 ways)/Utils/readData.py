import csv

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
        l = numpy.insert(l,0,1)
        features.append(l)

    features = numpy.array(features)
    return features

def get_result(matrix):
    l = []
    result = []
    for res in matrix:
        l = [res[-1]]
        result.append(l)

    result = numpy.array(result)
    return result

def get_training_data(matrix):
    num = len(matrix)*0.8

    return matrix[:int(num)]

def get_test_data(matrix):
    training = get_training_data(matrix)
    return matrix[len(training):]




