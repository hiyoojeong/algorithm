import java.util.Scanner;

// 블랙잭
public class Main {

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

	public static void combination(boolean[] visited, int cnt, int sum) {
		if (cnt == 3) {
			if (sum <= M) {
				answer = (M - sum < M - answer) ? sum : answer;
			}
			return;
		}

		for (int i = 0; i < N; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			combination(visited, cnt + 1, sum + cards[i]);
			visited[i] = false;
		}
	}

}
