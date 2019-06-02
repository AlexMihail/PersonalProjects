from Cardiocograma.Evolutiv.evolutiv import EvolutiveAlgorithm
from Cardiocograma.GradientDescent.Gradient_descent import main_gradient
from Cardiocograma.Utils.readData import get_matrix_from_file, get_result, get_features, get_training_data, \
    get_test_data, normalizare

matrix = get_matrix_from_file("date2.csv")


result = get_result(matrix)
features = get_features(matrix)

training_features = get_training_data(features)
training_features = normalizare(training_features)

result_training = get_training_data(result)

test_features = get_test_data(features)
test_features = normalizare(test_features)

result_test = get_test_data(result)


epochs = 1000
learning_rate = 0.0006


def main():
    main_gradient(training_features, result_training, epochs, learning_rate,test_features,result_test)
    #ev = EvolutiveAlgorithm(test_features,result_training, epochs, 10, learning_rate)
    #bect = ev.start()
    #print("Best Accuracy", bect.get_Fitness())
main()