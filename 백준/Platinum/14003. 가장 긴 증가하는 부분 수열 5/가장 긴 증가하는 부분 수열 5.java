import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

// 가장 긴 증가하는 부분 수열 5
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> list = new ArrayList<>();
        int[] idx = new int[N];
        for (int i = 0; i < N; i++) {
            int left = 0;
            int right = list.size();
            while (left < right) {
                int mid = (left + right) / 2;
                if (list.get(mid) >= arr[i]) { // 하계
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            idx[i] = right;
            if (right == list.size()) {
                list.add(arr[i]);
            } else {
                list.set(right, arr[i]);
            }
        }

        // 가장 긴 증가하는 부분 수열 저장
        Stack<Integer> stack = new Stack<>();
        int maxIdx = list.size() - 1;
        for (int i = N - 1; i >= 0; i--) {
            if (maxIdx == idx[i]) {
                stack.push(arr[i]);
                maxIdx--;
            }
        }

        sb.append(stack.size() + "\n");
        while(!stack.isEmpty()) {
            sb.append(stack.pop() + " ");
        }

        System.out.println(sb);
    }

}