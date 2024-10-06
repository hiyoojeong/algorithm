import java.util.Scanner;

// 상호의 배틀필드
public class Solution {

    static class Pos {

        int r, c, d;

        public Pos(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    static int H, W;
    static char[][] map;
    static Pos tank;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        StringBuffer answer = new StringBuffer();
        for (int test_case = 1; test_case <= T; test_case++) {
            // 입력
            H = sc.nextInt();
            W = sc.nextInt();
            map = new char[H][W];
            tank = null;
            for (int i = 0; i < H; i++) {
                String input = sc.next();
                for (int j = 0; j < W; j++) {
                    map[i][j] = input.charAt(j);
                    if (map[i][j] == '^' || map[i][j] == 'v' || map[i][j] == '<'
                        || map[i][j] == '>') {
                        tank = new Pos(i, j, conventToIdx(map[i][j]));
                        map[i][j] = '.';
                    }
                }
            }

            // 명령어 처리
            int N = sc.nextInt();
            String input = sc.next();
            for (int i = 0; i < N; i++) {
                char op = input.charAt(i);
                if (op == 'S') {
                    shoot();
                } else {
                    tank.d = conventToIdx(op); // 방향바꾸기
                    move();
                }
            }

            // 출력
            answer.append(String.format("#%d ", test_case));
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (tank.r == i && tank.c == j) {
                        answer.append(conventToChar(tank.d));
                    } else {
                        answer.append(map[i][j]);
                    }
                }
                answer.append("\n");
            }
        }

        System.out.println(answer);

        sc.close();
    }

    static int conventToIdx(char direct) {
        if (direct == '^' || direct == 'U') {
            return 0;
        } else if (direct == 'v' || direct == 'D') {
            return 1;
        } else if (direct == '<' || direct == 'L') {
            return 2;
        } else if (direct == '>' || direct == 'R') {
            return 3;
        }
        return -1;
    }

    static char conventToChar(int direct) {
        if (direct == 0) {
            return '^';
        } else if (direct == 1) {
            return 'v';
        } else if (direct == 2) {
            return '<';
        } else if (direct == 3) {
            return '>';
        }
        return ' ';
    }

    static void move() {
        int nr = tank.r + dr[tank.d];
        int nc = tank.c + dc[tank.d];

        // 게임 맵 밖이면 이동 불가능
        if (nr < 0 || nc < 0 || nr >= H || nc >= W) {
            return;
        }
        // 평지가 아니면 이동 불가능
        if (map[nr][nc] != '.') {
            return;
        }

        tank.r = nr;
        tank.c = nc;
    }

    static void shoot() {
        int nr = tank.r;
        int nc = tank.c;

        while (true) {
            nr += dr[tank.d];
            nc += dc[tank.d];

            // 게임 맵 밖으로 나간 경우
            if (nr < 0 || nc < 0 || nr >= H || nc >= W) {
                break;
            }
            // 강철로 만들어진 벽에 충돌한 경우
            if (map[nr][nc] == '#') {
                break;
            }
            // 벽돌로 만들어진 벽에 충돌한 경우
            if (map[nr][nc] == '*') {
                map[nr][nc] = '.'; // 벽이 파괴되어 칸은 평지가 됨
                break;
            }
        }
    }
}
