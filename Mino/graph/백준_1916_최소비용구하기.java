package baekjoon.find_minimum_cost_1916;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * Dijkstra 알고리즘을 이용한 풀이
 * 최단거리 알고리즘인 Dijstra 알고리즘은 BFS를 근간으로 한다.
 */

// 인접리스트 구현을 위해 도착정보와 간선의 가중치를 저장하는 Vertex 클래스
class Vertex implements Comparable<Vertex>{
    int v;
    int total;

    public Vertex(int v, int total) {
        this.v = v;
        this.total = total;
    }

    @Override
    public int compareTo(Vertex o) {
        return total-o.total;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());


        // 시작정점(start)로 부터 각 인덱스에 해당하는 정점까지의 최소거리를 저장하는 리스트
        int[] min_dist = new int[n+1];
        // 초기값은 무수히 큰 값으로 초기화한다.
        Arrays.fill(min_dist, Integer.MAX_VALUE);

        // 인접리스트
        ArrayList<ArrayList<Vertex>> adj = new ArrayList<>();
        for (int i = 0; i < m+1; i++) {
            // 해당 정점에서 나가는 정점이 없을 수도 있으니 필요할 때 객체를 할당하는 방법도 있다.
            adj.add(new ArrayList<>());
        }

        // 출발정점(u) 에서 도착정점(v)까지 가는 버스의 비용(cost)을 인접리스트에 저장한다.
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adj.get(u).add(new Vertex(v, cost));
        }

        // 문제에서 정답을 요구하는(최소 거리를 구하고 싶은) 출발정점으로부터 도착정점을 받는다.
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        System.out.println(dijkstra(n, min_dist, adj, start, end));

    }

    public static int dijkstra(int n, int[] min_dist, ArrayList<ArrayList<Vertex>> adj, int start, int end) {
        // Queue를 사용할 수 있으나 가장 짧은 거리를 구하기 위한 정렬이 항상 이루어져야 하므로 우선순위 큐를 사용하였다.
        PriorityQueue<Vertex> hq = new PriorityQueue<>();
        // BFS에서 처럼 방문표시를 할 visited 리스트
        boolean[] visited = new boolean[n+1];

        // 여기서의 Vertex의 저장값은 문제에서 요구한 시작정점부터 해당 정점까지의 비용을 저장한다.
        hq.offer(new Vertex(start, 0));
        min_dist[start]=0;

        while (!hq.isEmpty()) {
            Vertex poll = hq.poll();
            int u = poll.v;
            int total = poll.total;

            if (u == end) {
                return total;
            }

            // 방문하지 않은 정점이라면
            if (!visited[u]) {
                visited[u] = true;
                // 해당 정점과 연결된 정점들에 대하여
                for (Vertex vertex : adj.get(u)) {
                    //
                    int new_total = total + vertex.total;
                    // start<->u 의 cost 최소값 + u<->v의 cost 이 여태까지 저장된 start <-> v 의 최소값보다 작다면 갱신 후 우선순위 큐에 추가.
                    if (new_total < min_dist[vertex.v]) {
                        min_dist[vertex.v] = new_total;
                        hq.offer(new Vertex(vertex.v, new_total));
                    }
                }
            }
        }

        return min_dist[end];
    }
}
