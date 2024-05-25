# 시작점과의 거리 비교
def Distance(x, y):
    return max(abs(startX - x), abs(startY - y))

# 원이 끝나는 지점. 9, 25, 49 ...
def GetNumber(n):
    return (2*n + 1)**2

def PrintBoard():
    for i in range(len(board[0])):
        print(' '.join(map(str, board[i])))

N = int(input())
K = int(input())
dirList = [[0,-1], [1,0], [0,1],[-1,0]]
dir = 0

board = [[0 for x in range(N)] for y in range(N)]

# 현재 그리고 있는 원이 몇번째인지
# 원은 시작점과의 거리를 통해 다음 위치의 직진 가능여부를 판별하는데 사용됨
circleCnt = 1

# 중앙 시작점에서 출발
startX, startY = N//2, N//2
board[startX][startY] = 1

# 현재 위치 now
nowX, nowY = startX, startY

for cnt in range(2, N*N + 1):

    # 직진할 다음 위치 next
    nextX = nowX + dirList[dir][0]
    nextY = nowY + dirList[dir][1]

    # 직진으로 갈 수 없으면 우회전하여 방향 수정
    if nextX < 0 or nextY < 0 or nextX >= N or nextY >= N\
          or Distance(nextX, nextY) > circleCnt:
        dir = (dir + 1) % 4
        nextX = nowX + dirList[dir][0]
        nextY = nowY + dirList[dir][1]
    
    # 다음 위치로 이동
    board[nextY][nextX] = cnt
    nowX = nextX
    nowY = nextY

    # 현재 위치가 9, 25, 49... 일 때 circle이 끝나고 새로운 원을 그리기 시작
    if cnt == GetNumber(circleCnt):
        circleCnt += 1

PrintBoard()

# board[y][x] 로 구현했으므로 마지막에 x 와 y를 바꿔서 출력
for y in range(N):
    for x in range(N):
        if board[y][x] == K:
            print(y+1, x+1)
