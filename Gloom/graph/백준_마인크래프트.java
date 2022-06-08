import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int block = Integer.parseInt(st.nextToken());

		int[][] map = new int[n][m];
		int min = Integer.MAX_VALUE;
		int max = 0;
		for (int x = 0; x < n; x++) {
			st = new StringTokenizer(br.readLine());
			for (int y = 0; y < m; y++) {
				map[x][y] = Integer.parseInt(st.nextToken());
				min = Math.min(min, map[x][y]);
				max = Math.max(max, map[x][y]);
			}
		}

		int resultTime = Integer.MAX_VALUE;
		int resultHigh = -1;
		for (int f = min; f <= max; f++) {
			int time = 0;
			int inventory = block;

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					int dif = map[i][j] - f;

					if (dif > 0) {
						time += Math.abs(dif) * 2;
						inventory += Math.abs(dif);
					} else if (dif < 0) {
						time += Math.abs(dif);
						inventory -= Math.abs(dif);
					}

				}
			}
			if (inventory < 0)
				continue;
			if (resultTime >= time) {
				resultTime = time;
				resultHigh = f;
			}
		}

		System.out.println(resultTime + " " + resultHigh);
	}
}
