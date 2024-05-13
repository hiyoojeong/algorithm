import java.util.Arrays;

// 이모티콘 할인행사
public class No150368 {

	static int[] sales = new int[] { 10, 20, 30, 40 }; // 이모티콘이 가질 수 있는 할인
	static int[] saleArr = null; // 각각 이모티콘 할인율을 저장하는 배열

	public static void main(String[] args) {
//		int[][] users = { { 40, 10000 }, { 25, 10000 } };
//		int[] emoticons = { 7000, 9000 };

		int[][] users = { { 40, 2900 }, { 23, 10000 }, { 11, 5200 }, { 5, 5900 }, { 40, 3100 }, { 27, 9200 },
				{ 32, 6900 } };
		int[] emoticons = { 1300, 1500, 1600, 4900 };

		int[] result = solution(users, emoticons);
		System.out.println(Arrays.toString(result));

	}

	public static int[] solution(int[][] users, int[] emoticons) {
		int[] answer = { 0, 0 };

		saleArr = new int[emoticons.length]; // 각각 이모티콘 할인율을 저장하는 배열의 길이 = 이모티콘 개수
		permutation(0, users, emoticons, answer);

		return answer;
	}

	// 중복조합
	private static void permutation(int cnt, int[][] users, int[] emoticons, int[] answer) {
		// 조합이 만들어졌다.
		if (cnt == emoticons.length) {
			int totplus = 0, totcost = 0; // 만들어진 조합에 대한 총 이모티콘 플러스 사용자 수, 총 이모티콘 구매 비용을 저장하는 변수
			for (int i = 0; i < users.length; i++) {
				int s = users[i][0], c = users[i][1], nowcost = 0;
				for (int j = 0; j < emoticons.length; j++) {
					if (s <= saleArr[j]) { // 원하는 할인율보다 할인율이 높아, 구매를 하는 경우
						nowcost += emoticons[j] - (emoticons[j] * saleArr[j] / 100);
						if (nowcost >= c) { // 원하는 구매비용보다 구매비용이 커, 이모티콘 플러스로 전환하는 경우 
							nowcost = 0;
							totplus++;
							break;
						}
					}
				}
				totcost += nowcost;

			}

			// 만들어진 조합이 조건에 더 부합한다면, 업데이트
			if (answer[0] < totplus) {
				answer[0] = totplus;
				answer[1] = totcost;
			} else if (answer[0] == totplus) {
				if (answer[1] < totcost) {
					answer[1] = totcost;
				}
			}
			return;
		}

		// 각각 이모티콘 할인율을 저장하는 배열에 할인율을 저장
		for (int i = 0; i < sales.length; i++) {
			saleArr[cnt] = sales[i];
			permutation(cnt + 1, users, emoticons, answer);
		}
	}

}
