import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 용액 합성하기
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int B = Integer.MAX_VALUE; // 0에 가장 가까운 특성값 B
        int s = 0, e = N - 1;

        while (s < e) {
            // 두 용액의 합이 0에 가장 가깝다면, B를 업데이트한다.
            int sum = A[s] + A[e];
            if(Math.abs(sum) < Math.abs(B)) {
                B = sum;
            }

            // 두 용액의 합이 0이면, 탐색 종료
            if(sum == 0) {
                break;
            }
            // 두 용액의 합이 0보다 작으면, 시작 인덱스를 늘려 합을 늘려야한다.
            else if(sum < 0) {
                s++;
            }
            // 두 용액의 합이 0보다 크면, 끝 인덱스를 줄여 합을 줄여야 한다.
            else if(sum > 0) {
                e--;
            }
        }

        System.out.println(B);
    }
}
