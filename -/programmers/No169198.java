import java.util.Arrays;

// 당구 연습
public class No169198 {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(10, 10, 3, 7, new int[][] { { 7, 7 }, { 2, 7 }, { 7, 3 } })));
//		System.out.println(Arrays.toString(solution(10, 10, 5, 9, new int[][] { { 5, 8 } }))); // 9
//		System.out.println(Arrays.toString(solution(10, 10, 5, 1, new int[][] { { 5, 2 } }))); // 9
//		System.out.println(Arrays.toString(solution(10, 10, 9, 5, new int[][] { { 8, 5 } }))); // 9
//		System.out.println(Arrays.toString(solution(10, 10, 1, 5, new int[][] { { 2, 5 } }))); // 9
	}

	public static int[] solution(int m, int n, int startX, int startY, int[][] balls) {
		int[] answer = new int[balls.length];

		for (int i = 0; i < balls.length; i++) {
			int targetX = balls[i][0];
			int targetY = balls[i][1];

			int curlen, len = Integer.MAX_VALUE;

			// 좌
			if (!(startY == targetY && startX >= targetX)) {
				curlen = getDistance(startX, startY, targetX * (-1), targetY);
				len = curlen < len ? curlen : len;
			}

			// 우
			if (!(startY == targetY && startX <= targetX)) {
				curlen = getDistance(startX, startY, m + (m - targetX), targetY);
				len = curlen < len ? curlen : len;
			}

			// 상
			if (!(startX == targetX && startY <= targetY)) {
				curlen = getDistance(startX, startY, targetX, n + (n - targetY));
				len = curlen < len ? curlen : len;
			}

			// 하
			if (!(startX == targetX && startY >= targetY)) {
				curlen = getDistance(startX, startY, targetX, targetY * (-1));
				len = curlen < len ? curlen : len;
			}
			
			answer[i] = len;
		}

		return answer;
	}

	public static int getDistance(int sx, int sy, int tx, int ty) {
		return (int) (Math.pow(sx - tx, 2) + Math.pow(sy - ty, 2));
	}

	public static int[] solution_fail(int m, int n, int startX, int startY, int[][] balls) {
		int[] answer = new int[balls.length];

		for (int i = 0; i < balls.length; i++) {
			double targetX = (double) balls[i][0];
			double targetY = (double) balls[i][1];

			double distanceX = Math.abs(startX - targetX);
			double distanceY = Math.abs(startY - targetY);

			double startLen, targetLen, curlen, len = Integer.MAX_VALUE;

			if (distanceY != 0) {
				// 좌
				startLen = getDistance_fail(startX, targetX, distanceY);
				targetLen = getDistance_fail(targetX, startX, distanceY);
				curlen = (int) Math.pow(startLen + targetLen, 2);
				if (curlen < len)
					len = curlen;

				// 우
				startLen = getDistance_fail(m - startX, m - targetX, distanceY);
				targetLen = getDistance_fail(m - targetX, m - startX, distanceY);
				curlen = (int) Math.pow(startLen + targetLen, 2);
				if (curlen < len)
					len = curlen;
			}

			if (distanceX != 0) {
				// 상
				startLen = getDistance_fail(n - startY, n - targetY, distanceX);
				targetLen = getDistance_fail(n - targetY, n - startY, distanceX);
				curlen = (int) Math.pow(startLen + targetLen, 2);
				if (curlen < len)
					len = curlen;

				// 하
				startLen = getDistance_fail(startY, targetY, distanceX);
				targetLen = getDistance_fail(targetY, startY, distanceX);
				curlen = (int) Math.pow(startLen + targetLen, 2);
				if (curlen < len)
					len = curlen;
			}

			answer[i] = (int) len;
		}

		return answer;
	}

	public static double getDistance_fail(double i, double j, double d) {
		return Math.sqrt((Math.pow(i, 2) + Math.pow(d * i / (i + j), 2)));

	}
}

////좌측 하단 모서리
//if(startX*targetY==startY*targetY){curlen=getDistance(startX,startY,targetX*(-1),targetY*(-1));len=curlen<len?curlen:len;}
//
//// 우측 하단 모서리
//if((m-startX)*targetY==startY*(m-targetX)){curlen=getDistance(startX,startY,m+(m-targetX),targetY*(-1));len=curlen<len?curlen:len;}
//
//// 좌측 상단 모서리
//if(startX*(n-targetY)==(n-startY)*targetX){curlen=getDistance(startX,startY,targetX*(-1),n+(n-targetY));len=curlen<len?curlen:len;}
//
//// 우측 상단 모서리
//if((m-startX)*(n-targetY)==(n-startY)*(m-targetX)){curlen=getDistance(startX,startY,m+(m-targetX),n+(n-targetY));len=curlen<len?curlen:len;}
