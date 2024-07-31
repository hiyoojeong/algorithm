// 랜선 자르기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static long[] lans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken()); // 이미 가지고 있는 랜선의 개수
        N = Integer.parseInt(st.nextToken()); // 필요한 랜선의 개수
        lans = new long[K]; // 이미 가지고 있는 랜선 정보

        // 이미 가지고 있는 랜선을 저장한다.
        // 최대 랜선 길이를 저장해둔다.
        long maxValue = 0;
        for (int i = 0; i < K; i++) {
            lans[i] = Long.parseLong(br.readLine());
            maxValue = Math.max(maxValue, lans[i]);
        }

        // left, right를 초기화한다.
        long left = 1;
        long right = maxValue;

        // 이분탐색으로 최대 랜선 길이를 구한다.
        long maxLen = Integer.MIN_VALUE;
        while (left <= right) {
            long mid = (left + right) / 2;
            long cnt = cut(mid);

            if (cnt < N) { // 필요한 랜선의 개수보다 부족하므로, 더 잘게 잘라야 한다.
                right = mid - 1;
            } else { // 필요한 랜선의 개수는 충족하였으나, 최대 랜선의 길이가 있을 수 있으니 지금 길이를 가장 작은 범위로 설정해둔다.
                maxLen = Math.max(mid, maxLen);
                left = mid + 1;
            }
        }

        System.out.println(maxLen);

    }

    public static long cut(long size) {
        long cnt = 0;
        for (long lan : lans) {
            cnt += (lan / size);
        }
        return cnt;
    }

}
