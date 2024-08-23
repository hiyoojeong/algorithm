
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Z
public class Main {

	static long cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		dfs(r, c, (long) Math.pow(2, N));
	}

	public static void dfs(long r, long c, long size) {
		long half = size / 2;
		long halfArea = half * half;

		if (size == 1) {
			System.out.println(cnt);
			return;
		}

		if (r < half && c < half) {
			cnt += 0;
			dfs(r, c, half);
		} else if (r < half && c >= half) {
			cnt += halfArea;
			dfs(r, c - half, half);
		} else if (r >= half && c < half) {
			cnt += halfArea * 2;
			dfs(r - half, c, half);
		} else if (r >= half && c >= half) {
			cnt += halfArea * 3;
			dfs(r - half, c - half, half);
		}

	}

}
