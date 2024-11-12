import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 용액
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int s = 0, e = N - 1, ansVal = Integer.MAX_VALUE;
        int[] ans = null;

        while (s < e) {
            int val = arr[s] + arr[e];
            if (Math.abs(val) < Math.abs(ansVal)) {
                ansVal = val;
                ans = new int[]{arr[s], arr[e]};
            }

            if (val == 0) {
                break;
            } else if (val > 0) { // 합을 줄여야 함
                e--;
            } else if (val < 0) { // 합을 늘여야 함
                s++;
            }
        }

        Arrays.sort(ans);
        System.out.println(ans[0] + " " + ans[1]);
    }
}