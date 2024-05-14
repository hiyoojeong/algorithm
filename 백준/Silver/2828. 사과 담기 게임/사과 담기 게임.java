import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int J = Integer.parseInt(br.readLine());

		int start = 1, end = start + M - 1;
		int answer = 0, move = 0;
		for (int i = 0; i < J; i++) {
			int pos = Integer.parseInt(br.readLine());

			if (pos >= start && pos <= end) {
				continue;
			}

			if (pos < start) {
				move = (start - pos);
				answer += move;

				start -= move;
				end -= move;
			} else if (pos > end) {
				move = (pos - end);
				answer += move;

				start += move;
				end += move;
			}

		}
		System.out.println(answer);

	}
}
