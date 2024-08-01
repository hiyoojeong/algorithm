import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 이상한 술집
public class Main {

    static int N, K;
    static long[] sizes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        sizes = new long[N];

        long maxValue = 0;
        for (int i = 0; i < N; i++) {
            long value = Long.parseLong(br.readLine());
            sizes[i] = value;

            maxValue = Math.max(value, maxValue);
        }

        long left = 1;
        long right = maxValue;

        long result = 0;
        while(left <= right) {
            long mid = (left + right) / 2;
            long cut = cut(mid);

            if(cut < K) { // 사람수보다 적으면, 더 작은 용량으로 나눠야한다.
                right = mid - 1;
            } else { // 사람수보다 많거나 같으면, 충족은 했으니 값을 업데이트! 더 큰 용량으로 나눠도 되는지 계속 확인!
                result = Math.max(mid, result);
                left = mid + 1;
            }
        }

        System.out.println(result);
    }

    public static long cut(long i) {
        long cut = 0;
        for (long size : sizes) {
            cut += (size / i);
        }
        return cut;
    }

}
