import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

// 하늘에서 별똥별이 빗발친다
public class Main {

    static class Star {

        int x, y;

        public Star(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            Star p = (Star) obj;
            return this.x == p.x && this.y == p.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.x, this.y);
        }
    }

    static int N, M, L, K;
    static List<Star> stars;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 가로
        M = Integer.parseInt(st.nextToken()); // 세로
        L = Integer.parseInt(st.nextToken()); // 트럼펄린 길이
        K = Integer.parseInt(st.nextToken()); // 별똥별의 수

        stars = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            stars.add(new Star(x, y));
        }

        int answer = 0;
        for (Star s1 : stars) {
            for (Star s2 : stars) {
                // 두 별을 모서리로 갖는 위치를 트램펄린의 좌상단으로 둔다.
                answer = Math.max(answer, getStars(s1.x, s2.y));
            }
        }

        System.out.println(K - answer);

    }

    public static int getStars(int x, int y) {
        int answer = 0;
        for (Star s : stars) {
            // 트램펄린에 튕겨져 나가는 별들의 개수를 센다.
            if (x <= s.x && s.x <= x + L && y <= s.y && s.y <= y + L) {
                answer++;
            }
        }
        return answer;
    }
}
