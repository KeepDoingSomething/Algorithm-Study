from typing import List

nums = set()

def dfs(numbers: str, comb: str, visited: List[bool]) -> None:
    if comb:
      nums.add(int(comb))

    for i in range(len(numbers)):
        if not visited[i]:
            visited[i] = True
            dfs(numbers, comb + numbers[i], visited)
            visited[i] = False

def solution(numbers: str) -> int:
    visited = [False] * len(numbers)
    cnt = 0

    dfs(numbers, '', visited)

    for num in nums:
        if is_prime(num):
            cnt += 1
    
    return cnt

def is_prime(number: int):
    if number in (0, 1):
        return False

    sqrt = int(number ** 0.5) + 1
    
    for i in range(2, sqrt):
        if number % i == 0:
            return False
    
    return True

print(solution('011'))