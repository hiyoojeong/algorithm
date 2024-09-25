import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 동전 0
class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 동전 종류 수
        int K = Integer.parseInt(st.nextToken()); // 만들어야 하는 수

        int[] coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int idx = N - 1; // 현재 사용하려는 동전 단위 = 큰 동전부터 사용
        int cnt = 0; // K원을 만드는 데 필요한 동전 개수
        while (K != 0) {
            if (coins[idx] <= K) {
                cnt += K / coins[idx];
                K %= coins[idx];
            }
            idx--;
        }

        System.out.println(cnt);
    }
}