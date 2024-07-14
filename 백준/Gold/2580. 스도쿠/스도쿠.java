import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

// 스도쿠
public class Main {

    static class Blank {

        int r, c;

        public Blank(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int[][] sudoku = new int[9][9];
    static int[][] answer = new int[9][9];
    static List<Blank> blanks = new ArrayList<>();
    static boolean isFull = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = Integer.parseInt(st.nextToken());

                // 빈 칸의 위치를 저장한다.
                if (sudoku[i][j] == 0) {
                    blanks.add(new Blank(i, j));
                }
            }
        }

        // 스도쿠 채우기
        dfs(0);

        // 출력
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(answer[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }

    public static void dfs(int idx) {
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                System.out.print(sudoku[i][j]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println();

        if(isFull) {
            return;
        }

        // 모든 빈 칸을 다 채웠으면, 빈 칸 채우기를 종료한다.
        if (idx == blanks.size()) {
            isFull = true;

            for(int i=0; i<9; i++) {
                for (int j = 0; j < 9; j++) {
                    answer[i][j] = sudoku[i][j];
                }
            }
            return;
        }

        // 현재 채워야 할 빈 칸 정보를 가져온다.
        Blank blank = blanks.get(idx);

        // 이미 사용한 숫자 정보를 저장한다.
        Set<Integer> nums = new HashSet<>();
        for (int i = 0; i < 9; i++) { // 가로, 세로
            nums.add(sudoku[blank.r][i]);
            nums.add(sudoku[i][blank.c]);
        }
        for (int i = 0; i < 3; i++) { // 3X3
            for (int j = 0; j < 3; j++) {
                nums.add(sudoku[3 * (blank.r / 3) + i][3 * (blank.c / 3) + j]);
            }
        }

        // 사용하지 않는 숫자를 빈 칸에 적는다.
        for (int i = 1; i <= 9; i++) {
            if (!nums.contains(i)) {
                sudoku[blank.r][blank.c] = i;
                dfs(idx + 1);
                sudoku[blank.r][blank.c] = 0;
            }
        }
    }


}