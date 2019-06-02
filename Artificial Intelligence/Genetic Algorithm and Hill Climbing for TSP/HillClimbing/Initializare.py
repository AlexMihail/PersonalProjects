import random


class initializareFunctii:
    def __init__(self,matrix,nrTowns):
        self.matrix = matrix
        self.nrTowns = nrTowns

    def init_random_tour(self):
        tour = list(range(1,self.nrTowns + 1))
        random.shuffle(tour)
        return tour

    def tour_length(self,tour):
        total = 0
        nr_towns = len(tour)
        for i in range(0,nr_towns):
            j = (i+1) % nr_towns
            town1 = tour[i]
            town2 = tour[j]
            total += self.matrix[town1-1][town2-1]
        return total

    def objective_function(self,tour):  #that will tell us how good a solution is
        return -self.tour_length(tour)