
import java.util.Scanner;

// 홈 방범 서비스
public class Solution {

    static int N, M, map[][];
    static int houseCnt = 0, serviceCnt = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuffer answer = new StringBuffer();

        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt(); // 도시의 크기
            M = sc.nextInt(); // 하나의 집이 지불할 수 있는 비용
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            int maxHouseCnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = 1; k <= N + 1; k++) { // k는 서비스 영역 크기
                        houseCnt = 0;
                        serviceCnt = 0;
                        getHouseCnt(i, j, k);

                        int profit = houseCnt * M - serviceCnt;
                        if (profit >= 0
                            && maxHouseCnt < houseCnt) { // 손해를 보지 않으면서 홈방범 서비스를 가장 많은 집들 수 업데이트
                            maxHouseCnt = houseCnt;
                        }
                    }
                }
            }

            answer.append(String.format("#%d %d\n", test_case, maxHouseCnt));
        }

        System.out.println(answer);
        sc.close();
    }

    public static void getHouseCnt(int sr, int sc, int k) {
        int size = 1;
        for (int r = sr - (k - 1); r <= sr + (k - 1); r++) {
            for (int c = sc - (size - 1); c <= sc + (size - 1); c++) {
                serviceCnt++; // 도시를 벗어난 영역에 서비스를 제공해도 운영 비용은 변경되지 않는다.
                if (isRange(r, c)) {
                    if (map[r][c] == 1) {
                        houseCnt++;
                    }
                }
            }
            if (r < sr) { // 중심행 이전까지는 열 사이즈가 증가한다.
                size++;
            } else if (r >= sr) { // 중심행 부터는 열 사이즈가 감소한다.
                size--;
            }
        }
    }

    public static boolean isRange(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }

}