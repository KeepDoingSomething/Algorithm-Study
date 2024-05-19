'''
Author    : Kim Jae Hwan
Date      : 2024.05.18(Sat)
Runtime   : 2104 ms
Memory    : 34016 KB
Algorithm : Queue
'''

# 할 일: 내 차례가 되었을때 제거되는 것. 
# 내 차례가 아니라면 큐의 뒤로 가서 차례가 오길 기다린다.
from collections import deque

n, k = map(int, input().split())
q = deque()
answer = []

# 1~n까지 큐에 넣기
# [1 2 3 4 5 6 7]
for i in range(1, n+1):
    q.append(i)

while len(q) > 0:
    # 제거할 차례(k)가 아니면 큐의 맨뒤로
    # [2 3 4 5 6 7 1]
    # [3 4 5 6 7 1 2]
    for i in range(1, k):   # 1 ~ k-1
        q.append(q.popleft())

    # 제거할 차례(k)면 
    answer.append(str(q.popleft()))

print("<{}>".format(', '.join(answer)))
