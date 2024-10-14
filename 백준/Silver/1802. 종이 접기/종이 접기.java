import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 종이 접기
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            String str = br.readLine();
            answer.append(isPossible(str, 0, str.length() - 1) ? "YES\n" : "NO\n");
        }

        System.out.println(answer);
    }

    private static boolean isPossible(String str, int start, int end) {
        if (start == end) {
            return true;
        }

        int mid = (start + end) / 2;
        for (int i = start; i < mid; i++) {
            if (str.charAt(i) == str.charAt(end - i)) { // 중앙을 기준으로 반대편이 반대방향으로 접혀있어야 한다.
                return false;
            }
        }
        return isPossible(str, start, mid - 1) && isPossible(str, mid + 1, end);
    }
}