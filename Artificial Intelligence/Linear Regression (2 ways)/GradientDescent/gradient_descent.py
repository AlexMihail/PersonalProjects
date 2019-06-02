import numpy as np

def gradient_descent(feat, res, learning_rate, epochs):
    features = np.asarray(feat)
    result = np.asarray(res[:]).T[0]

    predictions = []

    coeffs = np.random.rand(len(features[0]))


    for i in range(epochs):
        prediction = np.dot(features, coeffs)
        predictions.append(prediction)
        error = prediction - result
        print(error)
        coeffs = coeffs - (learning_rate * np.dot(features.T, error))

    return coeffs



def update_coef(feat,resu,learning_rate,epochs):

    coef = np.random.rand(len(feat[0]))
    res = []
    for i in range(0,len(resu)):
        res.append(resu[i][0])

    v_computed = []
    for k in range(epochs):
        for ex in feat:
            v_computed.append(predict(coef,ex))

        for i in range(0,len(coef)):
            gradient = 0.0
            for j in range(0,len(feat)):
                exi=feat[j]
                gradient += (v_computed[j] - res[j]) * exi[i]
            coef[i] = coef[i] - learning_rate*gradient

    return coef


def predict(coef,ex):
    computed = 0
    for i in range(0,len(ex)):
        computed += coef[i]*ex[i]
    computed += coef[-1]
    return computed


def getGD_error(coefficients,test_set,test_res):

    '''
    for test in range(len(test_set)):
        calculated_res = 0
        for i in range(len(coefficients)):
            calculated_res += coefficients[i][0] * test_set[test][i]
        print("Expected: " + str(test_res[test][0]) + " Predicted: " + str(calculated_res))
    '''
    return ((np.dot(test_set, coefficients) - test_res) ** 2).mean()