'''
Author    : Kim Jae Hwan
Date      : 2024.05.18(Sat)
Runtime   : 108 ms
Memory    : 31552 KB
Algorithm : Stack
'''

# 괄호유효성 문제와 똑같다.
ans = 0
for _ in range(int(input())):
    text = input()  # AABB
    stack = []
    for c in text:  # A in AABB
        while stack and stack[-1] == c: # 현재 스택에 값이 존재하고 단어 c가 스택의 top과 같다면 pop. ex) c:A, stack:[BA] -> stack:[B]
            stack.pop()
        stack.append(c) # 현재 스택이 비었거나 현재 단어가 스택의 top과 다르다면 push
                
    # 스택이 깔끔하게 비워졌다면 좋은 단어
    if len(stack) == 0:
        ans += 1

print(ans)
