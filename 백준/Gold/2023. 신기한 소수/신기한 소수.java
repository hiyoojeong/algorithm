import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 2023. 신기한 소수
public class Main {

    static int N;
    static StringBuffer answer;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        answer = new StringBuffer();

        N = Integer.parseInt(br.readLine());
        dfs(0, 0);

        System.out.println(answer);

    }

    public static void dfs(int num, int n) {
        if (n == N) { // N자리 신기한소수 생성
            answer.append(num).append("\n");
            return;
        }

        for (int i = 1; i <= 9; i++) { // 숫자 하나씩 붙이기
            int nextNum = num * 10 + i;
            if (isPrime(nextNum)) { // 붙여도 소수라면 다음 숫자도 붙이기
                dfs(nextNum, n + 1);
            }
        }
    }

    public static boolean isPrime(int num) {
        if (num == 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}
