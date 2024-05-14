
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// 마니또
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int t = 1;; t++) {

			int N = Integer.parseInt(br.readLine());
			if (N == 0) {
				break;
			}

			int[][] manito = new int[N][N];
			HashMap<String, Integer> name = new HashMap<>();
			int number = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				String name1 = st.nextToken();
				String name2 = st.nextToken();

				if (!name.containsKey(name1)) {
					name.put(name1, number++);
				}
				if (!name.containsKey(name2)) {
					name.put(name2, number++);
				}

				manito[name.get(name1)][name.get(name2)] = 1;
			}

			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (manito[i][j] == 1) {
						manito[i][j] = 0;
						int start = i;
						int person = j;

						while (start != person) {
							for (int k = 0; k < N; k++) {
								if (manito[person][k] == 1) {
									manito[person][k] = 0;
									person = k;
									break;
								}
							}

						}

						cnt++;
					}
				}
			}

			System.out.println(t + " " + cnt);
		}

	}

}
