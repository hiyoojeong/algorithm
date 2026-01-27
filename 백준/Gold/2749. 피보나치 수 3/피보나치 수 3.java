import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 2749 피보나치 수 3
public class Main {

    static final long pisano = 15 * 100_000;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());
        N %= pisano;

        long[] fibo = new long[(int) N + 1];
        fibo[0] = 0;
        fibo[1] = 1;
        for (int i = 2; i <= N; i++) {
            fibo[i] = (fibo[i - 2] + fibo[i - 1]) % 1_000_000;
        }

        System.out.println(fibo[(int) N]);
    }
}