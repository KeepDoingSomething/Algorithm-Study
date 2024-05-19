'''
Author    : Kim Jae Hwan
Date      : 2024.05.18(Sat)
Runtime   : 1624 ms
Memory    : 183136 KB
Algorithm : Hash
'''

# Set를 사용하여 시간 복잡도를 O(N)으로, N <= 1,000,000 이므로 리스트를 사용한 O(N^2)은 위험하다
for _ in range(int(input())):
    N = int(input())
    note1 = set(map(int, input().split()))  # Set 사용
    M = int(input())
    note2 = list(map(int, input().split()))

    for i in note2:
        if i in note1:
            print(1)
        else:
            print(0)