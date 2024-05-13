

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 마라톤1
public class No10655 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 체크포인트 정보 저장
		int N = Integer.parseInt(br.readLine());
		int[][] checkPoint = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			checkPoint[i][0] = x;
			checkPoint[i][1] = y;
		}

		// 원래 마라톤 거리 구하기
		int distance = 0;
		for (int i = 0; i < N - 1; i++) {
			int d = Math.abs(checkPoint[i][0] - checkPoint[i + 1][0])
					+ Math.abs(checkPoint[i][1] - checkPoint[i + 1][1]);
			distance += d;
		}

		// 최소 거리가 되는 마라톤 거리 구하기
		int minDistance = distance;
		for (int i = 0; i < N - 2; i++) {
			// |x1-x2| + |y1-y2|
			// (i+1) 지점을 거쳐갈 때
			int original1 = Math.abs(checkPoint[i][0] - checkPoint[i + 1][0])
					+ Math.abs(checkPoint[i][1] - checkPoint[i + 1][1]);
			int original2 = Math.abs(checkPoint[i + 1][0] - checkPoint[i + 2][0])
					+ Math.abs(checkPoint[i + 1][1] - checkPoint[i + 2][1]);
			int original = original1 + original2;

			// (i+1) 지점을 거쳐가지 않을 때
			int skip = Math.abs(checkPoint[i][0] - checkPoint[i + 2][0])
					+ Math.abs(checkPoint[i][1] - checkPoint[i + 2][1]);
			
			int skipDistance = distance - original + skip;

			minDistance = minDistance < skipDistance ? minDistance : skipDistance;
		}

		System.out.println(minDistance);
	}

}
