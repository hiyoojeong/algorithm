import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// 과일 탕후루
public class Main {

    static final int maxType = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 과일 개수
        int[] fruits = new int[N]; // 과일 정보

        // 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            fruits[i] = Integer.parseInt(st.nextToken());
        }

        // 투 포인터
        int s = 0, e = 0, cnt = 0, maxCnt = Integer.MIN_VALUE;
        Map<Integer, Integer> picks = new HashMap<>();
        while (e < N) {
            // 이미 선택한 종류의 과일인 경우, 뒤에 과일을 꽂는다.
            if (picks.containsKey(fruits[e])) {
                picks.put(fruits[e], picks.get(fruits[e]) + 1);
                e++;
                cnt++;
            }

            // 새로운 종류의 과일인 경우
            else {
                // 현재 maxType 미만이라면, 뒤에 과일을 꽂는다.
                if (picks.size() < maxType) {
                    picks.put(fruits[e], 1);
                    e++;
                    cnt++;
                }

                // 현재 이미 maxType 이라면, 앞에 꽂은 과일을 뺸다.
                else if (picks.size() == maxType) {
                    int frontCnt = picks.get(fruits[s]);
                    if (frontCnt == 1) {
                        picks.remove(fruits[s]);
                    } else {
                        picks.put(fruits[s], frontCnt - 1);
                    }
                    s++;
                    cnt--;
                }
            }

            maxCnt = Math.max(cnt, maxCnt);
        }

        System.out.println(maxCnt);

    }


}