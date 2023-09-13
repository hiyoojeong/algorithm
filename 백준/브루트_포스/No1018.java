package 브루트_포스;

import java.util.Scanner;

public class No1018 {

	// 체스판 다시 칠하기
	public static void main(String[] args) {
		// 입력
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		char[][] board = new char[N][M];
		for (int i = 0; i < N; i++) {
			String scline = sc.next();
			for (int j = 0; j < M; j++) {
				board[i][j] = scline.charAt(j);
			}
		}
		sc.close();

		// 출력
		System.out.println(solution(N, M, board));
	}

	public static int solution(int N, int M, char[][] board) {
		int answer = Integer.MAX_VALUE;

		// 8X8 체스판을 만들 시작점을 찾는다.
		// 해당 시작점에 대해, 맨 왼쪽 위 칸이 흰색인 경우와 검은색인 경우 다시 칠해야하는 횟수를 구한다.
		// 그 중 최소 횟수를 구한다.
		for (int startX = 0; startX <= N - 8; startX++) {
			for (int startY = 0; startY <= M - 8; startY++) {
				answer = Math.min(answer,
						Math.min(repaint('B', startX, startY, board), repaint('W', startX, startY, board)));
			}
		}

		return answer;
	}

	// 맨 왼쪽 위 칸이 color색인 경우 다시 칠해야하는 횟수를 구한다.
	public static int repaint(char color, int startX, int startY, char[][] board) {
		int cnt = 0;

		for (int i = startX; i < startX + 8; i++) {
			for (int j = startY; j < startY + 8; j++) {
				if (board[i][j] != color)
					cnt++;
				if (j != (startY + 8) - 1)
					color = (color == 'B') ? 'W' : 'B';
			}
		}

		return cnt;
	}

}
