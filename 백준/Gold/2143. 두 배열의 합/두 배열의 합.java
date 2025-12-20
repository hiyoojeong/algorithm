import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 2143 두 배열의 합

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        int m = Integer.parseInt(br.readLine());
        int[] B = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        // A 부배열 합 체크
        long[] subA = new long[n * (n + 1) / 2];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = i; j < n; j++) {
                sum += A[j];
                subA[idx++] = sum;
            }
        }

        // B 부배열 합 체크
        long[] subB = new long[m * (m + 1) / 2];
        idx = 0;
        for (int i = 0; i < m; i++) {
            long sum = 0;
            for (int j = i; j < m; j++) {
                sum += B[j];
                subB[idx++] = sum;
            }
        }

        // 이분탐색으로 T 가 되는 경우 탐색
        Arrays.sort(subB);

        long answer = 0;
        for (int i = 0; i < subA.length; i++) {
            int cntB = upper(subB, T-subA[i]) - lower(subB, T-subA[i]);
            answer += cntB;
        }

        System.out.println(answer);
    }

    public static int upper(long[] arr, long target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int middle = (left + right) / 2;
            if (arr[middle] <= target) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }

        return right;
    }

    public static int lower(long[] arr, long target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int middle = (left + right) / 2;
            if (arr[middle] < target) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }

        return right;
    }
}