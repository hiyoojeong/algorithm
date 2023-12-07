import java.util.ArrayList;
import java.util.HashMap;

// 개인정보 수집 유효기간
class No150370 {

	public static void main(String[] args) {
		String today = "2022.05.19";
		String[] terms = { "A 6", "B 12", "C 3" };
		String[] privacies = { "2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C" };

//		String today = "2020.01.01";
//		String[] terms = { "Z 3", "D 5" };
//		String[] privacies = { "2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z" };

		int[] res = solution(today, terms, privacies);
		printResult(res);
	}

	public static int[] solution(String today, String[] terms, String[] privacies) {
		int[] result = null;
		ArrayList<Integer> results = new ArrayList<>();

		// today를 .을 기준으로 split
		// . 은 특수문자로 앞에 \\를 붙여줘야 인식한다.
		String[] todayInfo = today.split("\\.");
		int year = Integer.parseInt(todayInfo[0]);
		int month = Integer.parseInt(todayInfo[1]);
		int day = Integer.parseInt(todayInfo[2]);

		// terms를 공백을 기준으로 split
		// HashMap에 저장하여 term을 바로 검색가능하게 한다.
		HashMap<Character, Integer> termInfo = new HashMap<Character, Integer>();
		for (int i = 0; i < terms.length; i++) {
			String[] tArr = terms[i].split(" ");
			termInfo.put(tArr[0].charAt(0), Integer.parseInt(tArr[1]));
		}

		// privacies를 .과 공백을 기준으로 split
		for (int i = 0; i < privacies.length; i++) {
			String[] privacyInfo = privacies[i].split("\\.| ");
			int y = Integer.parseInt(privacyInfo[0]);
			int m = Integer.parseInt(privacyInfo[1]) - 1;
			int d = Integer.parseInt(privacyInfo[2]);
			char type = privacyInfo[3].charAt(0);

			int term = termInfo.get(type);
			
			y = y + ((m + term) / 12);
			m = ((m + term) % 12) + 1;
			
			System.out.println(y+"."+m+"."+d);
			
			if (year > y) { // 파기할 정보를 저장한다.
				results.add(i + 1);
			} else if (year == y) {
				if (month > m) {
					results.add(i + 1);
				} else if (month == m) {
					if (day >= d) {
						results.add(i + 1);
					}
				}
			}
			
		}

		result = new int[results.size()];
		for (int i = 0; i < results.size(); i++) {
			result[i] = results.get(i);
		}
		return result;
	}

	public static void printResult(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}

}
