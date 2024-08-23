
import java.util.Scanner;

// 1873. 상호의 배틀필드
public class Solution {

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static class Tank {
		Pos pos, dir;

		public Tank(Pos pos, Pos dir) {
			this.pos = pos;
			this.dir = dir;
		}

		public void setDir(char ch) {
			if (ch == '^' || ch == 'U') {
				dir.x = -1;
				dir.y = 0;
			} else if (ch == 'v' || ch == 'D') {
				dir.x = 1;
				dir.y = 0;
			} else if (ch == '<' || ch == 'L') {
				dir.x = 0;
				dir.y = -1;
			} else if (ch == '>' || ch == 'R') {
				dir.x = 0;
				dir.y = 1;
			}
		}

		public void move() {
			int nx = pos.x + dir.x;
			int ny = pos.y + dir.y;

			if ((nx < 0 || ny < 0 || nx >= H || ny >= W) || map[nx][ny] != BLANK) {
				return;
			}

			// 다음 위치와 현재 위치 변경
			char tmp = map[pos.x][pos.y];
			map[pos.x][pos.y] = map[nx][ny];
			map[nx][ny] = tmp;

			// 전차 위치 업데이트
			pos.x = nx;
			pos.y = ny;
		}
	}

	static final char BLANK = '.';
	static final char BRICK = '*';
	static final char STEEL = '#';
	static final char WATER = '-';

	static int H, W;
	static char map[][];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		StringBuffer answer = new StringBuffer();
		for (int test_case = 1; test_case <= T; test_case++) {
			H = sc.nextInt(); // 게임 맵의 행
			W = sc.nextInt(); // 게임 맵의 열

			map = new char[H][W]; // 게임 맵
			Tank tank = null; // 탱크

			for (int i = 0; i < H; i++) {
				String input = sc.next();
				for (int j = 0; j < W; j++) {
					char ch = input.charAt(j);
					map[i][j] = ch;
					if (ch == '^' || ch == 'v' || ch == '<' || ch == '>') {
						tank = new Tank(new Pos(i, j), new Pos(0, 0));
						tank.setDir(ch);
					}
				}
			}

			int N = sc.nextInt(); // 사용자가 넣을 입력의 개수
			String input = sc.next();
			for (int i = 0; i < N; i++) {
				char ch = input.charAt(i);

				// 포탄 발사
				if (ch == 'S') {
					int x = tank.pos.x;
					int y = tank.pos.y;

					while (true) {
						int nx = x + tank.dir.x;
						int ny = y + tank.dir.y;

						if ((nx < 0 || ny < 0 || nx >= H || ny >= W) || map[nx][ny] == STEEL) {
							break;
						}

						if (map[nx][ny] == BRICK) {
							map[nx][ny] = BLANK;
							break;
						}

						x = nx;
						y = ny;
					}
				}
				// 전차 이동
				else {
					tank.setDir(ch);
					if (ch == 'U') {
						map[tank.pos.x][tank.pos.y] = '^';
					} else if (ch == 'D') {
						map[tank.pos.x][tank.pos.y] = 'v';
					} else if (ch == 'L') {
						map[tank.pos.x][tank.pos.y] = '<';
					} else if (ch == 'R') {
						map[tank.pos.x][tank.pos.y] = '>';
					}
					tank.move();
				}
			}

			answer.append(String.format("#%d ", test_case));
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					answer.append(map[i][j]);
				}
				answer.append("\n");
			}
		}

		System.out.println(answer);

		sc.close();
	}

}
