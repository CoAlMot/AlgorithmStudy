package baekjoon.sort_card_1715;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        // 최소값을 뽑아내기 위한 최소힙
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
             pq.offer(Integer.valueOf(br.readLine()));
        }

        // 누적합을 저장하는 변수
        Integer result = 0;
        while (pq.size()>1) {
            // 저장된 최소값 두개를 뽑아서 다음 값으로 지정
            int next = pq.poll() + pq.poll();
            // 결과에 누적해서 더한다.
            result += next;
            // 구해진 다음 값을 다시 최소힙에 삽입
            pq.offer(next);
        }

        System.out.println(result);
    }
}
