import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// ATM
class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] p = new int[N + 1];
        int[] dp = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            p[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(p); // 오름차순 정렬

        int waitingTime = 0;
        for (int i = 1; i <= N; i++) {
            dp[i] = dp[i-1] + p[i]; // 앞사람들이 걸린 시간 + 내가 걸린 시간
            waitingTime += dp[i];
        }

        System.out.println(waitingTime);
    }
}