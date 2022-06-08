# 아기상어

# BFS 풀이
# https://www.acmicpc.net/problem/16236

# 상어와 물고기는 자연수 크기를 가진다.
# 처음 상어의 크기는 2, 1초에 한칸씩 이동할 수 있다.
# 크거나 같은 물고기는 먹을 수없다. 다만 같은 크기의 물고기는 밟고 이동할 수 있다.
# 먹을 수 있는 물고기가 1마리 이상이면 가까운 것(유클리드거리) / 거리 마저 같다면 가장 위쪽 먼저, 또 같다면 가장 왼쪽 물고기를 먹는다.
# 자신의 크기만큼 먹어야 상어의 크기가 1 큰다.

from collections import deque

dx=[0,1,0,-1]
dy=[1,0,-1,0]

# 공간의 크기
N = int(input())
space = []

# BFS 진행을 위한 deque
q = deque()
# 물고기의 수
M=0

# 1. 반복문을 돌며 물고기의 수와 아기상어의 위치를 찾는다.
for i in range(N):
    s = list(map(int,input().split()))
    space.append(s)
    M+=len(s)-s.count(0)
    if 9 in s:
        shark_x, shark_y = i, s.index(9)
        M-=1

# 먹은 물고기의 개수를 저장하는 변수
eat=0
# 이동한 시간을 저장하는 변수
cur_time=0

# 2. 물고기의 수만큼 BFS를 반복
for _ in range(M):
    # 방문 여부를 담은 리스트 visited
    visited = [[False]*N for _ in range(N)]
    visited[shark_x][shark_y] = True

    # 3. 상어의 위치를 0으로 초기화
    space[shark_x][shark_y]=0
    # 먹은 물고기 리스트 초기화
    catch = []
    # 상어의 위치를 담은 deque 생성
    q.append([shark_x, shark_y, 2, 0])

    # 4. BFS를 시작
    while(q):
        x, y, size, time = q.popleft()

        # 상,하,좌,우로 이동하면서 확인
        for i in range(4):
            nx = x+dx[i]
            ny = y+dy[i]

            # 5. 상어의 크기보다 새 위치의 물고기의 크기가 같거나 작고, 방문하지 않았다면 실행
            if 0<=nx<N and 0<=ny<N and space[nx][ny] <= size and not visited[nx][ny]:

                # 6. 현재 물고기를 먹기까지의 이동시간보다 / 조건에 맞춰 새로 발견된 물고기의 이동시간이 길다면 확인할 필요가 없다 (이동거리가 길기에)
                if catch and catch[0][3] < time+1: continue

                visited[nx][ny]=True
                q.append([nx, ny, size, time+1])

                # 7. 물고기가 존재하고 (0이 아니다), 상어의 크기보다 물고기가 작다면 먹은 물고기 리스트에 추가
                if 0 < space[nx][ny] < size:
                    catch.append([nx, ny, size, time+1])

    # 8. 하나도 먹은 물고기가 없다면 반복을 끝낸다. (엄마상어의 도움)
    if not catch:
        break

    # 9. 먹은 물고기 추가
    eat+=1
    # 10. 같은 조건이라면 가장 위(x축), 가장 왼쪽(y축)으로 정렬하여 뽑는다.
    catch.sort(key=lambda x : (x[0],x[1]))
    shark_x, shark_y, size, cur_time = catch[0]

    # 11. 현재 먹은 물고기의 수와 상어의 크기가 같다면 상어의 크기가 1증가하며, 먹은 물고기를 초기화한다.
    if eat==size:
        size+=1
        eat=0

    # 12. 현재 상어가 먹은 물고기의 위치부터 다시 BFS를 시작한다.
    q.append([shark_x, shark_y, size, cur_time])

print(cur_time)