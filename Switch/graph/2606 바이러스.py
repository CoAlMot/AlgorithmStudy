import sys
input = sys.stdin.readline

n = int(input())
m = int(input())

#map 컴퓨터 수만큼
M = {}
for i in range(n+1):
    M[i] = []


##바이러스 o,x 컴퓨터 수만큼
vi = [0] * (n+1)

##노드 입력받고 연결된 map에 넣어줌
for _ in range(m):
    a, b = map(int, input().split())
    M[a].append(b)
    M[b].append(a)


##바이러스 체크
def check(M, v, vi):
    vi[v] = 1
    for i in M[v]:
      ##바이러스가 감염되지 않은녀석이면 재귀
        if vi[i] != 1:
            check(M, i, vi)

##1부터
check(M, 1, vi)

##모든합 (1번제외)
print(sum(vi)-1)
