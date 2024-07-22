import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 알약
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer answer = new StringBuffer();

        long[][] dp = new long[31][31]; // dp[w][h]: 알약 한알이 w이고, 알약 반알이 h일 경우 만들어질 수 있는 경우의 수

        for (int w = 0; w <= 30; w++) {
            for (int h = 0; h <= 30; h++) {
                // w(알약 한알)보다 h(알약 반알)가 많은 경우는 없다.
                if (w < h) {
                    continue;
                }
                // h(알약 반알)가 0이면, w(알약 한알)으로만 구성되어 있다는 의미이다.
                if (h == 0) {
                    dp[w][h] = 1;
                }
                // w(알약 한알)이 가장 앞에 오는 경우의 수와 h(알약 반알)이 가장 뒤에 오는 경우의 수를 더해준다.
                else {
                    dp[w][h] = dp[w - 1][h] + dp[w][h - 1];
                }
            }
        }

        int N = 0;
        while ((N = Integer.parseInt(br.readLine())) != 0) {
            answer.append(dp[N][N]).append("\n");
        }

        System.out.println(answer);
    }


}