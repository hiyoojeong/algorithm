import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 피자 판매
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int size = Integer.parseInt(br.readLine()); // 손님이 구매하고자 하는 피자크기

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken()); // A 피자의 피자조각 개수
        int n = Integer.parseInt(st.nextToken()); // B 피자의 피자조각 개수

        int[] A = new int[m];
        int[] B = new int[n];
        int[] sumA = new int[2_000_001];
        int[] sumB = new int[2_000_001];

        // 입력 및 전체집합, 공집합 카운팅
        int sum = 0;
        for (int i = 0; i < m; i++) {
            A[i] = Integer.parseInt(br.readLine());
            sum += A[i];
        }
        sumA[sum] = 1; // 전체집합
        sumA[0] = 1; // 공집합

        sum = 0;
        for (int i = 0; i < n; i++) {
            B[i] = Integer.parseInt(br.readLine());
            sum += B[i];
        }
        sumB[sum] = 1; // 전체집합
        sumB[0] = 1; // 공집합

        // 부분합 카운팅
        for (int i = 0; i < m; i++) {
            sum = 0;
            for (int j = i; j < i + m - 1; j++) { // 전제집합은 카운팅 해놨으니, 직전 부분집합까지만 카운팅
                sum += A[j % m];
                sumA[sum]++;
            }
        }

        for (int i = 0; i < n; i++) {
            sum = 0;
            for (int j = i; j < i + n - 1; j++) { // 전제집합은 카운팅 해놨으니, 직전 부분집합까지만 카운팅
                sum += B[j % n];
                sumB[sum]++;
            }
        }

        // 경우의 수
        int cnt = 0;
        for (int i = 0; i <= size; i++) {
            cnt += sumA[i] * sumB[size - i];
        }

        System.out.println(cnt);
    }
}