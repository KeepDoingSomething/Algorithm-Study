def solution(board):
    
    ans = 0
    
    height = len(board)
    width = len(board[0])
    
    if height == 1 and width == 1:
        return board[0][0]
    
    for y in range(height):
        
        for x in range(width):
            if board[y][x] == 0:
                continue
            elif y -1 <0 or x -1 < 0:
                continue
            else:
                sqr1 = board[y-1][x]
                sqr2 = board[y][x-1]
                sqr3 = board[y-1][x-1]
                board[y][x] = min(sqr1, min(sqr2, sqr3)) + 1
        
            ans = max(ans, board[y][x]**2)
            
    return ans
                
            