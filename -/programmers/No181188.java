import java.util.Arrays;

// 요격 시스템
public class No181188 {

	public static void main(String[] args) {
		System.out.println(
				solution(new int[][] { { 4, 5 }, { 4, 8 }, { 10, 14 }, { 11, 13 }, { 5, 12 }, { 3, 7 }, { 1, 4 } }));
	}

	public static int solution(int[][] targets) {
		int answer = 0;

		// 개구간이 끝나는 지점 e에 대해 오름차순 정렬
		Arrays.sort(targets, (o1, o2) -> {
			return o1[1] - o2[1];
		});

		// 요격 미사일 발사 지점 계산
		int point = -1;
		for (int i = 0; i < targets.length; i++) {
			int s = targets[i][0];
			int e = targets[i][1];

			// 요격 미사일 발사 지점은 항상 개구간이 끝나는 지점에 둔다.
			if (s >= point) {
				point = e;
				answer++;
			}
		}

		return answer;
	}

}
