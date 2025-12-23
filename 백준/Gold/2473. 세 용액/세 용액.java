import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 2473 세 용액

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int ans1 = 0, ans2 = 0, ans3 = 0;
        long minSum = Long.MAX_VALUE;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {

                int l = j + 1, r = n - 1;
                while (l <= r) {
                    int m = (l + r) / 2;
                    long val = (long) arr[i] + arr[j] + arr[m];
                    if (Math.abs(val) < Math.abs(minSum)) {
                        ans1 = arr[i];
                        ans2 = arr[j];
                        ans3 = arr[m];
                        minSum = val;
                    }

                    if (val == 0) {
                        break;
                    } else if (val < 0) {
                        l = m + 1;
                    } else {
                        r = m - 1;
                    }
                }

                if (minSum == 0) break;
            }

            if (minSum == 0) break;
        }

        System.out.println(ans1 + " " + ans2 + " " + ans3);
    }
}