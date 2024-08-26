import java.util.Scanner;

// 가스관
public class Main {

    static int R, C;
    static char[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력
        R = sc.nextInt();
        C = sc.nextInt();

        map = new char[R + 1][C + 1];
        for (int i = 1; i <= R; i++) {
            String input = sc.next();
            for (int j = 1; j <= C; j++) {
                map[i][j] = input.charAt(j - 1);
            }
        }

        // 탐색
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (map[i][j] == '.') {
                    // 블록 '+' 확인
                    if (up(i, j) && down(i, j) && left(i, j) && right(i, j)) {
                        // 4 방향에 파이프가 존재하는데, 좌우나 상하로 출발지-도착지가 바로 연결된다면 + 파이프가 올 수 없다.
                        if ((map[i - 1][j] != 'M' && map[i + 1][j] != 'Z') && (map[i - 1][j] != 'Z'
                            && map[i + 1][j] != 'M') &&
                            (map[i][j - 1] != 'M' && map[i][j + 1] != 'Z') && (map[i][j - 1] != 'Z'
                            && map[i][j + 1] != 'M')) {
                            System.out.println(i + " " + j + " " + "+");
                            return;
                        }
                    }

                    // 블록 '|' 확인
                    if (up(i, j) && down(i, j)) {
                        System.out.println(i + " " + j + " " + "|");
                        return;
                    }

                    // 블록 '-' 확인
                    if (left(i, j) && right(i, j)) {
                        System.out.println(i + " " + j + " " + "-");
                        return;
                    }

                    // 블록 '1' 확인
                    if (down(i, j) && right(i, j)) {
                        System.out.println(i + " " + j + " " + "1");
                        return;
                    }

                    // 블록 '2' 확인
                    if (up(i, j) && right(i, j)) {
                        System.out.println(i + " " + j + " " + "2");
                        return;
                    }

                    // 블록 '3' 확인
                    if (up(i, j) && left(i, j)) {
                        System.out.println(i + " " + j + " " + "3");
                        return;
                    }

                    // 블록 '4' 확인
                    if (down(i, j) && left(i, j)) {
                        System.out.println(i + " " + j + " " + "4");
                        return;
                    }

                }
            }
        }

        sc.close();
    }

    public static boolean up(int r, int c) {
        if (r - 1 > 0) {
            if (map[r - 1][c] == '|' || map[r - 1][c] == '+' || map[r - 1][c] == '1'
                || map[r - 1][c] == '4' || map[r - 1][c] == 'M' || map[r - 1][c] == 'Z') {
                return true;
            }
        }

        return false;
    }

    public static boolean down(int r, int c) {
        if (r + 1 <= R) {
            if (map[r + 1][c] == '|' || map[r + 1][c] == '+' || map[r + 1][c] == '2'
                || map[r + 1][c] == '3' || map[r + 1][c] == 'M' || map[r + 1][c] == 'Z') {
                return true;
            }
        }

        return false;
    }

    public static boolean left(int r, int c) {
        if (c - 1 > 0) {
            if (map[r][c - 1] == '-' || map[r][c - 1] == '+' || map[r][c - 1] == '1'
                || map[r][c - 1] == '2' || map[r][c - 1] == 'M' || map[r][c - 1] == 'Z') {
                return true;
            }
        }

        return false;
    }

    public static boolean right(int r, int c) {
        if (c + 1 <= C) {
            if (map[r][c + 1] == '-' || map[r][c + 1] == '+' || map[r][c + 1] == '3'
                || map[r][c + 1] == '4' || map[r][c + 1] == 'M' || map[r][c + 1] == 'Z') {
                return true;
            }
        }

        return false;
    }

}
