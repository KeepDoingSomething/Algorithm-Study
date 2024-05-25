# 입력
N = int(input())
switches = list(map(int , input().split(" ")))
students = int(input())
S = []
for _ in range(students):
    S.append(tuple(map(int, input().split(" "))))

for s in S:
    # 남자일때
    if s[0] == 1:
        n = N // s[1]
        for i in range(1, n+1):
            idx = s[1] * i -1
            switches[idx] = 1 - switches[idx]
        
    # 여자일때
    else:
        idx = s[1] - 1
        switches[idx] = 1 - switches[idx]
        left = idx -1
        right = idx + 1        
        while left >= 0 and right < len(switches) and switches[left] == switches[right]:
            switches[left] = 1 - switches[left]
            switches[right] = 1 - switches[right]
            left -= 1
            right += 1

for i in range(N):
    print(switches[i], end=" ")
    if (i+1) % 20 == 0:
        print()