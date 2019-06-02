import numpy as np

def transpusa(matrix):

    t = []
    for col in range(len(matrix[0])):
        trans_row = []
        for row in range(len(matrix)):
            trans_row.append(matrix[row][col])
        t.append(trans_row)
    return t


def mul_matrix(X, Y):
    return [[sum(a * b for a, b in zip(X_row, Y_col)) for Y_col in zip(*Y)] for X_row in X]


def get_minor(matrix, i, j):
    return [row[:j] + row[j + 1:] for row in (matrix[:i] + matrix[i + 1:])]

def determinant(matrix):

    if len(matrix) == 2:
        return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]

    determinant = 0
    for c in range(len(matrix)):
        determinant += ((-1) ** c) * matrix[0][c] * determinant(get_minor(matrix, 0, c))
    return determinant

def inversa(matrix):

    det = np.linalg.det(matrix)
    if det == 0:
        return None
    if len(matrix) == 2:
        return [matrix[1][1] / det, -matrix[0][1] / det,
                -matrix[1][0] / det, matrix[0][0] / det]

    adj_matrix = [[0] * len(matrix) for i in range(len(matrix))]
    transpose = transpusa(matrix)
    for i in range(len(transpose)):
        for j in range(len(transpose[0])):
            adj_matrix[i][j] = ((-1) ** (i + j)) * np.linalg.det(get_minor(transpose, i, j))
            adj_matrix[i][j] /= det
    return adj_matrix



def matrix_square_diff(matrix1, matrix2):
    """
    Function to calculate the squared difference between two matrices
    """

    if len(matrix2) != len(matrix1):
        return None
    if len(matrix1[0]) != len(matrix2[0]):
        return None

    rez = []
    for i in range(len(matrix1)):
        rez_line = []
        for j in range(len(matrix1[0])):
            rez_line.append((matrix1[i][j] - matrix2[i][j]) ** 2)
        rez.append(rez_line)
    return rez


def matrix_mean(matrix):

    if len(matrix) == 0:
        return None

    sum_elem = sum(map(sum, matrix))
    return sum_elem / len(matrix)


def normalize(x):
    return x/np.linalg.norm(x, ord=2, axis=1, keepdims=True)
