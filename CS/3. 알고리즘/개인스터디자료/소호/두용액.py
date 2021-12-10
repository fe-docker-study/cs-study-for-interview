N = int(input())

liquids = list(map(int, input().split()))
liquids.sort()

compare = 2000000000
p1 = 0
p2 = N-1
result = []

while p1 < p2:
    mix = liquids[p1] + liquids[p2]
    if abs(mix) < compare:
        compare = abs(mix)
        result = liquids[p1], liquids[p2]

    if mix == 0:
        break
    if mix > 0:
        p2 -= 1
    if mix < 0:
        p1 += 1

print(' '.join(list(map(str, result))))
