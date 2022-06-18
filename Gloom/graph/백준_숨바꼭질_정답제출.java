import java.io.*;
import java.util.*;
// BFS

public class Main {
    static int K  ;
    static int N  ;

    static int[] visited = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //수빈 위치
        K = Integer.parseInt(st.nextToken()); //동생 위치


        Queue<Integer> locationQue = new LinkedList<>();
        locationQue.add(N);
        visited[N] = 1;
        int curLocation ;


        while (!locationQue.isEmpty()){
            curLocation = locationQue.poll();


            // 목표 도달
            if(curLocation == K){

                break;
            }
            //방문했던 곳인지 파악하면서 3가지 경우 적용하기
            if((curLocation-1) >= 0 && visited[curLocation- 1] == 0 ) {
                locationQue.add(curLocation - 1);
                visited[curLocation - 1] = visited[curLocation] + 1;
            }
            if((curLocation +1) < 100001 && visited[curLocation + 1] == 0 ) {
                locationQue.add(curLocation + 1);
                visited[curLocation + 1] = visited[curLocation] + 1;
            }
            if((curLocation * 2) < 100001 && visited[curLocation * 2 ] == 0) {
                locationQue.add(curLocation * 2);
                visited[curLocation * 2] = visited[curLocation] + 1;
            }
        }
        System.out.println(visited[K]- 1);
        br.close();
    }
}