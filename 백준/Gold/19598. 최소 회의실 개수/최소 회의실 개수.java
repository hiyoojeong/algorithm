import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 최소 회의실 개수
class Main {

    static class Time {

        long time;
        boolean isStart;

        public Time(long time, boolean isStart) {
            this.time = time;
            this.isStart = isStart;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Queue<Time> pq = new PriorityQueue<>(new Comparator<Time>() {
            @Override
            public int compare(Time o1, Time o2) {
                // 시간이 동일하면, 끝나는 회의가 시작하는 회의보다 앞에 온다.
                if(o1.time == o2.time) {
                    if(o1.isStart && !o2.isStart) return 1;
                    else if(!o1.isStart && o2.isStart) return -1;
                    else return 0;
                }
                return Long.compare(o1.time, o2.time); // 시간 빠른순
            }
        });
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long s = Long.parseLong(st.nextToken());
            long e = Long.parseLong(st.nextToken());
            pq.add(new Time(s, true));
            pq.add(new Time(e, false));
        }

        int cnt = 0, maxCnt = 0;
        while (!pq.isEmpty()) {
            Time time = pq.poll();
            if (time.isStart) { // 진행중인 회의 추가
                cnt++;
            } else { // 진행중인 회의 삭제
                cnt--;
            }

            maxCnt = Math.max(cnt, maxCnt); // 진행중인 최대 회의 수 = 최소 회의실 개수
        }

        System.out.println(maxCnt);
    }
}