import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 벼락치기
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[] time = new int[N];
        int[] score = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            score[i] = Integer.parseInt(st.nextToken());

        }

        int[] dp = new int[T + 1];
        for (int i = 0; i < N; i++) {
            for (int j = T; j - time[i] >= 0; j--) {
                dp[j] = Math.max(dp[j], dp[j - time[i]] + score[i]);
            }
        }

        System.out.println(dp[T]);
    }

}