import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 빌런 호석
public class Main {

    static int[][] numInfo = new int[][]{
        {1, 1, 1, 0, 1, 1, 1},
        {0, 0, 1, 0, 0, 1, 0},
        {1, 0, 1, 1, 1, 0, 1},
        {1, 0, 1, 1, 0, 1, 1},
        {0, 1, 1, 1, 0, 1, 0},
        {1, 1, 0, 1, 0, 1, 1},
        {1, 1, 0, 1, 1, 1, 1},
        {1, 0, 1, 0, 0, 1, 0},
        {1, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 0, 1, 1},
    };

    static int N, K, P, cnt;
    static String X;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 최데 숫자
        K = Integer.parseInt(st.nextToken()); // 자리 수
        P = Integer.parseInt(st.nextToken()); // 최대 반전 수
        X = ((Integer) Integer.parseInt(st.nextToken())).toString(); // 현재 숫자
        String prefix = "";
        for (int i = 0; i < K - X.length(); i++) {
            prefix += "0";
        }
        X = prefix + X;

        dfs(0, 0, "");
        System.out.println(cnt);
    }

    private static void dfs(int pos, int reverse, String str) {
        if (reverse > P) { // 최대 반전 수를 넘어간 경우
            return;
        }

        int num = 0;
        if (str.length() > 0) {
            num = Integer.parseInt(str);
            if (num > N) { // 최대 숫자를 넘어간 경우
                return;
            }
        }

        if (pos == K) { // 자리 수만큼 숫자를 만든 경우
            if (reverse > 0 && num > 0) { // 최소 반전 수, 최소 숫자를 넘긴 경우
                // 경우의 수 카운팅
                cnt++;
            }
            return;
        }

        for (int i = 0; i <= 9; i++) {
            int tmp = 0;
            for (int j = 0; j < 7; j++) {
                if (numInfo[i][j] != numInfo[X.charAt(pos) - '0'][j]) {
                    tmp++;
                }
            }
            dfs(pos + 1, reverse + tmp, str + i);
        }
    }
}