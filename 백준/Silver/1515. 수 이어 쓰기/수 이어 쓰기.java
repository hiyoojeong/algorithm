import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 수 이어 쓰기
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = br.readLine();

		// N의 최소값
		int num = 0;

		// 찾으려는 수의 인덱스 번호
		int idx = 0;

		while (true) {
			num++;
			String numStr = String.valueOf(num);

			// N 값의 앞부분부터 찾으려는 수가 있는지 확인한다.
			for (int i = 0; i < numStr.length(); i++) {
				if (numStr.charAt(i) == str.charAt(idx)) {
					idx++;
				}
				// 찾으려는 수를 모두 확인했으면, 반복을 종료한다.
				if (idx == str.length()) {
					System.out.println(num);
					return;
				}
			}
		}


	}

}
