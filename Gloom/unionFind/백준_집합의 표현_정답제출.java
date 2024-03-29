import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] parent = new int[N+1];

        for(int i =0 ; i< parent.length; i++){
            parent[i] = i;
        }

        int order;
        int x;
        int y;
        StringBuilder sb =new StringBuilder();

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            order = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            if(order == 0){
                unionParent(parent, x,y);
            }

            if(order == 1){
                if(findParent(parent,x,y)) sb.append("YES");
                else sb.append("NO");
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
        br.close();


    }
    public static int getParent(int[] parent, int x){
        if(parent[x] == x) return x;
        return parent[x] = getParent(parent, parent[x]);
    }

    public static void unionParent(int[] parent, int x, int y){
        x = getParent(parent, x);
        y = getParent(parent, y);

        if(x > y)  parent[x] = y;
        else parent[y] =x ;
    }

    public static boolean findParent(int[] parent, int x, int y){
        x = getParent(parent, x);
        y = getParent(parent, y);

        return x == y;
    }
}
