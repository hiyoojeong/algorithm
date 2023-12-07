

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 암호화된 비밀번호
public class No9549 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer answer = new StringBuffer();
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			String crypt = br.readLine();
			String origin = br.readLine();
			int lenCrypt = crypt.length();
			int lenOrigin = origin.length();
			int ordA = (int) 'a';

			// 원래 문자열 알파벳 정보 저장
			int[] originList = new int[26];
			for (int i = 0; i < lenOrigin; i++) {
				originList[(int) origin.charAt(i) - ordA]++;
			}
			
			// 암호화된 문자열 알파벳 정보 저장 (원래 문자열 길이만큼만 우선 저장)
			int[] cryptList = new int[26];
			for (int i = 0; i < lenOrigin; i++) {
				cryptList[(int) crypt.charAt(i) - ordA]++;
			}

			boolean isIn = false;
			for (int i = lenOrigin; i < lenCrypt; i++) {
				if (isEqual(cryptList, originList)) {
					isIn = true;
					break;
				}

				cryptList[(int) crypt.charAt(i - lenOrigin) - ordA]--; // 슬라이딩 윈도우 가장 앞 쪽에 있는 알파벳 정보 삭제
				cryptList[(int) crypt.charAt(i) - ordA]++; // 슬라이딩 윈도우 가장 뒤 쪽에 있는 알파벳 정보 추가
			}

			if (isEqual(cryptList, originList)) {
				isIn = true;
			}

			answer.append(isIn ? "YES\n" : "NO\n");
		}
		
		System.out.println(answer);
	}

	private static boolean isEqual(int[] arr1, int[] arr2) {
		for (int i = 0; i < 26; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}
}
