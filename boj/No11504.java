

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 돌려 돌려 돌림판
public class No11504 {

	static int[] board;
	static int numSize;
	static int boardSize;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer answer = new StringBuffer();

		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			boardSize = Integer.parseInt(st.nextToken());
			numSize = Integer.parseInt(st.nextToken());

			board = new int[boardSize];
			int x = 0, y = 0;

			// x
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < numSize; j++) {
				x = x * 10 + Integer.parseInt(st.nextToken());
			}

			// y
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < numSize; j++) {
				y = y * 10 + Integer.parseInt(st.nextToken());
			}

			// 돌림판 숫자 정보 저장
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < boardSize; j++) {
				board[j] = Integer.parseInt(st.nextToken());
			}

			// 조건에 만족하는 z 개수 구하기
			answer.append(getZ(x, y) + "\n");

		}

		System.out.println(answer);

	}

	public static int getZ(int x, int y) {
		int count = 0;

		for (int i = 0; i < boardSize; i++) {
			int z = 0;

			for (int j = i, k = 0; k < numSize; j = (j + 1) % boardSize, k++) {
				z = z * 10 + board[j];
			}


			if (x <= z && z <= y) {
				count++;
			}
		}

		return count;
	}

}
