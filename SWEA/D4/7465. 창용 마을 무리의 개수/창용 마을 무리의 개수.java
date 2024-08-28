
// 창용 마을 무리의 개수
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {

	static int N, M;
	static int[] parents;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuffer answer = new StringBuffer();

		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			// 입력
			N = sc.nextInt();
			M = sc.nextInt();

			make(); // 집합 생성

			for (int i = 0; i < M; i++) { // 집합 합치기
				int a = sc.nextInt();
				int b = sc.nextInt();

				union(a, b);
			}

			Set<Integer> group = new HashSet<>(); // 집합 개수 카운트
			for (int i = 1; i <= N; i++) {
				group.add(find(i));
			}

			answer.append("#" + test_case + " " + group.size() + "\n");
		}

		System.out.println(answer);
		sc.close();

	}

	public static void make() {
		parents = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			parents[i] = i;
		}
	}

	public static int find(int v) {
		if (v == parents[v]) {
			return v;
		}

		return parents[v] = find(parents[v]);
	}

	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot) {
			return false;
		}

		if (aRoot > bRoot) {
			parents[aRoot] = bRoot;
		} else {
			parents[bRoot] = aRoot;
		}
		return true;
	}

}
