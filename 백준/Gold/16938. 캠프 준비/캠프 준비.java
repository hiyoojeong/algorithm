import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 캠프 준비
public class Main {

    static int arr[], N, L, R, X, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        subset(0, 0, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        System.out.println(result);
    }

    public static void subset(int idx, int cnt, int sum, int easy, int hard) {
        if (idx == N) {
            if ((sum >= L && sum <= R) && hard - easy >= X && cnt >= 2) {
                result++;
            }
            return;
        }

        // 현재 문제를 선택했을 때
        subset(idx + 1, cnt + 1, sum + arr[idx], Math.min(easy, arr[idx]),
            Math.max(hard, arr[idx]));
        // 현재 문제를 선택하지 않았을 때
        subset(idx + 1, cnt, sum , easy, hard);
    }
}