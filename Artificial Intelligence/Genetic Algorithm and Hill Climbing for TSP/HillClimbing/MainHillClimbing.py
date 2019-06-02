import random

from TSP_2.Graph import Graph
from TSP_2.HillClimbing.Initializare import initializareFunctii


def all_pairs(size):
    r1 = list(range(1,size+1))
    r2 = list(range(1,size+1))
    random.shuffle(r1)
    random.shuffle(r2)
    for i in r1:
        for j in r2:
            yield (i,j)


def reversed_parts(tour):
    for i, j in all_pairs(len(tour)):
        if i != j:
            copy = tour[:]
            if i < j:
                copy[i:j + 1] = reversed(tour[i:j + 1])
            else:
                copy[i + 1:] = reversed(tour[:j])
                copy[:j] = reversed(tour[i + 1:])
            if copy != tour:
                yield copy


def hillclimb(maxIterations,funct):
    best = funct.init_random_tour()
    best_score = funct.objective_function(best)

    nriterations = 1
    while nriterations < maxIterations:
        move = False
        for next in reversed_parts(best):
            if nriterations >= maxIterations:
                break
            nriterations += 1
            next_score = funct.objective_function(next)
            if next_score > best_score:
                best = next
                best_score = next_score
                move = True
                break

        if not move:
            break   #we couldn't find a better move

    return (nriterations,best_score,best)





def main():
    g = Graph()
    matrix = g.generare("date1.in")
    funct = initializareFunctii(matrix,g.n)
    nriterations,best_score, best = hillclimb(10,funct)
    print(best)
    print(-best_score)










main()