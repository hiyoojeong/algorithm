// 광물 캐기
public class No172927 {

	static int answer;

	public static void main(String[] args) {
		System.out.println(solution(new int[] { 1, 3, 2 },
				new String[] { "diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone" }));
		System.out.println(solution(new int[] { 0, 1, 1 }, new String[] { "diamond", "diamond", "diamond", "diamond",
				"diamond", "iron", "iron", "iron", "iron", "iron", "diamond" }));
	}

	public static int solution(int[] picks, String[] minerals) {
		answer = Integer.MAX_VALUE;
		dfs(0, null, 0, minerals, 0, picks[0], picks[1], picks[2]);
		return answer;
	}

	// tool: 선택한 곡괭이
	// tool_cnt: 선택한 곡괭이로 연속해서 광물을 캔 횟수
	// minerals: 광물의 순서
	// minerals_cnt: 현재 캘 광물의 순서 인덱스
	// diamond, iron, stone: 각 곡괭이의 수
	public static void dfs(int fatigue, String tool, int tool_cnt, String[] minerals, int minerals_cnt, int diamond,
			int iron, int stone) {

		// 더 사용할 곡괭이가 없거나 광산에 있는 모든 광물을 캘 때까지 과정을 반복한다.
		if ((diamond == 0 && iron == 0 && stone == 0 && tool_cnt == 0) || (minerals_cnt == minerals.length)) {
			if (fatigue < answer)
				answer = fatigue;
			return;
		}

		// 곡괭이를 하나 선택한다.
		if (tool_cnt == 0) {
			if (diamond > 0)
				dfs(fatigue, "diamond", (tool_cnt + 1) % 6, minerals, minerals_cnt, diamond - 1, iron, stone);
			if (iron > 0)
				dfs(fatigue, "iron", (tool_cnt + 1) % 6, minerals, minerals_cnt, diamond, iron - 1, stone);
			if (stone > 0)
				dfs(fatigue, "stone", (tool_cnt + 1) % 6, minerals, minerals_cnt, diamond, iron, stone - 1);
		}

		// 선택한 곡괭이로 광물 5개를 연속으로 캔다.
		if (tool_cnt > 0) {
			if (tool.equals("diamond")) {
				dfs(fatigue + 1, tool, (tool_cnt + 1) % 6, minerals, minerals_cnt + 1, diamond, iron, stone);
			}
			if (tool.equals("iron")) {
				if (minerals[minerals_cnt].equals("diamond"))
					dfs(fatigue + 5, tool, (tool_cnt + 1) % 6, minerals, minerals_cnt + 1, diamond, iron, stone);
				else
					dfs(fatigue + 1, tool, (tool_cnt + 1) % 6, minerals, minerals_cnt + 1, diamond, iron, stone);
			}
			if (tool.equals("stone")) {
				if (minerals[minerals_cnt].equals("diamond"))
					dfs(fatigue + 25, tool, (tool_cnt + 1) % 6, minerals, minerals_cnt + 1, diamond, iron, stone);
				else if (minerals[minerals_cnt].equals("iron"))
					dfs(fatigue + 5, tool, (tool_cnt + 1) % 6, minerals, minerals_cnt + 1, diamond, iron, stone);
				else if (minerals[minerals_cnt].equals("stone"))
					dfs(fatigue + 1, tool, (tool_cnt + 1) % 6, minerals, minerals_cnt + 1, diamond, iron, stone);
			}
		}
	}

	public static int solution_fail(int[] picks, String[] minerals) {
		// answer 초기화
		answer = Integer.MAX_VALUE;

		// 각 곡괭이로 광물을 캘 때의 피로도
		int[][] degree = { { 1, 1, 1 }, { 5, 1, 1 }, { 25, 5, 1 } };

		// 곡괭이 배열
		int[] picks_num = new int[picks[0] + picks[1] + picks[2]];
		int idx = 0;
		for (int i = 0; i < picks.length; i++) {
			for (int j = 0; j < picks[i]; j++) {
				picks_num[idx++] = i;
			}
		}

		// 광물 배열
		int[] minerals_num = new int[minerals.length];
		for (int i = 0; i < minerals.length; i++) {
			switch (minerals[i]) {
			case "diamond":
				minerals_num[i] = 0;
				break;
			case "iron":
				minerals_num[i] = 1;
				break;
			case "stone":
				minerals_num[i] = 2;
				break;
			default:
				break;
			}
		}

		permutation(picks_num, new boolean[picks_num.length], new int[picks_num.length], minerals_num, degree, 0);
		return answer;
	}

	// 순열
	public static void permutation(int[] picks_num, boolean[] visited, int[] output, int[] minerals_num, int[][] degree,
			int pos) {
		// 곡괭이 사용 순서를 지정했다면, 해당 곡괭이 순서로 광물을 캘 때 피로도를 구한다.
		if (pos == picks_num.length) {
			int i = 0, j = 0;

			int cnt = 0; // 피로도
			int picks_cnt; // 곡괭이 사용 횟수 (광물은 연속으로 5개까지만 캘 수 있다.)

			for (; i < picks_num.length; i++) {
				picks_cnt = 0;
				for (; j < minerals_num.length; j++) {
					if (picks_cnt++ == 5)
						break;
					cnt += degree[output[i]][minerals_num[j]];
				}
			}

			// 최소한의 피로도라면 answer 업데이트
			if (cnt < answer)
				answer = cnt;

			return;
		}

		// 곡괭이 사용 순서를 지정한다.
		for (int i = 0; i < picks_num.length; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			output[pos] = picks_num[i];
			permutation(picks_num, visited, output, minerals_num, degree, pos + 1);
			visited[i] = false;
		}
	}
}
