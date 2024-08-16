import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 집합
public class Main {

	static int flag = 0;
	static StringBuffer answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		answer = new StringBuffer();

		int M = Integer.parseInt(br.readLine()); // 수행해야 하는 연산의 수
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String instruction = st.nextToken();

			if (instruction.equals("add")) {
				int value = Integer.parseInt(st.nextToken());
				add(value);
			} else if (instruction.equals("remove")) {
				int value = Integer.parseInt(st.nextToken());
				remove(value);
			} else if (instruction.equals("check")) {
				int value = Integer.parseInt(st.nextToken());
				check(value);
			} else if (instruction.equals("toggle")) {
				int value = Integer.parseInt(st.nextToken());
				toggle(value);
			} else if (instruction.equals("all")) {
				all();
			} else if (instruction.equals("empty")) {
				empty();
			}
		}

		System.out.println(answer);
	}

	public static void add(int value) {
		flag |= (1 << value); // 해당 자리에 1을 or 연산
	}

	public static void remove(int value) {
		flag |= (1 << value); // 해당 자리에 1을 or 연산
		flag ^= (1 << value); // 해당 자리에 1을 xor 연산
	}

	public static void check(int value) {
		if ((flag & (1 << value)) > 0) { // 해당자리에 1을 and 연산
			answer.append(1);
		} else {
			answer.append(0);
		}
		answer.append("\n");
	}

	public static void toggle(int value) {
		if ((flag & (1 << value)) > 0) { // 해당자리에 1을 and 연산
			remove(value);
		} else {
			add(value);
		}
	}

	public static void all() {
		for (int i = 1; i <= 20; i++) {
			add(i);
		}
	}

	public static void empty() {
		flag = 0;
	}

}
