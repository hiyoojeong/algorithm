import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 기차 여행
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[] plan = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            plan[i] = Integer.parseInt(st.nextToken());
        }

		// costs[i] = i -> i+1로 가는 비용
		int[][] costs = new int[n][3];
		for (int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			costs[i][0] = Integer.parseInt(st.nextToken());
			costs[i][1] = Integer.parseInt(st.nextToken());
			costs[i][2] = Integer.parseInt(st.nextToken());
		}

		// uses[i] = i -> i+1로 가는 횟수
		int[] uses = new int[n + 1];
        for (int i = 0; i < m - 1; i++) {
            if (plan[i] < plan[i + 1]) {
                uses[plan[i]]++;
                uses[plan[i + 1]]--;
            } else {
                uses[plan[i]]--;
                uses[plan[i + 1]]++;
            }
        }

		long answer = 0, use = 0;
		for (int i = 1; i < n; i++) {
			use += uses[i];
			answer += Math.min(costs[i][0] * use, costs[i][1] * use + costs[i][2]);
		}
		System.out.println(answer);
	}

}
