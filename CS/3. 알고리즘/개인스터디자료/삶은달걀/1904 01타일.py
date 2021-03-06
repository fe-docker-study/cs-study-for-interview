# N = 0 => 00(X)
# N = 1 => 001, 00(1)
# N = 2 => 0011, 0000, 00(2)
# N = 3 => 00111, 001, 0000, 000000, 00100, 00001(3)
n = int(input())
dp = [0] * 1000001
dp[1] = 1
dp[2] = 2
for i in range(3, n+1):
    dp[i] = (dp[i-1] + dp[i-2])%15746
print(dp[n])