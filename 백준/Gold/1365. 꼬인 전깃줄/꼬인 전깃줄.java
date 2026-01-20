import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 1365 꼬인 전깃줄
public class Main {

    static List<Integer> lis;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> lis = new ArrayList<>();
        int ans = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            int l = 0;
            int r = lis.size();
            while (l < r) {
                int m = (l + r) / 2;
                if (num >= lis.get(m)) {
                    l = m + 1;
                } else {
                    r = m;
                }
            }

            if (r < lis.size()) {
                lis.set(r, num);
            } else {
                lis.add(num);
            }

            ans = Math.max(lis.size(), ans);
        }

        System.out.println(N - ans);
    }
}