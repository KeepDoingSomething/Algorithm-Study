def solution(n):
    nums = ['1', '2', '4']
    answer = ""
    
    while n > 0:
        n -= 1
        answer = nums[n%3] + answer
        n //= 3
        
    return answer