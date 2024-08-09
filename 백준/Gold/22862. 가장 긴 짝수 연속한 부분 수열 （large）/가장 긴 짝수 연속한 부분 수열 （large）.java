import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

// 가장 긴 짝수 연속한 부분 수열 (large)
public class Main {

    static Map<Integer, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 수열 S의 길이
        int K = Integer.parseInt(st.nextToken()); // 삭제할 수 있는 최대 횟수인 K
        int[] arr = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arr[N] = 1;

        // 투 포인터
        int maxCnt = 0, cnt = 0;
        int s = 0, e = 1;

        if (arr[s] % 2 != 0) { // 홀수면 삭제할 수 있는 횟수 차감
            K--;
        } else { // 짝수면 짝수 개수 추가
            cnt++;
        }

        while (e <= N) {
            // 짝수면 그냥 길이를 늘린다.
            if (arr[e] % 2 == 0) {
                e++;
                cnt++;
            }
            // 홀수면 삭제할 수 있는 횟수를 차감한다.
            else {
                // 더 이상 삭제할 수 없으면, 제일 처음에 삭제한 홀수의 바로 뒤부터 시작한다.
                if (K == 0) {
                    while (true) {
                        if (arr[s] % 2 != 0) { // 홀수의 바로 뒤부터 시작
                            s++;
                            break;
                        } else { // 짝수면 짝수개수 차감
                            s++;
                            cnt--;
                        }
                    }
                    K++;
                }

                K--;
                e++;
            }

            // 연속한 최대 부분 수열 중 가장 긴 길이라면 업데이트한다.
            maxCnt = Math.max(cnt, maxCnt);
        }

        System.out.println(maxCnt);
    }
}
