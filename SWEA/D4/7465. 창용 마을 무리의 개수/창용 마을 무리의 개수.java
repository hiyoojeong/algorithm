import java.util.Scanner;

// 창용 마을 무리의 개수
public class Solution {

    static int N, M;
    static int[] parents;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        StringBuffer answer = new StringBuffer();
        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt(); // 창용 마을에 사는 사람의 수
            M = sc.nextInt(); // 관계 수

            // 집합 초기화
            parents = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                parents[i] = i;
            }

            // 관계별로 집합 합치기
            for (int i = 0; i < M; i++) {
                union(sc.nextInt(), sc.nextInt());
            }

            // 집합 개수세기
            int cnt = 0;
            for (int i = 1; i <= N; i++) {
                if (parents[i] == i) { // 대표자 수만 세면 된다.
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
