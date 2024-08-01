import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 먹을 것인가 먹힐 것인가
public class Main {

    static int N, M;
    static int[] a, b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer answer = new StringBuffer();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            // 입력
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            a = new int[N];
            b = new int[M + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                b[i] = Integer.parseInt(st.nextToken());
            }
            b[M] = Integer.MAX_VALUE;

            // 정렬
            Arrays.sort(a);
            Arrays.sort(b);

            int cnts = 0, left = 0;
            for (int i = 0; i < N; i++) {
                // 하한값 인덱스 = 먹을 수 있는 물고기 수
                int idx = lowerbound(left, a[i]);
                // 다음 물고기는 크기가 더 크므로, 최소 지금 먹을 수 있는 물고기 수보다 많이 먹을 수 있다.
                left = idx;

                cnts += idx;
            }

            answer.append(cnts).append("\n");
        }

        System.out.println(answer);
    }

    public static int lowerbound(int left, int target) {
        int right = M;

        while(left < right) {
            int mid = (left + right) / 2;

            if(b[mid] >= target) { // 큰거나 같은 경우만 고른다.
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }

}
