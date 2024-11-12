import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 부분합
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 수열 길이
        int S = Integer.parseInt(st.nextToken()); // 최소 합

        int[] sum = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            sum[i] = sum[i - 1] + Integer.parseInt(st.nextToken());
        }

        int minLen = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            if (sum[i] < S) { // 0부터 i번째까지 다 더해도 S가 안되는 경우
                continue;
            }

            // 부분수열에 포함되지 않는 경계선을 구한다.
            // left가 0인 이유 : 다 더했을 때 S 이상이 되는 경우
            // right가 i인 이유 : 하나도 안 더했을 때 S 이상이 되는 경우 (S는 0일 수 있다)
            int left = 0, right = i, len = Integer.MAX_VALUE;
            while (left <= right) {
                int mid = (left + right) / 2;
                int val = sum[i] - sum[mid];

                if (val == S) { // 수열은 자연수로 이루어져 있어서, S가 되었다면 가장 짧은 부분수열이라는 의미!
                    len = i - mid;
                    break;
                } else if (val < S) { // S보다 부족하므로, 부분수열을 늘려야 함
                    right = mid - 1;
                } else if (val > S) { // S보다 넘쳤으므로, 일단 길이 저장한 후 부분수열을 줄여볼 수 있음
                    len = i - mid;
                    left = mid + 1;
                }
            }

            minLen = Math.min(minLen, len);
        }

        System.out.println(minLen == Integer.MAX_VALUE ? 0 : minLen);
    }
}