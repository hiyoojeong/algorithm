import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 평버한 배낭 2
public class Main {

	static class Item {
		int weight;
		int satisfaction;

		public Item(int weight, int satisfaction) {
			this.weight = weight;
			this.satisfaction = satisfaction;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 물건 종류의 수
		int M = Integer.parseInt(st.nextToken()); // 민호가 들 수 있는 가방의 최대 무게

		List<Item> items = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int satisfaction = Integer.parseInt(st.nextToken());
			int cnt = Integer.parseInt(st.nextToken());

			// 물건 개수만큼 물건을 추가하면, O(N(물건 종류의 수)*K(물건의 개수)*M(가방의 최대 무게))
			// 이진 수의 형태로 물건을 추가하면, O(N(물건 종류의 수)*logK(물건의 개수)*M(가방의 최대 무게))
			// 이진 수의 형태로 물건을 추가한다.
			int currentCnt = 1;
			while (cnt > 0) {
				if (currentCnt > cnt) {
					items.add(new Item(weight * cnt, satisfaction * cnt));
					break;
				}
				
				items.add(new Item(weight * currentCnt, satisfaction * currentCnt));

				cnt -= currentCnt;
				currentCnt *= 2;
			}
		}

		int[] dp = new int[M + 1];
		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			for (int j = M; j - item.weight >= 0; j--) {
				dp[j] = Math.max(dp[j], item.satisfaction + dp[j - item.weight]);
			}

		}

		System.out.println(dp[M]);

	}

}
