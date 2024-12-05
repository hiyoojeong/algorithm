import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 시간 관리
public class Main {

    static class Task {
        int time, end;

        public Task(int time, int end) {
            this.time = time;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Queue<Task> q = new PriorityQueue<>((o1, o2) -> o2.end - o1.end);

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            q.add(new Task(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int latestEnd = Integer.MAX_VALUE; // 뒷일부터 해결했을 때, 끝내야 되는 시간
        while(!q.isEmpty()) {
            Task t = q.poll();
            latestEnd = Math.min(latestEnd, t.end) - t.time;
            if(latestEnd < 0) {
                latestEnd = -1;
                break;
            }
        }

        System.out.println(latestEnd);
    }
}