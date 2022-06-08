# 아기상어

#
# https://www.acmicpc.net/problem/16236

# 상어와 물고기는 크기를 가진다. 자연수
# 초기상어 2, 1초에 한칸씩
# 크거나 같은 물고기는 먹을 수없다.
# 1마리 이상이면 가까운거. 유클리드거리 / 마저 같다면 가장 위쪽 먼저 또 같다면 가장 왼쪽
# 자신의 크기만큼 먹어야 1개가 큰다.

# 시간출력
import sys
from collections import deque

dx=[0,1,0,-1]
dy=[1,0,-1,0]

N = int(input())
space = []
q = deque()
M=0

for i in range(N):
    s = list(map(int,input().split()))
    space.append(s)
    M+=len(s)-s.count(0)
    if 9 in s:
        shark_x, shark_y = i, s.index(9)
        M-=1

eat=0
cur_time=0
for _ in range(M):
    visited = [[False]*N for _ in range(N)]
    visited[shark_x][shark_y] = True
    space[shark_x][shark_y]=0
    catch = []
    q.append([shark_x, shark_y, 2, 0])
    while(q):
        x, y, size, time = q.popleft()

        for i in range(4):
            nx = x+dx[i]
            ny = y+dy[i]

            if 0<=nx<N and 0<=ny<N and space[nx][ny] <= size and not visited[nx][ny]:
                if catch and catch[0][3] < time+1: continue

                visited[nx][ny]=True
                q.append([nx, ny, size, time+1])

                if 0 < space[nx][ny] < size:
                    catch.append([nx, ny, size, time+1])

    if not catch:
        break

    eat+=1
    catch.sort(key=lambda x : (x[0],x[1]))
    shark_x, shark_y, size, cur_time = catch[0]

    if eat==size:
        size+=1
        eat=0
    q.append([shark_x, shark_y, size, cur_time])

print(cur_time)






