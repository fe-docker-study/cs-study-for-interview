N, C = map(int, input().split())
houses = [0] * N
for i in range(N):
    houses[i] = int(input())
# ======== 입력 =========
houses.sort()

start = 1
end = houses[N-1] - houses[0]  # 최대 범위


def possible(dist):
    cnt = 0
    prev = houses[0]
    for i in range(1, N):
        if houses[i] - prev >= dist:
            cnt += 1
            prev = houses[i]
    cnt += 1  # 처음 집 추가
    if C <= cnt:
        return True
    return False


result = 0

while start <= end:
    mid = (start + end) // 2
    if possible(mid):
        result = max(mid, result)
        start = mid + 1
    else:
        end = mid - 1

print(result)
