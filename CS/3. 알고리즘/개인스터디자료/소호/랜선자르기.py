K, N = map(int, input().split())
lines = [0] * K
for i in range(K):
    lines[i] = int(input())
# ========= 입력 ========= #

start = 0
end = max(lines)

result = 0
while start <= end:
    mid = (start + end) // 2
    cut_lines_cnt = 0
    if mid == 0:
        mid = 1
    for line in lines:
        cut_lines_cnt += line // mid

    if cut_lines_cnt < N:
        # 더 작게 잘라야 함
        end = mid - 1
    else:
        # N개이거나 N보다 많으면
        result = max(result, mid)
        start = mid + 1

print(result)
