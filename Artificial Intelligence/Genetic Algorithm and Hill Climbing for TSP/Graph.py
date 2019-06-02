class Graph:
    def __init__(self):
        self.graph = []
        self.n = 0

    def generare(self,numeFisier):
        f = open(numeFisier, "r")
        self.n = int(next(f))
        for line in f:
            array = []
            for x in line.split():
                array.append(int(x))
            self.graph.append(array)

        return self.graph

