n = 0
graph = []
mini = 10000

path = []
final_path = []
used = []


def citire():
    global n, graph
    f = open("date.in", "r")
    n = int(next(f))
    for line in f:
       array = []
       for x in line.split():
           array.append(float(x))
       graph.append(array)


def initializare():
    for i in range(0,n):
        path.append(0)
        used.append(0)
        final_path.append(0)


def tsp_dfs(start, cost, poz):
    global mini
    used[start] = 1
    path[poz] = start+1

    #daca toate au fost vizitate
    vizitate = 1
    for i in range(0,n):
        if used[i] == 0:
            vizitate = 0

    if vizitate == 1:
        cost = cost + graph[start][0]
        if cost < mini:
            mini = cost
            for i in range(0,n):
                final_path[i] = path[i]


    for i in range(0,n):
        if used[i] == 0:
            cost = cost + graph[i][start]
            tsp_dfs(i, cost, poz+1)
            used[i] = 0
            cost = cost - graph[i][start]


def main_dfs():
    citire()
    initializare()

    start =0
    cost = 0
    poz = 0
    tsp_dfs(start, cost, poz)

    file = open("date.out", "w")
    file.write(str(n))
    file.write("\n")
    for nr in final_path:
        file.write(str(nr) + " ")
    file.write("\n")
    file.write(str(mini))


main_dfs()


