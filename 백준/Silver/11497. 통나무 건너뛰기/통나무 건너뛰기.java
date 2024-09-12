import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 통나무 건너뛰기
class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < TC; tc++) {
            int N = Integer.parseInt(br.readLine());
            Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                pq.add(Integer.parseInt(st.nextToken()));
            }

            // 큰 수부터 왼쪽에 붙였다, 오른쪽에 붙였다 하면서 높이 차 반영하기
            int left, right;
            left = right = pq.poll();

            int maxDist = 0;
            boolean isLeft = true;
            while (!pq.isEmpty()) {
                int num = pq.poll();
                if (isLeft) {
                    maxDist = Math.max(maxDist, left - num);
                    left = num;
                } else {
                    maxDist = Math.max(maxDist, right - num);
                    right = num;
                }
                isLeft = !isLeft;
            }

            // 다 붙여지고 나서, 가장 왼쪽과 오른쪽의 높이 차도 반영하기
            maxDist = Math.max(maxDist, Math.abs(left - right));

            answer.append(maxDist).append("\n");
        }

        System.out.println(answer);
    }
}