

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int answer = 0;

		int N = Integer.parseInt(br.readLine());
		int maxSize = Integer.MIN_VALUE;

		List<List<Integer>> files = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			List<Integer> file = new ArrayList<Integer>();

			while (true) {
				int value = Integer.parseInt(st.nextToken());
				file.add(value);

				if (value == -1) {
					break;
				}
			}

			maxSize = (file.size() > maxSize) ? file.size() : maxSize;
			files.add(file);
		}

		for (int row = 0; row < maxSize; row++) {
			boolean isDifferent = true;
			HashSet<Integer> values = new HashSet<>();
			
			for (int f = 0; f < N; f++) {
				List<Integer> file = files.get(f);
				if (file.size() > row) {
					if (values.contains(file.get(row))) {
						answer = row + 1;
						isDifferent = false;
						break;
					}
					
					values.add(file.get(row));
					
				}
			}

			if (isDifferent) {
				break;
			}
		}

		System.out.println(answer + 1);

	}

}
