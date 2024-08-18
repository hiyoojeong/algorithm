import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    static int D, W, K;
    static int[][] cases; // 보호 필름의 단면 정보
    static int[][] copy; // 보호 필름의 단면 정보 복구용

    static final int A = 0;
    static final int B = 1;

    static boolean[] isSelected; // 약물을 투여할 보호 필름 정보
    static int minCnt; // 최소 약물 투여 횟수

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer answer = new StringBuffer();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken()); // 보호 필름의 두께 = 행
            W = Integer.parseInt(st.nextToken()); // 가로 크기 = 열
            K = Integer.parseInt(st.nextToken()); // 합격 기준

            cases = new int[D][W]; // 보호 필름 단면 정보
            copy = new int[D][W]; // 보호 필름 단면 정보 복구용
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    cases[i][j] = Integer.parseInt(st.nextToken());
                    copy[i][j] = cases[i][j];
                }
            }

            isSelected = new boolean[D]; // 약물을 투여할 보호 필름 정보
            minCnt = Integer.MAX_VALUE; // 최소 약물 투여 횟수
            combination(0);

            answer.append(String.format("#%d %d\n", t, minCnt));
        }

        System.out.println(answer);
    }

    /*
     * 약물을 투여할 보호 필름의 모든 부분 집합을 구한다.
     */
    public static void combination(int cnt) {
        if (cnt == D) { // 모든 보호 필름에 약물을 넣을 것인지, 넣지 않을 것인지 선택이 완료되었다.
            dfs(0, 0);
            copyCases();
            return;
        }

        isSelected[cnt] = true; // 현재 보호 필름에 약물을 넣는 경우
        combination(cnt + 1);
        isSelected[cnt] = false; // 현재 보호 필름에 약물을 넣지 않는 경우
        combination(cnt + 1);
    }

    /*
     * 약물을 투여할 보호 필름에 약물 A와 약물 B를 투여해본다.
     */
    public static void dfs(int cnt, int index) {
        // 최소 약물 투여 횟수보다 크거나 같다면, 이후는 굳이 확인할 필요가 없다.
        if (cnt >= minCnt) {
            return;
        }

        if (index == D) {
            // 합격 기준을 넘는지 확인한다.
            if (isPassed()) {
                minCnt = cnt;
            }

            // 합격 기준을 넘는다면, 최소 약물 투여 횟수를 업데이트한다.
            return;
        }

        // 현재 보호 필름에 약물을 넣어야 하는 경우
        if (isSelected[index]) {
            // A 투여
            Arrays.fill(cases[index], A);
            dfs(cnt + 1, index + 1);

            // B 투여
            Arrays.fill(cases[index], B);
            dfs(cnt + 1, index + 1);

        }
        // 현재 보호 필름에 약물을 넣지 말아야 하는 경우
        else {
            dfs(cnt, index + 1);
        }
    }

    /*
     * 합격 기준을 통과하는 지 확인한다.
     */
    public static boolean isPassed() {
        for (int i = 0; i < W; i++) {
            int s = 0, e = 1;
            int len = 1;

            while (e < D) {
                // 같은 종류의 필름이라면, 길이를 증가시킨다.
                if (cases[s][i] == cases[e][i]) {
                    e++;
                }
                // 다른 종류의 필름이라면, 해당 위치부터 다시 길이를 잰다.
                else {
                    s = e;
                    e++;
                }

                // 길이 업데이트
                len = Math.max(e - s, len);

                // 길이가 합격 기준을 넘어가면, 더이상 확인할 필요가 없다.
                if (len >= K) {
                    break;
                }
            }

            // 길이가 합격 기준을 넘어가지 않는다.
            if (len < K) {
                return false;
            }
        }

        // 길이가 합격 기준을 넘어간다.
        return true;
    }

    public static void copyCases() {
        for (int i = 0; i < D; i++) {
            for (int j = 0; j < W; j++) {
                cases[i][j] = copy[i][j];
            }
        }
    }
}
