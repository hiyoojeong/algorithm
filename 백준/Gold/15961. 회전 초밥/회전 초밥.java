import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// 회전 초밥
public class Main {

    static int N, d, k, c;
    static int[] plates;
    static Map<Integer, Integer> info;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 회전 초밥 벨트에 놓인 접시의 수
        d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
        k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접의 수
        c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        plates = new int[N];
        for (int i = 0; i < N; i++) {
            plates[i] = Integer.parseInt(br.readLine());
        }

        info = new HashMap<>();

        // 투 포인터
        int max = 1, cnt = 1;
        int s = 0, e = 1;

        info.put(plates[s], 1);

        boolean isStart = true;
        while (s != 0 || isStart) {
            // 초밥을 연속해서 더 먹을 수 있는 경우
            if (cnt < k) {
                // 현재까지 먹은 접시 수를 늘린다.
                cnt++;

                // 현재까지 먹은 초밥 종류를 업데이트한다.
                int next = plates[e];
                info.put(next, info.getOrDefault(next, 0) + 1);

                // 끝 인덱스를 다음 접시로 이동한다.
                e = (e + 1) % N;
            }
            // 초밥을 연속해서 최대로 다 먹은 경우
            else {
                // 초밥 가짓수 업데이트
                // 추가 초밥이 이미 먹은 것이므로, 먹은 초밥 가짓수는 동일하다.
                if (info.containsKey(c)) {
                    max = Math.max(info.size(), max);
                }
                // 추가 초밥이 새로운 것이므로, 먹은 초밥 가짓수는 1개 더 많아진다.
                else {
                    max = Math.max(info.size() + 1, max);
                }

                // 먹었던 초밥 뱉기
                // 현재까지 먹은 접시 수를 줄인다.
                cnt--;

                // 현재까지 먹은 초밥 종류를 업데이트한다.
                int front = plates[s];
                info.put(front, info.get(front) - 1);
                if(info.get(front) == 0) {
                    info.remove(front);
                }

                // 시작 인덱스를 다음 접시로 이동한다.
                s = (s + 1) % N;
                isStart = false;
            }
        }

        System.out.println(max);

    }
}
