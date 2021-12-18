# 토마토
from collections import deque

M, N = map(int, input().split())
graph = [[] for _ in range(N)]
for i in range(N):
    graph[i] = list(map(int, input().split()))

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]
q = deque([])


def bfs():
    while q:
        x, y = q.popleft()
        for i in range(4):
            nx = x+dx[i]
            ny = y+dy[i]
            # 안 익은 토마토이면
            if 0 <= nx < N and 0 <= ny < M and graph[nx][ny] == 0:
                q.append([nx, ny])
                graph[nx][ny] = graph[x][y] + 1  # 옮은 토마토 자리보다 1씩 큰 값을 저장


for x in range(N):
    for y in range(M):
        # 익은 토마토에서 bfs 시작
        if graph[x][y] == 1:
            q.append([x, y])

bfs()

days = 0
for line in graph:
    for tomato in line:
        if tomato == 0:
            print(-1)
            exit(0)
    days = max(days, max(line))

print(days-1)
