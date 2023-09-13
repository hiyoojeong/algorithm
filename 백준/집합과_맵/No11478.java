package 집합과_맵;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

// 서로 다른 부분 문자열의 개수
public class No11478 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();

		HashSet<String> substrs = new HashSet<>();
		for (int i = 0; i < str.length(); i++) {
			for (int j = 1; i + j <= str.length(); j++) {
				String substr = str.substring(i, i + j);
				substrs.add(substr);
			}
		}

		System.out.println(substrs.size());

	}

}
