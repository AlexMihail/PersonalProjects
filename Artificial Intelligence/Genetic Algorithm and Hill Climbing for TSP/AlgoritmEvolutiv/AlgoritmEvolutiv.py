import random, operator
import numpy as np
import  pandas as pd, matplotlib.pyplot as plt


from TSP_2.AlgoritmEvolutiv.Fitness import Fitness
from TSP_2.Graph import Graph


def init_random_tour(nrTowns):
    tour = list(range(1,nrTowns + 1))
    random.shuffle(tour)
    return tour

def initialPopulation(popSize,nrTowns):
    population = []

    for i in range(0,popSize):
        population.append(init_random_tour(nrTowns))

    return population


def rankRoutes(population,matrix):
    fitnessResults = {}
    for i in range(0, len(population)):
        f = Fitness(population[i], matrix)
        fitnessResults[i] = f.routeFitness()

    return sorted(fitnessResults.items(), key=operator.itemgetter(1), reverse=True)


def selection(popRanked,eliteSize):
    selectionResult = []
    df = pd.DataFrame(np.array(popRanked), columns=["Index","Fitness"])
    df['cum_sum'] = df.Fitness.cumsum()
    df['cum_perc'] = 100*df.cum_sum/df.Fitness.sum()

    for i in range(0, eliteSize):
        selectionResult.append(popRanked[i][0])
    for i in range(0, len(popRanked) - eliteSize):
        pick = 100 * random.random()
        for i in range(0, len(popRanked)):
            if pick <= df.iat[i, 3]:
                selectionResult.append(popRanked[i][0])
                break
    return selectionResult


def matingPool(population, selectionResult):
    mating_pool = []
    for i in range(0, len(selectionResult)):
        index = selectionResult[i]
        mating_pool.append(population[index])
    return mating_pool


def breed(parent1, parent2):
    child = []
    child1 = []
    child2 = []

    geneA = int(random.random() * len(parent1))
    geneB = int(random.random() * len(parent1))

    startGene = min(geneA, geneB)
    endGene = max(geneA, geneB)

    for i in range(startGene, endGene):
        child1.append(parent1[i])

    child2 = [item for item in parent2 if item not in child1]

    child = child1 + child2
    return child


def breedPopulation(matingpool, eliteSize):
    children = []
    length = len(matingpool) - eliteSize
    pool = random.sample(matingpool, len(matingpool))

    for i in range(0, eliteSize):
        children.append(matingpool[i])

    for i in range(0, length):
        child = breed(pool[i], pool[len(matingpool) - i - 1])
        children.append(child)
    return children


def mutate(individual, mutationRate):
    for swapped in range(len(individual)):
        if random.random() < mutationRate:
            swapWith = int(random.random() * len(individual))

            city1 = individual[swapped]
            city2 = individual[swapWith]

            individual[swapped] = city2
            individual[swapWith] = city1
    return individual


def mutatePopulation(population, mutationRate):
    mutatedPopulation = []

    for ind in range(0, len(population)):
        mutatedInd = mutate(population[ind], mutationRate)
        mutatedPopulation.append(mutatedInd)
    return mutatedPopulation


def nextGeneration(currentGen, eliteSize, mutationRate,matrix):
    popRanked = rankRoutes(currentGen,matrix)
    selectionResults = selection(popRanked, eliteSize)
    matingpool = matingPool(currentGen, selectionResults)
    children = breedPopulation(matingpool, eliteSize)
    nextGeneration = mutatePopulation(children, mutationRate)
    return nextGeneration


def genetic_algorithm(nrTowns, popSize, eliteSize, mutationRate, generations,matrix):
    pop = initialPopulation(popSize, nrTowns)
    print("Initial distance: " + str(- rankRoutes(pop,matrix)[0][1]))
    print(pop)
    print("\n")

    for i in range(0, generations):
        pop = nextGeneration(pop, eliteSize, mutationRate,matrix)

    print("Final distance: " + str(- rankRoutes(pop,matrix)[0][1]))
    print(pop)
    bestRouteIndex = rankRoutes(pop,matrix)[0][0]
    bestRoute = pop[bestRouteIndex]
    return bestRoute


def geneticAlgorithmPlot(nrTowns, popSize, eliteSize, mutationRate, generations,matrix):
    pop = initialPopulation(popSize, nrTowns)
    progress = []
    progress.append(- rankRoutes(pop,matrix)[0][1])

    for i in range(0, generations):
        pop = nextGeneration(pop, eliteSize, mutationRate,matrix)
        progress.append(- rankRoutes(pop,matrix)[0][1])

    plt.plot(progress)

    plt.ylabel('Distance')
    plt.xlabel('Generation')
    plt.show()

def main():
    g = Graph()
    matrix = g.generare("date1.in")
    geneticAlgorithmPlot(nrTowns=g.n, popSize=100, eliteSize=20, mutationRate=0.01, generations=500, matrix=matrix)

main()

