import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        List<Integer> group = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        int pre = Integer.parseInt(st.nextToken());
        int cnt = 1;
        for (int i = 1; i < N; i++) {
            int now = Integer.parseInt(st.nextToken());
            if (pre == now) {
                group.add(cnt);
                cnt = 1;
            } else {
                cnt++;
                pre = now;
            }
        }
        group.add(cnt);

        int total = group.get(0);
        int max = total;
        if (group.size() > 1) {
            total += group.get(1);
            max = total;
            for (int i = 2; i < group.size(); i++) {
                total += group.get(i);
                max = Math.max(total, max);
                total -= group.get(i - 2);
            }
        }

        System.out.println(max);

    }

}