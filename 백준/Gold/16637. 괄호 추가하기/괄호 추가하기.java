import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 괄호 추가하기
public class Main {

    static List<Integer> nums;
    static List<Character> ops;
    static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        String equations = br.readLine();

        nums = new ArrayList<>();
        ops = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            char ch = equations.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*') {
                ops.add(ch);
                continue;
            }
            nums.add(Character.getNumericValue(ch));
        }

        dfs(nums.get(0), 0);
        System.out.println(ans);
    }

    public static void dfs(int result, int idx) {
        if (idx >= ops.size()) {
            ans = Math.max(ans, result);
            return;
        }

        // 괄호가 없는 경우
        int res1 = calc(ops.get(idx), result, nums.get(idx + 1));
        dfs(res1, idx + 1);

        // 괄호가 있는 경우
        if (idx + 1 < ops.size()) {
            int tmp = calc(ops.get(idx + 1), nums.get(idx + 1), nums.get(idx + 2));
            int res2 = calc(ops.get(idx), result, tmp);
            dfs(res2, idx + 2);
        }
    }

    public static int calc(char op, int n1, int n2) {
        if (op == '+') {
            return n1 + n2;
        }
        if (op == '-') {
            return n1 - n2;
        }
        if (op == '*') {
            return n1 * n2;
        }
        return -1;
    }
}
