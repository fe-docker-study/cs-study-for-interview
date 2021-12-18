from collections import deque


def get_distance(loc1, loc2):
    return abs(loc1[0]-loc2[0])+abs(loc1[1]-loc2[1])


def bfs():
    q = deque(spots[0])  # 집부터 시작


t = int(input())
for _ in range(t):
    n = int(input()) + 2
    spots = []
    for _ in range(n):
        spots.append(list(map(int, input().split())))

    graph = [[] for _ in range(n)]

    for i in range(n-1):
        for j in range(i, n):
            if i == j:
                continue
            d = get_distance(spots[i], spots[j])
            if d > 1000:
                continue
            graph[i].append(j)
            graph[j].append(i)
