
import java.util.Scanner;

// 수영장
public class Solution {

	static int day, month, month3, year;
	static int[] plan = new int[12];
	static int minCost;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuffer answer = new StringBuffer();

		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			day = sc.nextInt();
			month = sc.nextInt();
			month3 = sc.nextInt();
			year = sc.nextInt();

			for (int i = 0; i < 12; i++) {
				plan[i] = sc.nextInt();
			}

			minCost = year;
			dfs(0, 0);

			answer.append(String.format("#%d %d\n", test_case, minCost));
		}

		System.out.println(answer);
		sc.close();
	}

	public static void dfs(int cnt, int cost) {
		if (cost >= minCost) { // 이미 최소 비용을 넘긴 경우
			return;
		}

		if (cnt >= 12) { // 1월부터 12월까지의 이용 비용이 최소 비용인 경우
			minCost = cost;
			return;
		}

		// 1일 이용권
		dfs(cnt + 1, cost + day * plan[cnt]);

		// 1달 이용권
		dfs(cnt + 1, cost + month);

		// 3달 이용권
		dfs(cnt + 3, cost + month3);

		// 12달 이용권
		dfs(cnt + 12, cost + year);
	}

}
