import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] match;
	static int[][] worldCup;

	static final int WIN = 0, DRAW = 1, LOSE = 2;
	static boolean check;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer answer = new StringBuffer();
		StringTokenizer st;

		match = new int[15][2];
		worldCup = new int[6][3];

		int index = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = i + 1; j < 6; j++) {
				match[index][0] = i;
				match[index][1] = j;
				index++;
			}
		}

		for (int T = 0; T < 4; T++) {
			st = new StringTokenizer(br.readLine());

			check = true;
			for (int i = 0; i < 6; i++) {
				worldCup[i][WIN] = Integer.parseInt(st.nextToken());
				worldCup[i][DRAW] = Integer.parseInt(st.nextToken());
				worldCup[i][LOSE] = Integer.parseInt(st.nextToken());

				if (worldCup[i][WIN] + worldCup[i][DRAW] + worldCup[i][LOSE] != 5) {
					check = false;
					break;
				}
			}

			if (check) {
				check = false;
				backTracking(0);
			}

			if (check) {
				answer.append("1 ");
			} else {
				answer.append("0 ");
			}
		}

		System.out.println(answer);

	}

	public static void backTracking(int cnt) {
		if (check) {
			return;
		}

		if (cnt == 15) {
			check = true;
			return;
		}

		int myTeam = match[cnt][0];
		int enemyTeam = match[cnt][1];

		if (worldCup[myTeam][WIN] > 0 && worldCup[enemyTeam][LOSE] > 0) {
			worldCup[myTeam][WIN]--;
			worldCup[enemyTeam][LOSE]--;
			backTracking(cnt + 1);
			worldCup[myTeam][WIN]++;
			worldCup[enemyTeam][LOSE]++;
		}

		if (worldCup[myTeam][DRAW] > 0 && worldCup[enemyTeam][DRAW] > 0) {
			worldCup[myTeam][DRAW]--;
			worldCup[enemyTeam][DRAW]--;
			backTracking(cnt + 1);
			worldCup[myTeam][DRAW]++;
			worldCup[enemyTeam][DRAW]++;
		}

		if (worldCup[myTeam][LOSE] > 0 && worldCup[enemyTeam][WIN] > 0) {
			worldCup[myTeam][LOSE]--;
			worldCup[enemyTeam][WIN]--;
			backTracking(cnt + 1);
			worldCup[myTeam][LOSE]++;
			worldCup[enemyTeam][WIN]++;
		}

	}
}
