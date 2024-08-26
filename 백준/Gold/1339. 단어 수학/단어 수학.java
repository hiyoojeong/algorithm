import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

// 단어 수학
public class Main {

	static int N;
	static String[] strs;
	static Map<Character, Integer> chs;

	static List<Character> chList;
	static boolean[] isSelected;

	static int maxTotal;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 단어의 개수
		strs = new String[N];

		// 입력 및 알파벳 정보 저장하기
		chs = new HashMap<>();
		for (int i = 0; i < N; i++) {
			strs[i] = sc.next();
			for (int j = 0; j < strs[i].length(); j++) {
				char ch = strs[i].charAt(j);
				chs.put(ch, chs.getOrDefault(ch, 0) + (int) Math.pow(10, strs[i].length() - j - 1));
			}
		}

		// 리스트로 만들기
		chList = new ArrayList<>();
		for (char ch : chs.keySet()) {
			chList.add(ch);
		}

		// 순열 만들기
		isSelected = new boolean[chList.size()];
		dfs(0, 9);

		System.out.println(maxTotal);

		sc.close();

	}

	public static void dfs(int cnt, int val) {
		if (cnt == chList.size()) {
			// 값 계산
			int total = 0;
			for (char ch : chs.keySet()) {
				total += chs.get(ch);
			}

			// 최대값 업데이트
			maxTotal = Math.max(total, maxTotal);

			return;
		}

		for (int i = 0; i < chList.size(); i++) {
			if (isSelected[i]) {
				continue;
			}

			int origin = chs.get(chList.get(i));
			chs.put(chList.get(i), origin * val);
			isSelected[i] = true;
			dfs(cnt + 1, val - 1);
			chs.put(chList.get(i), origin);
			isSelected[i] = false;
		}
	}

}
