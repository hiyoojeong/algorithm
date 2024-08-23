import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 2961. 도영이가 만든 맛있는 음식
public class Main {

    static class Flavor {

        int acidity; // 신맛
        int acerbity; // 쓴맛

        public Flavor(int acidity, int acerbity) {
            this.acidity = acidity;
            this.acerbity = acerbity;
        }
    }

    static int N, minDist;
    static Flavor[] flavors;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        flavors = new Flavor[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int acidity = Integer.parseInt(st.nextToken()); // 신맛
            int acerbity = Integer.parseInt(st.nextToken()); // 쓴맛
            flavors[i] = new Flavor(acidity, acerbity);
        }

        minDist = Integer.MAX_VALUE;
        subset(0, 1, 0);

        System.out.println(minDist);
    }

    public static void subset(int cnt, int acidity, int acerbity) {
        if (cnt == N) {
            if (acidity != 1 && acerbity != 0) {
                minDist = Math.min(minDist, Math.abs(acidity - acerbity));
            }
            return;
        }

        subset(cnt + 1, acidity * flavors[cnt].acidity, acerbity + flavors[cnt].acerbity);
        subset(cnt + 1, acidity, acerbity);
    }
}
