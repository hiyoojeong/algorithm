import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 2494 탑

public class Main {

    static class Top {
        int h;
        int pos;

        Top(int h, int pos) {
            this.h = h;
            this.pos = pos;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        Stack<Top> stack = new Stack<>();

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int h = Integer.parseInt(st.nextToken());
            while (!stack.isEmpty() && stack.peek().h < h) {
                stack.pop();
            }
            sb.append(stack.isEmpty() ? "0" : stack.peek().pos).append(" ");
            stack.add(new Top(h, i));
        }

        System.out.println(sb);
    }
}