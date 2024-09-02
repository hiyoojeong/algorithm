import java.util.Scanner;

// 2115. 벌꿀채취
public class Solution {

    static int N, M, C;
    static int[][] map, honey;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        StringBuffer answer = new StringBuffer();
        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt(); // 벌통들의 크기
            M = sc.nextInt(); // 선택할 수 있는 벌통의 개수
            C = sc.nextInt(); // 꿀을 채취할 수 있는 최대 양
            map = new int[N][N];
            honey = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            // 가로로 연속되도록 M개의 벌통을 선택하고, 선택한 벌통에서의 최대 수익을 저장한다.
            for (int i = 0; i < N; i++) {
                for (int j = 0; j <= N - M; j++) {
                    setMaxHoney(i, j, 0, 0, 0);
                }
            }

            // 두 일꾼 채취할 벌통을 선택하고, 선택한 벌통에서의 최대 수익의 합을 계산한다. 각 벌통의 최대 수입의 합이 최대값이면, 결과를 업데이트한다.
            int result = 0;
            // (i, j)에서 구한 수익 선택
            for (int i = 0; i < N; i++) {
                for (int j = 0; j <= N - M; j++) { // (i, j)에서 구한 수익 선택 완료
                    // (x, y)에서 구한 수익 선택
                    for (int x = i; x < N; x++) { // 조합이므로, x행은 i행부터 시작
                        int y = 0;
                        if (i == x) {
                            y = j + M; // 같은 행에서는 선택한 벌통이 겹치면 안되므로, y열은 j+M열부터 시작
                        }
                        for (; y <= N - M; y++) { // (x, y)에서 구한 수익 선택 완료
                            if (result < honey[i][j] + honey[x][y]) {
                                result = honey[i][j] + honey[x][y];
                            }
                        }
                    }
                }
            }

            answer.append(String.format("#%d %d\n", test_case, result));
        }

        System.out.println(answer);

        sc.close();
    }

    public static void setMaxHoney(int x, int y, int cnt, int sum, int squareSum) {
        // 최대 채취양보다 크면, 더이상 확인하지 않음
        if (sum > C) {
            return;
        }

        // M개 벌통 선택완료
        if (cnt == M) {
            // 이전에 선택한 벌통의 수익보다 크면, 업데이트
            if (honey[x][y] < squareSum) {
                honey[x][y] = squareSum;
            }
            return;
        }

        // 현재 벌통 선택
        setMaxHoney(x, y, cnt + 1, sum + map[x][y + cnt],
            squareSum + map[x][y + cnt] * map[x][y + cnt]);
        // 현재 벌통 미선택
        setMaxHoney(x, y, cnt + 1, sum, squareSum);
    }
}
