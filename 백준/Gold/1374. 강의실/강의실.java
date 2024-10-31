import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 강의실
public class Main {

    static class Class implements Comparable<Class> {

        int num, time, type;

        public Class(int num, int time, int type) {
            this.num = num;
            this.time = time;
            this.type = type;
        }

        @Override
        public int compareTo(Class o) {
            // 시간이 빠른순
            // 시간이 같으면 type이 빠른순(type이 -1이면 end, type이 1이면 start)
            return this.time == o.time ? this.type - o.type : this.time - o.time;
        }
    }

    static final int START = 1;
    static final int END = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Queue<Class> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            pq.add(new Class(num, start, START)); // 시작 저장
            pq.add(new Class(num, end, END)); // 끝 저장
        }

        int cnt = 0, tmpCnt = 0;
        while (!pq.isEmpty()) {
            Class cur = pq.poll();
            if (cur.type == START) {
                tmpCnt++;
            } else {
                tmpCnt--;
            }

            if(cnt < tmpCnt) {
                cnt = tmpCnt;
            }
        }

        System.out.println(cnt);
    }
}