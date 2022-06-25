import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    // 부모노드 얻기
    public static int getParent(int[] parent, int x) {
        if (parent[x] == x) return x;
        return parent[x] = getParent(parent, parent[x]);
    }

    // 두 부모 노드 합치기
    public static void unionParent(int[] parent, int x, int y) {
        x = getParent(parent, x);
        y = getParent(parent, y);
        if (x > y) parent[x] = y;
        else parent[y] = x;
    }

    // 부모가 같은지 확인 -> 즉, 같이 연결되어 있는지 확인
    public static boolean findParent(int[] parent, int x, int y) {
        x = getParent(parent, x);
        y = getParent(parent, y);
        return x == y;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken()); //정점의 갯수
        int E = Integer.parseInt(st.nextToken()); //간선의 갯수
        Edge[] arr = new Edge[E];
        int n1;
        int n2;
        int distance;
        for(int i =0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            n1 = Integer.parseInt(st.nextToken());
            n2 = Integer.parseInt(st.nextToken());
            distance = Integer.parseInt(st.nextToken());

            arr[i] = new Edge(n1,n2,distance);
        }



        // 오름차순으로 정렬
        Arrays.sort(arr);
//        for(Edge e: arr) {
//            System.out.println(e.getDistance()); // 값확인
//        }
        // 각 정점이 포함된 그래프가 어디인지 저장
        int[] parent = new int[V + 1];
        for (int i = 1; i < parent.length; i++) {
            parent[i] = i; // 모든 부모노드 초기화
        }
        int sum = 0;

        // arr 를 순회하면서, 사이클이 도는지만 체크
        for (int i = 0; i < arr.length; i++) {
            if (!findParent(parent, arr[i].getNode()[0], arr[i].getNode()[1])) { //사이클이 아니라면
                sum += arr[i].getDistance();
                unionParent(parent, arr[i].getNode()[0], arr[i].getNode()[1]);
            }

        }
        System.out.println(sum);
    }
}

class Edge implements Comparable<Edge> {
    private int[] node = new int[2];
    private int distance;

    public Edge(int node1, int node2, int distance) {
        this.node[0] = node1;
        this.node[1] = node2;
        this.distance = distance;
    }

    public int[] getNode() {
        return node;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public int compareTo(Edge o) {
        return this.distance - o.distance;
    }
}

