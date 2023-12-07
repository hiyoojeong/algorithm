import java.util.HashMap;


// 전화번호 목록
// Hash로 푸는 게 정확성, 효율성이 높게 나왔음
public class No42577 {

	public static void main(String[] args) {
		String[] phone_book = { "119", "97674223", "1195524421" };
//		String[] phone_book = { "123", "456", "789" };
//		String[] phone_book = { "12", "123", "1235", "567", "88" };

		System.out.println(solution(phone_book));
		System.out.println(solution_hash(phone_book));
	}

	public static boolean solution(String[] phone_book) {
		int N = phone_book.length;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i != j) {
					String tmp = phone_book[j].replaceFirst(phone_book[i], "");

					if (!phone_book[j].equals(tmp))
						return false;
				}
			}
		}

		return true;
	}

	public static boolean solution_hash(String[] phone_book) {
		int N = phone_book.length;

		HashMap<String, Integer> map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			map.put(phone_book[i], 1);
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<phone_book[i].length(); j++) {
				if(map.containsKey(phone_book[i].substring(0, j)))
					return false;
			}
		}

		return true;
	}

}
