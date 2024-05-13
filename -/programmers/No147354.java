import java.util.ArrayList;

// 테이블 해시
public class No147354 {

	public static void main(String[] args) {
		int[][] data = { { 2, 2, 6 }, { 1, 5, 10 }, { 4, 2, 9 }, { 3, 8, 3 } };
		int col = 2;
		int row_begin = 2;
		int row_end = 3;

		System.out.println(solution(data, col, row_begin, row_end));
	}

	public static int solution(int[][] data, int col, int row_begin, int row_end) {
		int result = 0;

		// col 번째 컬럼을 기준으로 오름차순 정렬, 동일한 경우 첫 번째 컬럼을 기준으로 내림차순 정렬
		ArrayList<Integer> order = new ArrayList<Integer>();
		order.add(0);
		for (int i = 1; i < data.length; i++) {
			boolean isAdd = false;
			for (int j = 0; j < order.size(); j++) {
				if (data[i][col - 1] < data[order.get(j)][col - 1]) {
					order.add(j, i);
					isAdd = true;
					break;
				} else if (data[i][col - 1] == data[order.get(j)][col - 1]) {
					if (data[i][0] > data[order.get(j)][0]) {
						order.add(j, i);
						isAdd = true;
						break;
					}
				}
			}
			if (!isAdd) {
				order.add(i);
			}
		}

		// S_i를 누적하여 bitwise XOR 한 값
		for (int i = row_begin; i <= row_end; i++) {
			int sum = 0;
			for (int j = 0; j < data[order.get(i - 1)].length; j++) {
				sum += (data[order.get(i - 1)][j] % i);
			}
			result ^= sum;
		}

		return result;
	}
}
