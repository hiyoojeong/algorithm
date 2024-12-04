import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 다각형의 면적
public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        long[] x = new long[N];
        long[] y = new long[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            x[i] = Long.parseLong(st.nextToken());
            y[i] = Long.parseLong(st.nextToken());
        }

        double res1 = 0, res2 = 0;
        for (int i = 0; i < N; i++) {
            res1 += x[i] * y[(i + 1) % N];
            res2 += y[i] * x[(i + 1) % N];
        }

        double res = Math.abs(res2 - res1) / 2.0;
        System.out.printf("%.1f", res);
    }
}