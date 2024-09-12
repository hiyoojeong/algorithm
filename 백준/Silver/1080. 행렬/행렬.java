import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 행렬
class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[][] a = new boolean[N][M];
        boolean[][] b = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                a[i][j] = (input.charAt(j) == '1' ? true : false);
            }
        }
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                b[i][j] = (input.charAt(j) == '1' ? true : false);
            }
        }

        // 3X3 바꾸기
        int cnt = 0;
        for (int i = 0; i < N - 2; i++) {
            for (int j = 0; j < M - 2; j++) {
                if (a[i][j] != b[i][j]) {
                    toggle(a, i, j);
                    cnt++;
                }
            }
        }

        // 일치 확인
        if (isSame(a, b, N, M)) {
            System.out.println(cnt);
        } else {
            System.out.println(-1);
        }
    }

    public static void toggle(boolean[][] a, int si, int sj) {
        for (int i = si; i < si + 3; i++) {
            for (int j = sj; j < sj + 3; j++) {
                a[i][j] = !a[i][j];
            }
        }
    }

    public static boolean isSame(boolean[][] a, boolean[][] b, int N, int M) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (a[i][j] != b[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}