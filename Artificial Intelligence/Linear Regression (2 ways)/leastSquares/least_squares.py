from Concrete.Utils.utils import transpusa, mul_matrix, inversa, matrix_mean, matrix_square_diff
import numpy as np
from numpy.linalg import inv

def least_squares(train, res):
    training = train
    result_training = res

    train = np.array(train)
    res = np.array(train)

    trans = train.transpose()

    m1 = np.dot(trans,training)
    m1 = inv(m1)

    p = np.dot(m1, trans)

    return np.dot(p, result_training)




def get_error(coefficients,test_set,test_res):

    '''
    for test in range(len(test_set)):
        calculated_res = 0
        for i in range(len(coefficients)):
            calculated_res += coefficients[i][0] * test_set[test][i]
        print("Expected: " + str(test_res[test][0]) + " Predicted: " + str(calculated_res))
    '''
    return ((np.dot(test_set, coefficients) - test_res) ** 2).mean()

