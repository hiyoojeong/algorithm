import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 파일 합치기 3
class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < TC; tc++) {
            int K = Integer.parseInt(br.readLine());
            Queue<Long> file = new PriorityQueue<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                file.add(Long.parseLong(st.nextToken()));
            }

            long cost = 0;
            while (file.size() > 1) {
                long size = file.poll() + file.poll();
                cost += size; // 파일을 합치는 비용
                file.add(size); // 새롭게 생성된 파일
            }

            answer.append(cost + "\n");
        }

        System.out.println(answer);
    }
}