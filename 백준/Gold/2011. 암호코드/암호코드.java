import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 암호코드
public class Main {

    static char[] num;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        num = br.readLine().toCharArray();
        dp = new int[num.length + 1]; // i번째 수까지의 만들어질 수 있는 경우의 수 (뒤에서부터 확인)

        dp[num.length] = 1;
        for (int i = num.length - 1; i >= 0; i--) {
            if (num[i] == '0') { // 앞의 수와 대응되어야 함
                dp[i] = 0;
                if (i > 0) {
                    int tmp = Integer.parseInt(String.valueOf(num[i - 1]) + String.valueOf(num[i]));
                    if (tmp >= 1 && tmp <= 26) {
                        dp[i - 1] = (dp[i - 1] + dp[i + 1]) % 1_000_000;
                    }
                }
                i--;
            } else { // 뒤의 수와 대응되어야 함
                dp[i] = (dp[i] + dp[i + 1]) % 1_000_000; // 한자리수
                if (i < num.length - 1) { // 두자리수
                    int tmp = Integer.parseInt(String.valueOf(num[i]) + String.valueOf(num[i + 1]));
                    if (tmp >= 1 && tmp <= 26) {
                        dp[i] = (dp[i] + dp[i + 2]) % 1_000_000;
                    }
                }
            }
        }

        System.out.println(dp[0]);
    }

}