def solution(n):
    answer = 0

    nums = [i for i in range(n + 1)]
    head = 1
    tail = 1

    sum = 1
    while head <= n and tail <= n:
        if sum == n:
            answer += 1

        if sum < n:
            tail += 1
            sum += nums[tail]
        else:
            sum -= nums[head]
            head += 1

    return answer