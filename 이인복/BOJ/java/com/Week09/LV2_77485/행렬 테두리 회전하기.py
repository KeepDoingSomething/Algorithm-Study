def solution(rows, columns, queries):
    maps = [[i + (j * columns) for i in range(1, columns + 1)] for j in range(rows)]
    ans = []
    
    for x1, y1, x2, y2 in queries:
        px, py = (x1 - 1), (y1 - 1)
        num = maps[px][py]
        copied_map = [row.copy() for row in maps]
        move_x, move_y = (x2 - x1), (y2 - y1)
        mins = num

        for _ in range(move_y):
            py += 1
            maps[px][py] = num
            num = copied_map[px][py]
            mins = min(num, mins)

        for _ in range(move_x):
            px += 1
            maps[px][py] = num
            num = copied_map[px][py]
            mins = min(num, mins)

        for _ in range(move_y):
            py -= 1
            maps[px][py] = num
            num = copied_map[px][py]
            mins = min(num, mins)

        for _ in range(move_x):
            px -= 1
            maps[px][py] = num
            num = copied_map[px][py]
            mins = min(num, mins)

        ans.append(mins)
    
    return ans

print(solution(6, 6, [[2,2,5,4],[3,3,6,6],[5,1,6,3]]))