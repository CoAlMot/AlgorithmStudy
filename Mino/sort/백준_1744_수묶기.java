package baekjoon.group_number_1744;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Integer[] arr = new Integer[n];
        // 양수가 아닌 수의 개수
        int negative = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            if (arr[i] <= 0) negative++;
        }

        // 오름차순으로 정렬
        Arrays.sort(arr);

        // 결과를 담을 변수
        int result = 0;

        // 1. 양수일 경우, 가장 큰 두 수의 곱이 최대값이 되므로 맨 뒤의 값부터 처리한다.
        for (int idx = arr.length - 1; idx >= negative; idx--) {
            // 단, 1의 경우 곱하는 것보다 더하는 것이 큰 값을 만들어내므로 예외적으로 처리한다.
            if (idx == negative || arr[idx] == 1 || arr[idx - 1] == 1) result += arr[idx];
            else result += arr[idx - 1] * arr[idx--];
        }

        // 2. 음수일 경우, 가장 작은 두 수의 곱이 최대값이 되므로 맨 앞의 값부터 처리한다.
        for (int idx = 0; idx < negative; idx++) {
            if (idx == negative - 1) result += arr[idx];
            else result += arr[idx + 1] * arr[idx++];
        }

        System.out.println(result);
    }
}
