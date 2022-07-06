package baekjoon.prime_sequence_sum_1644;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static Integer[] prime(int n){
        List<Integer> prime = new ArrayList<>();

        boolean isPrime[] = new boolean[n+1];
        isPrime[0] = isPrime[1] = true;

        for (int i = 2; i * i <= n; i++) {
            if (!isPrime[i]) {
                for(int j=i+i; j<=n; j+=i) isPrime[j] = true;
            }
        }

        for (int i = 2; i <= n; i++) {
            if(!isPrime[i]) prime.add(i);
        }

        return prime.toArray(Integer[]::new);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if (n == 1) {
            System.out.println(0);
            return;
        }

        Integer[] prime = prime(n);

        int left=0, right=0;
        int sum=prime[0];
        int result=0;
        while (true) {
            if(sum==n) result++;
            if(left==prime.length-1 && right==prime.length-1) break;
            if(sum > n) {
                sum-=prime[left];
                left += 1;
            } else if(sum <= n){
                right+=1;
                sum+=prime[right];
            }
        }

        System.out.println(result);

    }
}
