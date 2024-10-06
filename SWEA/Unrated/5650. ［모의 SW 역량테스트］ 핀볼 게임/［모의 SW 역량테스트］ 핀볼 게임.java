import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

// 핀볼 게임
public class Solution {

    static class Pos {

        int r, c, cnt;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Pos(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }

        @Override
        public boolean equals(Object obj) {
            Pos p = (Pos) obj;
            return this.r == p.r && this.c == p.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.r, this.c);
        }
    }

    static int[] dr = {-1, 0, 1, 0}; // 상좌하우
    static int[] dc = {0, -1, 0, 1};
    static int N;
    static int[][] map;
    static Map<Integer, List<Pos>> wormhole;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        StringBuffer answer = new StringBuffer();
        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            map = new int[N][N];
            wormhole = new HashMap<>();
            for (int i = 6; i <= 10; i++) {
                wormhole.put(i, new ArrayList<>());
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                    if (wormhole.containsKey(map[i][j])) {
                        wormhole.get(map[i][j]).add(new Pos(i, j));
                    }
                }
            }

            // (i,j) 위치에서 출발
            int maxScore = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 0) {
                        for (int d = 0; d < 4; d++) {
                            int score = go(i, j, d);
                            maxScore = Math.max(score, maxScore);
                        }
                    }
                }
            }

            answer.append(String.format("#%d %d\n", test_case, maxScore));
        }

        System.out.println(answer);

        sc.close();
    }

    private static int go(int r, int c, int direct) {
        int score = 0;

        int nr = r;
        int nc = c;
        do {
            nr += dr[direct];
            nc += dc[direct];

            // 범위를 벗어났거나 5번 블록을 만난 경우
            if (nr < 0 || nc < 0 || nr >= N || nc >= N || map[nr][nc] == 5) {
                score++;
                direct = (direct + 2) % 4;
                nr += dr[direct];
                nc += dc[direct];
            }

            // 1~4번 블록을 만난 경우
            // 0:상, 1:좌, 2:하, 3:우
            if (map[nr][nc] == 1) {
                score++;
                if (direct == 0 || direct == 3) {
                    direct = (direct + 2) % 4;
                } else if (direct == 1) {
                    direct = 0;
                } else if (direct == 2) {
                    direct = 3;
                }
            } else if (map[nr][nc] == 2) {
                score++;
                if (direct == 2 || direct == 3) {
                    direct = (direct + 2) % 4;
                } else if (direct == 0) {
                    direct = 3;
                } else if (direct == 1) {
                    direct = 2;
                }
            } else if (map[nr][nc] == 3) {
                score++;
                if (direct == 1 || direct == 2) {
                    direct = (direct + 2) % 4;
                } else if (direct == 0) {
                    direct = 1;
                } else if (direct == 3) {
                    direct = 2;
                }
            } else if (map[nr][nc] == 4) {
                score++;
                if (direct == 0 || direct == 1) {
                    direct = (direct + 2) % 4;
                } else if (direct == 2) {
                    direct = 1;
                } else if (direct == 3) {
                    direct = 0;
                }
            }

            // 웜홀을 만난 경우
            if (wormhole.containsKey(map[nr][nc])) {
                List<Pos> list = wormhole.get(map[nr][nc]);
                for (Pos p : list) {
                    if (p.r != nr || p.c != nc) {
                        nr = p.r;
                        nc = p.c;
                        break;
                    }
                }
            }

            // 블랙홀을 만난 경우
            if (map[nr][nc] == -1) {
                return score;
            }
        } while (nr != r || nc != c);

        return score;
    }
}
