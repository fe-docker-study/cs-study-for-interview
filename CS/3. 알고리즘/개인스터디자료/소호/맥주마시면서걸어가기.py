from collections import deque


def get_distance(loc1, loc2):
    return abs(loc1[0]-loc2[0])+abs(loc1[1]-loc2[1])


def bfs(s, visited):
    q = deque([s])
    visited[s] = True
    while q:
        v = q.popleft()
        for i in graph[v]:
            if not visited[i]:
                q.append(i)
                visited[i] = True


for _ in range(int(input())):
    n = int(input()) + 2
    spots = []
    for _ in range(n):
        spots.append(list(map(int, input().split())))

    graph = [[] for _ in range(n)]

    # 인접리스트 만들기
    for i in range(n):
        for j in range(n):
            if i != j and get_distance(spots[i], spots[j]) <= 1000:
                graph[i].append(j)
                graph[j].append(i)
    visited = [False] * n
    bfs(0, visited)  # 집부터 시작
    if visited[n-1]:
        print("happy")
    else:
        print("sad")
