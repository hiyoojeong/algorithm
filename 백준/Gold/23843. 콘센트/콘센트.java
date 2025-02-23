import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 콘센트
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 전자기기의 개수
        int M = Integer.parseInt(st.nextToken()); // 콘센트의 개수

        // 전자기기 충전시간 내림차순
        Queue<Integer> times = new PriorityQueue<>(Collections.reverseOrder());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            times.add(Integer.parseInt(st.nextToken()));
        }

        // 콘센트 총 충전시간 오름차순
        Queue<Integer> outlets = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            outlets.add(0);
        }

        // 총 충전시간이 젤 적은 콘센트에 충전시간이 젤 많은 전자기기를 충전
        while (!times.isEmpty()) {
            outlets.add(outlets.poll() + times.poll());
        }

        // 총 충전시간이 젤 많은 콘센트 총 충전시간 출력
        while(outlets.size() != 1) {
            outlets.poll();
        }
        System.out.println(outlets.poll());
    }
}
