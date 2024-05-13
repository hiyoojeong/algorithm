import java.util.*;

// 미로 탈출

class Node2 {
	int x, y, cnt;

	Node2(int x, int y) {
		this.x = x;
		this.y = y;
		this.cnt = 0;
	}

	Node2(int x, int y, int cnt) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
}

public class No159993 {

	public static void main(String[] args) {
		System.out.println(solution(new String[] { "SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE" }));
		System.out.println(solution(new String[] { "LOOXS", "OOOOX", "OOOOO", "OOOOO", "EOOOO" }));
	}

	public static int solution(String[] maps) {
		int answer = 0;

		Node2 s = null, l = null, e = null;
		for (int i = 0; i < maps.length; i++) {
			for (int j = 0; j < maps[0].length(); j++) {
				if (maps[i].charAt(j) == 'S') {
					s = new Node2(i, j);
				} else if (maps[i].charAt(j) == 'L') {
					l = new Node2(i, j);
				} else if (maps[i].charAt(j) == 'E') {
					e = new Node2(i, j);
				}
			}
		}

		int cnt;
		
		cnt = bfs(maps, s, l);
		if(cnt == 0) return -1;
		answer+=cnt;
		
		cnt = bfs(maps, l, e);
		if(cnt == 0) return -1;
		answer+=cnt;

		return answer;
	}
	
	public static int bfs(String[] maps, Node2 start, Node2 end) {
		int[] x = { 1, -1, 0, 0 };
		int[] y = { 0, 0, 1, -1 };

		LinkedList<Node2> queue = new LinkedList<>();
		boolean[][] check = new boolean[maps.length][maps[0].length()];
		
		queue.add(start);
		check[start.x][start.y] = true;

		while (queue.size() != 0) {
			Node2 node = queue.poll();

			if (node.x == end.x && node.y == end.y) {
				return node.cnt;
			}

			for (int i = 0; i < 4; i++) {
				int nextx = node.x + x[i];
				int nexty = node.y + y[i];
				int nextcnt = node.cnt + 1;

				if (nextx >= 0 && nexty >= 0 && nextx < maps.length && nexty < maps[0].length()) {
					if (!check[nextx][nexty] && maps[nextx].charAt(nexty) != 'X') {
						queue.add(new Node2(nextx, nexty, nextcnt));
						check[nextx][nexty] = true;
					}
				}
			}
		}
		return 0;
	}

//	public static int solution_fail(String[] maps) {
//		int answer = 0;
//
//		Node s = null, l = null, e = null;
//		for (int i = 0; i < maps.length; i++) {
//			for (int j = 0; j < maps[0].length(); j++) {
//				if (maps[i].charAt(j) == 'S') {
//					s = new Node(i, j);
//				} else if (maps[i].charAt(j) == 'L') {
//					l = new Node(i, j);
//				} else if (maps[i].charAt(j) == 'E') {
//					e = new Node(i, j);
//				}
//			}
//		}
//
//		cnt = Integer.MAX_VALUE;
//		dfs(maps, new boolean[maps.length][maps[0].length()], s, l, 0);
////		System.out.println("s->l cnt : " + cnt);
//		answer += cnt;
//
//		if (cnt == Integer.MAX_VALUE)
//			return -1;
//
//		cnt = Integer.MAX_VALUE;
//		dfs(maps, new boolean[maps.length][maps[0].length()], l, e, 0);
////		System.out.println("l->e cnt : " + cnt);
//		answer += cnt;
//
//		if (cnt == Integer.MAX_VALUE)
//			return -1;
//
//		return answer;
//	}
//
//	public static void dfs(String[] maps, boolean[][] check, Node cur, Node tar, int c) {
////		System.out.println("x: " + cur.x + " / y:" + cur.y);
//
//		if (cur.x < 0 || cur.y < 0 || cur.x >= maps.length || cur.y >= maps[0].length()) {
////			System.out.println("범위 벗어남");
//			return;
//		}
//
//		if (check[cur.x][cur.y] || maps[cur.x].charAt(cur.y) == 'X') {
////			System.out.println("갈 수 없는 길");
//			return;
//		}
//
//		if (cur.x == tar.x && cur.y == tar.y) {
////			System.out.println("도착");
//			if (c < cnt)
//				cnt = c;
//			return;
//		}
//
//		check[cur.x][cur.y] = true;
//		dfs(maps, check, new Node(cur.x + 1, cur.y), tar, c + 1);
//		dfs(maps, check, new Node(cur.x - 1, cur.y), tar, c + 1);
//		dfs(maps, check, new Node(cur.x, cur.y + 1), tar, c + 1);
//		dfs(maps, check, new Node(cur.x, cur.y - 1), tar, c + 1);
//		check[cur.x][cur.y] = false;
//	}
}
