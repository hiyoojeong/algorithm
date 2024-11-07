import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 옥상 정원 꾸미기
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        stack.add(Integer.parseInt(br.readLine()));
        long cnt = 0;
        for (int i = 1; i < N; i++) {
            int next = Integer.parseInt(br.readLine());
            while (!stack.isEmpty() && stack.peek() <= next) { // 현재 빌딩보다 작거나 같으면 못 본다
                stack.pop();
            }
            cnt += stack.size();
            stack.push(next);
        }
        System.out.println(cnt);
    }
}