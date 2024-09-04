
import java.util.Scanner;

// 그룹 나누기
public class Solution {

	static int[] parents;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuffer answer = new StringBuffer();

		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt(); // 출석번호
			int M = sc.nextInt(); // 신청서 수 (관계 수)

			parents = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				parents[i] = i;
			}

			// 조 합치기
			for (int i = 0; i < M; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();

				union(a, b);
			}

			// 조 개수 카운팅
			int cnt = 0;
			for (int i = 1; i <= N; i++) {
				if (parents[i] == i) {
					cnt++;
				}
			}

			answer.append(String.format("#%d %d\n", test_case, cnt));
		}

		System.out.println(answer);
		sc.close();
	}

	public static int find(int v) {
		if (parents[v] == v) {
			return v;
		}
		return parents[v] = find(parents[v]);
	}

	public static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);

		if (pa == pb) {
			return;
		}

		parents[pa] = pb;
	}

}
