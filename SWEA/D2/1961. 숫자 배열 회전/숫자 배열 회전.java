import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 숫자 배열 회전
public class Solution {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer answer = new StringBuffer();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 90도
			int[][] arr_90 = rotate(arr);

			// 180도
			int[][] arr_180 = rotate(arr_90);

			// 270도
			int[][] arr_270 = rotate(arr_180);

			answer.append("#").append(t).append("\n");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					answer.append(arr_90[i][j]);
				}
				answer.append(" ");
				for (int j = 0; j < N; j++) {
					answer.append(arr_180[i][j]);
				}
				answer.append(" ");
				for (int j = 0; j < N; j++) {
					answer.append(arr_270[i][j]);
				}
				answer.append("\n");
			}
		}
		
		System.out.println(answer);
	}

	public static int[][] rotate(int[][] arr) {
		int len = arr.length;

		int[][] rotated_arr = new int[len][len];

		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				// 행 -> 뒤에서 열
				// 열 -> 행
				rotated_arr[j][(len - 1) - i] = arr[i][j];
			}
		}

		return rotated_arr;
	}
}
