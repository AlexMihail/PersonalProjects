n = 0
graph = []
path = []
used = []
cost = 0


def citire():
    global n, graph
    f = open("date.in", "r")
    n = int(next(f))
    for line in f:
       array = []
       for x in line.split():
           array.append(int(x))
       graph.append(array)

def initializare():
    for i in range(0,n):
        used.append(0)
        path.append(0)

def writeF():
    file = open("date1.out", "w")
    file.write(str(n))
    file.write("\n")
    for nr in path:
        file.write(str(nr+1) + " ")
    file.write("\n")
    file.write(str(cost))

def tsp_greedy(poz):
    global cost
    mini = 10000
    imin = 0
    for i in range(0,n):
        if used[i] == 0 and graph[path[poz-1]][i] < mini and i!=path[poz-1]:
            mini = graph[path[poz-1]][i]
            imin = i
    path[poz] = imin
    used[imin] = 1
    cost = cost + graph[path[poz-1]][imin]
    if poz == (n-1):
        cost = cost + graph[imin][0]
        writeF()
    else:
        tsp_greedy(poz+1)



def main_greedy():
    citire()
    initializare()
    used[0] = 1
    path[0] = 0
    tsp_greedy(1)


main_greedy()