import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 13334 철로

public class Main {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        List<int[]> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int h = Integer.parseInt(st.nextToken());
            int o = Integer.parseInt(st.nextToken());

            if (h < o) {
                list.add(new int[]{h, o});
            } else {
                list.add(new int[]{o, h});
            }
        }

        int d = Integer.parseInt(br.readLine());
        list.sort((o1, o2) -> o1[1] - o2[1]);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int ans = 0;

        for (int[] pos : list) {
            if (pos[1] - pos[0] > d) { // 현재 선분 자체가 d를 넘어간 경우
                continue;
            }
            pq.offer(pos[0]);
            while (!pq.isEmpty()) {
                if (pos[1] - pq.peek() <= d) {
                    break;
                }
                pq.poll(); // 큐 선분 시작점 ~ 현재 선분 끝점이 d를 넘어간 경우, 큐 선분 제거
            }
            ans = Math.max(ans, pq.size());
        }

        System.out.println(ans);
    }
}