import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer answer = new StringBuffer();

		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			answer.append(checkString(str) + "\n");
		}
		System.out.println(answer);
	}

	public static String checkString(String str) {
		Map<Character, Integer> info = new HashMap<>();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			info.put(ch, info.getOrDefault(ch, 0) + 1);
			if (info.get(ch) == 3) {
				if(i == str.length() - 1 || str.charAt(i + 1) != ch) {
					return "FAKE";
				}
				i++;
				info.remove(ch);
			}
		}

		return "OK";
	}

}
