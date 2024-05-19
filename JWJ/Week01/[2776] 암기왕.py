"""
Author    : Jang Woo Jin
Date      : 2024.05.19(Sun)
Runtime   : 202288 KB
Memory    : 1776 ms
Algorithm : Data Structure
"""
import sys
input = sys.stdin.readline
from collections import defaultdict

T = int(input())
for _ in range(T):
    N = int(input())
    note1 = list(map(int, input().split()))
    M = int(input())
    note2 = list(map(int, input().split()))
    dic = defaultdict(int)

    for num in note1:
        dic[num] = 1
    
    for num in note2:
        if num in dic.keys():
            print(1)
        else:
            print(0)