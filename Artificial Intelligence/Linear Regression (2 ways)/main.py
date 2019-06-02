from Concrete.GradientDescent.gradient_descent import gradient_descent, getGD_error, update_coef
from Concrete.Utils.readData import get_matrix_from_file, get_result, get_features, get_training_data, get_test_data
from Concrete.Utils.utils import normalize
from Concrete.leastSquares.least_squares import least_squares, get_error

matrix = get_matrix_from_file("date.in")
matrix = normalize(matrix)

result = get_result(matrix)
features = get_features(matrix)

training_features = get_training_data(features)
result_features = get_training_data(result)
test_features = get_test_data(features)
result_test = get_test_data(result)


alg_type = "LEAST_SQUARE "
plt_size = 25
epochs = 100
learning_rate = 0.0000055


def main():
    if alg_type == "LEAST_SQUARES":
        coeffs = least_squares(training_features, result_features)
        print("Mean squared error  " + str(get_error(coeffs, test_features, result_test)))
        print("Coeffs: " + str(coeffs))
    else:
        res = update_coef(training_features, result_features, learning_rate, epochs)

        coeffs = []
        for el in res:
            coeffs.append([el])
        print("Mean squared error : " + str(getGD_error(coeffs, test_features, result_test)))
        print("Coeffs: " + str(coeffs))


main()