import java.io.*;
import java.util.*;

public class Main {
    static int sequence= 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int StartN = Integer.parseInt(st.nextToken());

        // 간선들 받아오기
        ArrayList<Integer>[] nodeArray = new ArrayList[N + 1];
        for(int i = 0 ; i< nodeArray.length; i++){
            nodeArray[i] = new ArrayList<>();
        }

        boolean[] visited = new boolean[N+1];
        int[] answer = new int[N];

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int one = Integer.parseInt(st.nextToken());
            int two = Integer.parseInt(st.nextToken());
            nodeArray[one].add(two);
            nodeArray[two].add(one);
        }

        for(int i = 0 ; i< nodeArray.length; i++){
            Collections.sort(nodeArray[i]);
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

        for (int nextNode : nodeArray[currentNode]){  // 애초에 저장할때, 작은 값부터 넣기
            if(visited[nextNode]){continue;} // 이미 방문한 곳이다.
            visited[nextNode] = true;
            DFS(nextNode, nodeArray,visited, answer);
        }

    }
}