package 브루트_포스;

import java.util.Scanner;

// 블랙잭
public class No2798 {

	static int N, M, answer = 0;
	static int[] cards;

	public static void main(String[] args) {
		// 입력
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		cards = new int[N];
		for (int i = 0; i < N; i++) {
			cards[i] = sc.nextInt();
		}
		sc.close();

		// 풀이
		combination(new boolean[N], 0, 0);

		// 출력
		System.out.println(answer);
	}

	// 조합(combination)
	// visited 배열: 카드를 중복을 막기 위해 고른 카드는 표시한다.
	// cnt: 현재까지 고른 카드의 수
	// sum: 현재까지 고른 카드의 합
	public static void combination(boolean[] visited, int cnt, int sum) {
		// 3개의 카드 조합을 모두 고른 경우, answer를 업데이트한다.
		if (cnt == 3) {
			if (sum <= M) {
				answer = (M - sum < M - answer) ? sum : answer;
			}
			return;
		}

		// 3개의 카드 조합을 고른다.
		for (int i = 0; i < N; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			combination(visited, cnt + 1, sum + cards[i]);
			visited[i] = false;
		}
	}

}
