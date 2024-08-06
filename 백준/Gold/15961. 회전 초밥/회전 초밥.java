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
    static Map<Integer, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 회전 초밥 벨트에 놓인 접시의 수
        d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
        k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
        c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        plates = new int[N];
        for (int i = 0; i < N; i++) {
            plates[i] = Integer.parseInt(br.readLine());
        }

        map = new HashMap<>();

        // K-1개를 미리 먹어둔다.
        for (int i = 0; i < k - 1; i++) {
            map.put(plates[i], map.getOrDefault(plates[i], 0) + 1);
        }

        // 먹기 -> 업데이트 -> 뱉기를 반복한다.
        int max = 0;
        int e = k - 1;
        for (int s = 0; s < N; s++) {
            int remove = plates[s];
            int eat = plates[e];

            // 먹는다.
            map.put(eat, map.getOrDefault(eat, 0) + 1);

            // 업데이트한다.
            if (map.containsKey(c)) {
                max = Math.max(map.size(), max);
            } else {
                max = Math.max(map.size() + 1, max);
            }

            // 뱉는다.
            map.put(remove, map.get(remove) - 1);
            if (map.get(remove) == 0) {
                map.remove(remove);
            }

            e = (e + 1) % N;
        }

        System.out.println(max);
    }
}
