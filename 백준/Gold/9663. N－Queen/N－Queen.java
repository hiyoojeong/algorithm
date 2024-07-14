import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// N-Queen
public class Main {

    static int N, result = 0;
    static int[] chess;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); //  체스판의 크기
        chess = new int[N]; // 체스판 정보: chess[i] = j는 i행 j열을 의미한다.

        dfs(0);

        System.out.println(result);

    }

    public static void dfs(int row) {
        // N개의 Queen을 모두 놓았을 경우, 경우의 수를 업데이트한다.
        if (row == N) {
            result++;
            return;
        }

        // 현재 행에 대해서, Queen을 놓을 열 위치를 찾는다.
        for (int column = 0; column < N; column++) {
            // 현재 열에 Queen을 놓을 수 있으면, 놓는다.
            if (check(row, column)) {
                chess[row] = column;
                dfs(row + 1);
            }
        }

    }

    public static boolean check(int row, int column) {
        for (int preRow = 0; preRow < row; preRow++) {
            int preColumn = chess[preRow];

            // 같은 열에 놓인 Queen이 있으면, Queen을 놓을 수 없다.
            if (preColumn == column) {
                return false;
            }

            // 같은 대각선에 놓인 Queen이 있으면, Queen을 놓을 수 없다.
            if (Math.abs(row - preRow) == Math.abs(column - preColumn)) {
                return false;
            }
        }
        return true;
    }
}