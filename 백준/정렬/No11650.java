package 정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
// 좌표 정렬하기
public class No11650 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] coordinates = new int[N][2];
		
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			coordinates[i][0] = Integer.parseInt(st.nextToken());
			coordinates[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(coordinates, (o1, o2) -> {
			return o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1];
		});

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < N; i++) {
			sb.append(coordinates[i][0] + " " + coordinates[i][1] + "\n");
		}
		
		System.out.println(sb);
	}

}
