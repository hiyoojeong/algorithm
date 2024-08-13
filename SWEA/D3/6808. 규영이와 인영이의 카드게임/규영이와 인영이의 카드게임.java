import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [SWEA] 6808. 규영이와 인영이의 카드 게임
public class Solution {

	static int N = 9;
	static int[] myCard = new int[N]; // 규영이의 카드 순열
	static int[] not_myCard = new int[N]; // 인영이의 카드 정보
	static int[] enemyCard = new int[N]; // 인영이의 카드 순열
	static boolean[] isSelected = new boolean[N];

	static int winCount = 0, loseCount = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer answer = new StringBuffer();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {

			// 입력
			st = new StringTokenizer(br.readLine());
			boolean[] isUsed = new boolean[18 + 1];
			for (int i = 0; i < N; i++) {
				myCard[i] = Integer.parseInt(st.nextToken());
				isUsed[myCard[i]] = true;
			}

			// 인영이의 카드를 저장한다.
			int idx = 0;
			for (int i = 1; i <= 18; i++) {
				if (!isUsed[i]) {
					not_myCard[idx++] = i;
				}
			}

			// 인영이의 카드 순열을 구한 뒤, 규영이와 이기는 경우와 지는 경우에 따라 경우의 수를 업데이트 한다.
			winCount = 0;
			loseCount = 0;
			permutation(0);

			// 출력
			answer.append("#").append(t).append(" ").append(winCount).append(" ").append(loseCount).append("\n");
		}

		System.out.println(answer);

	}

	public static void permutation(int cnt) {
		if (cnt == N) {
			// 규영이의 점수와 인영이의 점수를 계산한다.
			int myScore = 0, enemyScore = 0;
			for (int i = 0; i < N; i++) {
				if (myCard[i] > enemyCard[i]) {
					myScore += myCard[i] + enemyCard[i];
				}

				else if (myCard[i] < enemyCard[i]) {
					enemyScore += myCard[i] + enemyCard[i];
				}
			}

			// 규영이와 이기는 경우와 지는 경우에 따라 경우의 수를 업데이트 한다.
			if (myScore > enemyScore) {
				winCount++;
			} else if (myScore < enemyScore) {
				loseCount++;
			}

			return;
		}

		for (int i = 0; i < N; i++) {
			if (isSelected[i]) {
				continue;
			}

			enemyCard[cnt] = not_myCard[i];
			isSelected[i] = true;
			permutation(cnt + 1);
			isSelected[i] = false;
		}
	}

}
