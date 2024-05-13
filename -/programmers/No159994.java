// 카드 뭉치
public class No159994 {

	static String answer = "";

	public static void main(String[] args) {
		System.out.println(solution(new String[] { "i", "drink", "water" }, new String[] { "want", "to" },
				new String[] { "i", "want", "to", "drink", "water" }));
		System.out.println(solution(new String[] { "i", "water", "drink" }, new String[] { "want", "to" },
				new String[] { "i", "want", "to", "drink", "water" }));
	}

	public static String solution(String[] card1, String[] card2, String[] goal) {
		answer = "No";
		rec(card1, 0, card2, 0, goal, 0);
		return answer;
	}

	public static void rec(String[] card1, int pos1, String[] card2, int pos2, String[] goal, int pos) {
		if (pos == goal.length) {
			answer = "Yes";
			return;
		}

		if (pos1 < card1.length) {
			if (card1[pos1].equals(goal[pos])) {
				rec(card1, pos1 + 1, card2, pos2, goal, pos + 1);
			}
		}

		if (pos2 < card2.length) {
			if (card2[pos2].equals(goal[pos])) {
				rec(card1, pos1, card2, pos2 + 1, goal, pos + 1);
			}
		}

	}

}
