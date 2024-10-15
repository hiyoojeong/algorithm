import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 레벨 햄버거
public class Main {

    static int N;
    static long X, pat[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 햄버거 레벨
        X = Long.parseLong(st.nextToken()); // 아래부터 X장 먹음
        pat = new long[N + 1];

        long len = 1; // 레이어 수
        pat[0] = 1; // 패티 수
        for (int i = 1; i <= N; i++) {
            len = len * 2 + 3;
            pat[i] = pat[i - 1] * 2 + 1;
        }

        long result = hamburger(N, X, 1, len);
        System.out.println(result);
    }

    private static long hamburger(int N, long X, long start, long end) {
        if (N == 0) {
            return pat[0];
        }

        long mid = (start + end) / 2;
        if (X == start) {
            return 0;
        } else if (X < mid) {
            return hamburger(N - 1, X, start + 1, mid - 1);
        } else if (X == mid) {
            return pat[N - 1] + 1;
        } else if (X < end) {
            return pat[N - 1] + 1 + hamburger(N - 1, X, mid + 1, end - 1);
        } else { // X == end
            return pat[N - 1] * 2 + 1;
        }
    }
}