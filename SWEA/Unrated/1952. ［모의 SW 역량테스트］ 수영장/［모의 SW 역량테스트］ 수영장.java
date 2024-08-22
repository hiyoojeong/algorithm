import java.util.Scanner;

// 수영장
public class Solution {

	static int prices[], plans[];
	static int minCost;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		StringBuffer answer = new StringBuffer();
		for (int test_case = 1; test_case <= T; test_case++) {
			prices = new int[4];
			plans = new int[12];

			for (int i = 0; i < 4; i++) {
				prices[i] = sc.nextInt();
			}
			for (int i = 0; i < 12; i++) {
				plans[i] = sc.nextInt();
			}

			minCost = Integer.MAX_VALUE;
			dfs(0, 0);

			answer.append(String.format("#%d %d\n", test_case, minCost));
		}

		System.out.println(answer);

		sc.close();

	}

	public static void dfs(int month, int cost) {
		if (cost > minCost) {
			return;
		}

		if (month == 12) {
			minCost = cost;
			return;
		}

		// 1일 이용권
		dfs(month + 1, cost + prices[0] * plans[month]);
		// 1달 이용권
		dfs(month + 1, cost + prices[1]);
		// 3달 이용권
		dfs(Math.min(month + 3, 12), cost + prices[2]);
		// 1년 이용권
		dfs(Math.min(month + 12, 12), cost + prices[3]);
	}

}
