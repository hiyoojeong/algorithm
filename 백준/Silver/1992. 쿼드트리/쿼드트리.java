import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 쿼드트리
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] map = new char[N][N];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        System.out.println(compression(map, 0, 0, N));
    }

    public static String compression(char[][] map, int r, int c, int size) {
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (map[i][j] != map[r][c]) { // 전체가 같은 색이 아님
                    int half = size / 2;
                    return "(" + compression(map, r, c, half) + compression(map, r, c + half, half)
                        + compression(map, r + half, c, half) + compression(map, r + half, c + half,
                        half) + ")"; // 왼쪽위, 오른쪽위, 왼쪽아래, 오른쪽아래
                }
            }
        }
        return Character.toString(map[r][c]); // 전체가 같은 색임
    }

}