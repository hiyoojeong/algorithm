import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 주식
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] prices = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                prices[i] = Integer.parseInt(st.nextToken());
            }

            answer.append(getMaxProfit(N, prices)).append("\n");
        }

        System.out.println(answer);
    }

    public static long getMaxProfit(int N, int[] prices) {
        int maxPrice = prices[N - 1];
        long profit = 0;

        for (int i = N - 2; i >= 0; i--) {
            if (prices[i] > maxPrice) {
                maxPrice = prices[i];
                continue;
            }

            profit += maxPrice - prices[i];
        }

        return profit;
    }

}
