import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static class Num {

        int number, cnt;

        public Num(int number, int cnt) {
            this.number = number;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        int X = Integer.parseInt(br.readLine());

        Queue<Num> queue = new LinkedList<>();
        queue.add(new Num(X, 0));

        Set<Integer> set = new HashSet<>();
        set.add(X);

        int cnt = 0;
        while (!queue.isEmpty()) {
            Num num = queue.poll();

            if (num.number == 1) {
                cnt = num.cnt;
                break;
            }

            // X가 3으로 나누어 떨어지면, 3으로 나눈다.
            int next = num.number / 3;
            if (num.number % 3 == 0 && !set.contains(next)) {
                queue.add(new Num(next, num.cnt + 1));
                set.add(next);
            }
            // X가 2로 나누어 떨어지면, 2로 나눈다.
            next = num.number / 2;
            if (num.number % 2 == 0 && !set.contains(next)) {
                queue.add(new Num(next, num.cnt + 1));
                set.add(next);
            }
            // 1을 뺀다.
            next = num.number - 1;
            queue.add(new Num(next, num.cnt + 1));
            set.add(next);

        }

        System.out.println(cnt);
    }
}