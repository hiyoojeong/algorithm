

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// 마니또
public class No5107 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int t = 1;; t++) {
			
			int N = Integer.parseInt(br.readLine()); // 사람의 명수
			if (N == 0) {
				break;
			}

			int[][] manito = new int[N][N]; // 마니또 정보
			HashMap<String, Integer> name = new HashMap<>(); // 이름별 인덱스 정보
			int number = 0; // 인덱스

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				String name1 = st.nextToken();
				String name2 = st.nextToken();

				// 이름별 인덱스 부여
				if (!name.containsKey(name1)) {
					name.put(name1, number++);
				}
				if (!name.containsKey(name2)) {
					name.put(name2, number++);
				}

				// 마니또 정보 저장
				manito[name.get(name1)][name.get(name2)] = 1;
			}

			int cnt = 0;
			for (int from = 0; from < N; from++) {
				for (int to = 0; to < N; to++) {
					
					if (manito[from][to] == 1) {
						manito[from][to] = 0;
						int start = from;
						int person = to;

						// person이 다시 start로 돌아오면 하나의 마니또 체인이 끝
						while (start != person) {
							for (int k = 0; k < N; k++) {
								if (manito[person][k] == 1) {
									manito[person][k] = 0;
									person = k;
									break;
								}
							}

						}

						// 마니또 체인 개수 세기
						cnt++;
					}
				}
			}

			System.out.println(t + " " + cnt);
		}

	}

}
