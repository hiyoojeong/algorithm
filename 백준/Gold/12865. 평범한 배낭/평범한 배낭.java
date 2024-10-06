import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 평범한 배낭
class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 물품의 수
        int K = Integer.parseInt(st.nextToken()); // 버틸 수 있는 무게

        int[] w = new int[N]; // 물건의 무게
        int[] v = new int[N]; // 물건의 가치
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            w[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[K + 1];
        for (int i = 0; i < N; i++) {
            for (int j = K; j - w[i] >= 0; j--) {
                // i번째 물건을 담는게 더 이득인 경우
                dp[j] = Math.max(dp[j], v[i] + dp[j - w[i]]);
            }
        }
        System.out.println(dp[K]);
    }
}