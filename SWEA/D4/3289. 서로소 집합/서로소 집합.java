import java.util.Scanner;

// 서로소 집합
public class Solution {

	static int N, M;
	static int[] parents;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuffer answer = new StringBuffer();

		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			answer.append("#" + test_case + " ");

			// 입력
			N = sc.nextInt();
			M = sc.nextInt();

			make(N);

			for (int i = 0; i < M; i++) {
				int type = sc.nextInt(); // 0이면 합집합, 1이면 결과
				int a = sc.nextInt();
				int b = sc.nextInt();

				if (type == 0) { // 합집합
					union(a, b);
				} else { // 같은 집합에 포함되었는지 확인
					int aRoot = find(a);
					int bRoot = find(b);

					if (aRoot == bRoot) { // 같은 집합
						answer.append("1");
					} else { // 다른 집합
						answer.append("0");
					}
				}
			}

			answer.append("\n");
		}

		System.out.println(answer);
		sc.close();

	}

	public static void make(int N) {
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}

	public static int find(int v) {
		if (parents[v] == v) {
			return v;
		}
		return parents[v] = find(parents[v]);
	}

	public static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot) {
			return;
		}

		if (aRoot > bRoot) {
			parents[bRoot] = aRoot;
		} else {
			parents[aRoot] = bRoot;
		}
	}

}
