import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Moo 게임
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int k = 0, len = 3;
        while (N >= len) {
            k++;
            len = 2 * len + (k + 3);
        }

        char result = dfs(N, k, len, 0);
        System.out.println(result);
    }

    private static char dfs(int N, int k, int len, int start) {
        int preLen = (len - (k + 3)) / 2; // S(k-1)의 길이

        // 앞부분
        if (start + preLen >= N) {
            return dfs(N, k - 1, preLen, start);
        }

        // 중간
        else if (start + preLen + k + 3 >= N) {
            if (start + preLen + 1 == N) { // 첫 글자면 m
                return 'm';
            } else { // 나머지 글자면 o
                return 'o';
            }
        }

        // 뒷부분
        else { // start + preLen + k + 3 + preLen <= N
            return dfs(N, k - 1, preLen, start + preLen + k + 3);
        }
    }
}