import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

// [SWEA] 5653. 줄기세포배양(역량 보충문제)
public class Solution {

    static class Cell {

        int r, c, time, ready, active;

        public Cell(int r, int c, int time, int ready, int active) {
            this.r = r;
            this.c = c;
            this.time = time;
            this.ready = ready;
            this.active = active;
        }

        @Override
        public boolean equals(Object obj) {
            Cell c = (Cell) obj;
            return this.r == c.r && this.c == c.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.r, this.c);
        }

        @Override
        public String toString() {
            return "Cell{" +
                "r=" + r +
                ", c=" + c +
                ", time=" + time +
                ", ready=" + ready +
                ", active=" + active +
                '}';
        }
    }

    static int N, M, K;
    static int[][] map;

    static Queue<Cell> ready; // 비활성세포
    static Queue<Cell> active; // 활성세포
    static Set<Cell> isUsed; // 모든 세포 정보
    static int diedCount; // 죽은세포 개수

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer answer = new StringBuffer();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            // 입력
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 세로
            M = Integer.parseInt(st.nextToken()); // 가로
            K = Integer.parseInt(st.nextToken()); // 배양 시간

            ready = new LinkedList<>();
            active = new PriorityQueue<>(
                (o1, o2) -> -(o1.time - o2.time)); // 생명력 수치가 높은 줄기 세포순서대로 정렬
            isUsed = new HashSet<>();
            diedCount = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int time = Integer.parseInt(st.nextToken());
                    if (time > 0) {
                        Cell cell = new Cell(i, j, time, time, time);
                        ready.add(cell);
                        isUsed.add(cell);
                    }
                }
            }

            // 세포 배양
            for (int i = 0; i < K; i++) {
                cellCulture();
            }

            // 출력
            answer.append("#").append(t).append(" ").append(isUsed.size() - diedCount).append("\n");
        }

        System.out.println(answer);
    }

    public static void cellCulture() {
        int readySize = ready.size();
        int activeSize = active.size();

        // 활성 상태 : 번식
        // 활성 상태 -> 죽은 상태
        Queue<Cell> tempActive = new LinkedList<>(); // 번식 후에도 활성 상태인 세포는 잠시 저장해둔다.
        for (int i = 0; i < activeSize; i++) {
            Cell cell = active.poll();
            cell.active--;

            for (int d = 0; d < 4; d++) {
                int nr = cell.r + dr[d];
                int nc = cell.c + dc[d];
                int nt = cell.time;

                Cell nextCell = new Cell(nr, nc, nt, nt, nt);

                if (isUsed.contains(nextCell)) {
                    continue;
                }

                isUsed.add(nextCell);
                ready.add(nextCell);
            }

            // 활성 상태로 x 시간을 보냈다면, x시간이 지나는 순간 죽은 상태가 된다.
            if (cell.active == 0) {
                diedCount++;
                continue;
            }

            tempActive.add(cell);
        }

        // 번식 후에도 활성 상태인 세포를 활성 성태에 다시 저장한다.
        // 위 과정에서 바로 저장할 경우, PriorityQueue 정렬 방식에 의해 순서가 꼬인다.
        while (!tempActive.isEmpty()) {
            active.add(tempActive.poll());
        }

        // 비활성 상태 -> 활성 상태로 변환
        for (int i = 0; i < readySize; i++) {
            Cell cell = ready.poll();
            cell.ready--;

            // 비활성 상태로 x 시간을 보냈다면, x시간이 지나는 순간 활성 상태가 된다.
            if (cell.ready == 0) {
                active.add(cell);
                continue;
            }

            ready.add(cell);
        }
    }
}