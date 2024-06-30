def solution(k, dungeons):
    ans = 0
    max_cnt = len(dungeons)
    visited = [False] * max_cnt
    
    def backtracking(stamina, cnt):
        nonlocal ans
        ans = max(ans, cnt)

        if ans == max_cnt:
            return ans

        for idx in range(max_cnt):
            if not visited[idx] and stamina >= dungeons[idx][0]:
                visited[idx] = True
                backtracking(stamina - dungeons[idx][1], cnt + 1)
                visited[idx] = False

    backtracking(k, 0)

    return ans

print(solution(80, [[80,20],[50,40],[30,10]]))