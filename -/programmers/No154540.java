import java.util.*;

// 무인도 여행
public class No154540 {

	static int cnt;

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new String[] { "X591X", "X1X5X", "X231X", "1XXX1" })));
		System.out.println(Arrays.toString(solution(new String[] { "XXX", "XXX", "XXX" })));
	}

	public static int[] solution(String[] maps) {
		int[] answer = null;

		int[][] map = new int[maps.length][maps[0].length()];

		for (int i = 0; i < maps.length; i++) {
			for (int j = 0; j < maps[0].length(); j++) {
				map[i][j] = maps[i].charAt(j) == 'X' ? 0 : Character.getNumericValue(maps[i].charAt(j));
			}
		}

		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < maps.length; i++) {
			for (int j = 0; j < maps[0].length(); j++) {
				if (map[i][j] != 0) {
					cnt = 0;
					dfs(map, i, j);
					list.add(cnt);
				}
			}
		}

		if(list.size() == 0) {
			answer = new int[1];
			answer[0] = -1;
		} else {
			list.sort(Comparator.naturalOrder());
			answer = new int[list.size()];
			for (int i = 0; i < list.size(); i++)
				answer[i] = list.get(i);
		}

		return answer;
	}

	public static void dfs(int[][] map, int r, int c) {
		if (r < 0 || c < 0 || r >= map.length || c >= map[0].length)
			return;

		if (map[r][c] == 0)
			return;

		cnt += map[r][c];
		map[r][c] = 0;
		dfs(map, r + 1, c);
		dfs(map, r - 1, c);
		dfs(map, r, c + 1);
		dfs(map, r, c - 1);
	}
	
	public static void print(int[][] map) {
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("cnt: " + cnt);
	}

}
