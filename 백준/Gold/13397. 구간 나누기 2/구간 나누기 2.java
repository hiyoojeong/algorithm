import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 구간 나누기 2
public class Main {

    static int N, arr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 배열 길이
        int M = Integer.parseInt(st.nextToken()); // 구간 수

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }

        int left = 0, right = max;
        int ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            int partCnt = getPartCnt(mid);

            if (partCnt >= M) { // 같거나 더 많이 쪼개졌다면, 구간 내 차이를 늘려줘도 됨
                left = mid + 1;
            } else { // 같거나 덜 쪼개다면, 구간 내 차이를 좁혀야 함
                ans = mid;
                right = mid - 1;
            }

        }

        System.out.println(ans);
    }

    private static int getPartCnt(int dist) {
        int partCnt = 0;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);

            if (max - min > dist) {
                partCnt++;
                min = arr[i];
                max = arr[i];
            }
        }

        return partCnt;
    }
}