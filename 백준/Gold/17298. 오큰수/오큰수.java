import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 오큰수
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] result = new int[N];
        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.MAX_VALUE);
        for (int i = N - 1; i >= 0; i--) {
            while(arr[i] >= stack.peek()) {
                stack.pop();
            }
            result[i] = stack.peek() == Integer.MAX_VALUE ? -1 : stack.peek();
            stack.push(arr[i]);
        }

        for (int i = 0; i < N; i++) {
            answer.append(result[i] + " ");
        }
        System.out.println(answer);
    }
}