import java.io.*;
import java.util.*;


public class Main{
    static int sequence= 0;
    static ArrayList<Integer>[] nodeArray;
    static int[] answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int StartN = Integer.parseInt(st.nextToken());


        nodeArray = new ArrayList[N + 1];
        for(int i = 1 ; i< nodeArray.length; i++){
            nodeArray[i] = new ArrayList<>();
        }

        boolean[] visited = new boolean[N+1];
        answer = new int[N];

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int one = Integer.parseInt(st.nextToken());
            int two = Integer.parseInt(st.nextToken());
            nodeArray[one].add(two);
            nodeArray[two].add(one);
        }

        for(int i = 1 ; i< nodeArray.length; i++){
            Collections.sort(nodeArray[i],Collections.reverseOrder());
        }


        visited[StartN] =true;
        DFS(StartN, visited);
        StringBuilder sb = new StringBuilder();
        for(int i :answer){
            sb.append(i).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    public static void DFS(int currentNode, boolean[] visited){

        answer[currentNode-1] = ++sequence;

        for (int nextNode : nodeArray[currentNode]){
            if(visited[nextNode]){continue;}
            visited[nextNode] = true;
            DFS(nextNode,visited);
        }

    }
}