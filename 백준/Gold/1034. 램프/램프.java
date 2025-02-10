import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 램프
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[] lamp = new String[N];
        for (int i = 0; i < N; i++) {
            lamp[i] = br.readLine();
        }

        int K = Integer.parseInt(br.readLine()); // 스위치를 누르는 횟수

        int max = 0;
        for (int i = 0; i < N; i++) {
            // 켜야하는 램프수 카운팅
            int zeroCnt = 0;
            for (int j = 0; j < M; j++) {
                if (lamp[i].charAt(j) == '0') {
                    zeroCnt++;
                }
            }

            // 켜야하는 램프수가 K보다 크거나, 홀짝이 안 맞으면 행전체 켤 수 없음
            if (zeroCnt > K || zeroCnt % 2 != K % 2) {
                continue;
            }

            // 동일 구조를 가지고 있는 행은 켤 수 있으므로, 카운팅
            int cnt = 1;
            for (int k = i + 1; k < N; k++) {
                if (lamp[i].equals(lamp[k])) {
                    cnt++;
                }
            }

            max = Math.max(cnt, max);
        }

        System.out.println(max);
    }

}
