import java.util.Scanner;

// 햄버거 다이어트 - 백트래킹
public class Solution {

    static int N, L, scores[], calories[];
    static int maxScore;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        StringBuffer answer = new StringBuffer();
        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            L = sc.nextInt();
            scores = new int[N];
            calories = new int[N];

            for (int i = 0; i < N; i++) {
                scores[i] = sc.nextInt();
                calories[i] = sc.nextInt();
            }

            maxScore = 0;
            dfs(0, 0, 0);

            answer.append(String.format("#%d %d\n", test_case, maxScore));
        }

        System.out.println(answer);

        sc.close();
    }

    public static void dfs(int idx, int score, int calory) {
        // 제한칼로리가 넘어간 경우, 탐색 종료
        if (calory > L) {
            return;
        }

        // 재료 선택이 완료된 경우, 최대 점수 업데이트
        if (idx == N) {
            if (maxScore < score) {
                maxScore = score;
            }
            return;
        }

        dfs(idx + 1, score + scores[idx], calory + calories[idx]); // 현재 재료 선택
        dfs(idx + 1, score, calory); // 현재 재료 미선택
    }
}
