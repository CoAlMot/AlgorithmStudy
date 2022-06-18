# 문제 구성 📖
> 코딩테스트 사이트 : 백준  
> 난이도 : 실버2     
> 풀이 날짜 : 2022.06.04  
> 사용한 풀이 방법 : DFS

## 문제링크
https://www.acmicpc.net/problem/24479

## 풀이법

```java
    //깊이 우선탐색 구현하기, 오름차순으로 방문
// N개의 정점과 M개의 간선으로 구성된 무방향(양쪽다됨) 그래프
// 주어지는 조건
// 첫째줄
// 1= 정점의 수 (최대 10만)
// 2= 간선의 수 (최대 20만)
// 3= 시작 정점

// 2번째 줄부터 간선의 수 만큼
// 1 = 출발 정점
// 2=  도착 정점

// 방문 체크 배열
// 순서 담을 배열 

// 무방향이라 양쪽으로 추가

// 각 배열을 오름차순으로 정렬

// 낮은 번호부터 순환하여, 시작 정점부터 도착하게되는 순서대로 출력
// 도달할수 있는지를 파악하고, 해당 값을 대입

public class Main{
    static int sequence= 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //Node
        int E = Integer.parseInt(st.nextToken()); // Edge
        int StartN = Integer.parseInt(st.nextToken()); // StartNode

        // 간선들 받아오기
        ArrayList<Integer>[] nodeArray = new ArrayList[N + 1];
        for(int i = 1 ; i< nodeArray.length; i++){
            nodeArray[i] = new ArrayList<>();
        }

        boolean[] visited = new boolean[N+1];
        int[] answer = new int[N];

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int one = Integer.parseInt(st.nextToken());
            int two = Integer.parseInt(st.nextToken());
            nodeArray[one].add(two); // 무방향이기에
            nodeArray[two].add(one); // 양쪽 방향값을 다 배열에 넣어줌
        }

        for(int i = 1 ; i< nodeArray.length; i++){
            Collections.sort(nodeArray[i],Collections.reverseOrder());
        }

        // start -> 전체 순환
        visited[StartN] =true;
        DFS(StartN, nodeArray, visited, answer);

        for(int i :answer){
            System.out.println(i);
        }


    }

    public static void DFS(int currentNode, ArrayList<Integer>[] nodeArray, boolean[] visited, int[] answer){
        //탈출조건 1. 더이상 갈곳이 없다.
        //       2. 이미 방문한 곳이다.

        answer[currentNode-1] = ++sequence;

        for (int nextNode : nodeArray[currentNode]){  // 애초에 저장할때, 큰 값부터 넣기
            if(visited[nextNode]){continue;} // 이미 방문한 곳이다.
            visited[nextNode] = true;
            DFS(nextNode, nodeArray,visited, answer);
        }

    }
}
```
