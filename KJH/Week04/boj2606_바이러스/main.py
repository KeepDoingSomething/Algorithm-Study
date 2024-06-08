from itertools import permutations

def solution(n):
    prime = set()
    # 1. 모든 숫자 조합을 만든다
    for i in range(len(n)):                                                  
        prime |= set(map(int, map("".join, permutations(list(n), i + 1))))
    # 2. 소수가 아닌 수를 제외한다.
    prime -= set(range(0, 2))
    for i in range(2, int(max(prime) ** 0.5) + 1):
        prime -= set(range(i * 2, max(prime) + 1, i))
    return len(prime)