import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 과자 나눠주기
public class Main {

    static int N, M;
    static int[] snacks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // 조카의 수
        N = Integer.parseInt(st.nextToken()); // 과자의 수
        snacks = new int[N];

        int maxValue = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            snacks[i] = Integer.parseInt(st.nextToken());
            maxValue = Math.max(snacks[i], maxValue);
        }

        int left = 1;
        int right = maxValue;
        int maxLen = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            long cut = cut(mid);

            if (cut < M) { // 조카 수보다 막대 과자 수가 부족하다면, 더 잘게 자른다.
                right = mid - 1;
            } else {
                maxLen = Math.max(mid, maxLen); // 조카 수와 막대 과자 수가 같거나 더 많다면, 최대 길이인지 업데이트하고 더 긴 경우가 있는지 확인한다.
                left = mid + 1;
            }
        }

        System.out.println(maxLen);
    }

    public static long cut(int size) {
        long cut = 0;
        for (int i = 0; i < N; i++) {
            cut += snacks[i] / size;
        }
        return cut;
    }

}
