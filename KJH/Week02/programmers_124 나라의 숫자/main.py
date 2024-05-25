'''
Author    : Kim Jae Hwan
Date      : 2024.05.25(Sun)
'''

def solution(n):
    x=n-1
    answer=''
    while True:
        x,y=divmod(x,3)
        if y==0:
            answer+='1'
        elif y==1:
            answer+='2'
        else:
            answer+='4'
        if x==0:
            break
        x=x-1
    return answer[::-1]