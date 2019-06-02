class Fitness:
    def __init__(self,tour,matrix):
        self.tour = tour
        self.matrix = matrix
        self.distance = 0
        self.fitness = 0

    def tour_length(self):
        total = 0
        nr_towns = len(self.tour)
        for i in range(0, nr_towns):
            j = (i + 1) % nr_towns
            town1 = self.tour[i]
            town2 = self.tour[j]
            total += self.matrix[town1 - 1][town2 - 1]
        return total


    def routeFitness(self):
        if self.fitness == 0:
            self.fitness = - self.tour_length()
        return self.fitness
