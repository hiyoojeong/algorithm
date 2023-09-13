package 정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

// 단어 정렬
public class No1181 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		String[] words = new String[N];
		for (int i = 0; i < N; i++) {
			words[i] = br.readLine();
		}

		Arrays.sort(words, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.length() != o2.length() ? o1.length() - o2.length() : o1.compareTo(o2);
			}
		});

		StringBuffer sb = new StringBuffer();
		String pre = "";
		for (int i = 0; i < N; i++) {
			if (!words[i].equals(pre)) {
				sb.append(words[i] + "\n");
			}
			pre = words[i];
		}
		
		System.out.println(sb);
	}

}
