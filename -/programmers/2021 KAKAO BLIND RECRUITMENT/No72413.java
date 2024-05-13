// 합승 택시 요금
public class No72413 {

	public static void main(String[] args) {

		int n = 6, s = 4, a = 6, b = 2;
		int[][] fares = { { 4, 1, 10 }, { 3, 5, 24 }, { 5, 6, 2 }, { 3, 1, 41 }, { 5, 1, 24 }, { 4, 6, 50 },
				{ 2, 4, 66 }, { 2, 3, 22 }, { 1, 6, 25 } };

//		int n = 7, s = 3, a = 4, b = 1;
//		int[][] fares = { { 5, 7, 9 }, { 4, 6, 4 }, { 3, 6, 1 }, { 3, 2, 3 }, { 2, 1, 6 } };

//		int n = 6, s = 4, a = 6, b = 5;
//		int[][] fares = { { 2, 6, 6 }, { 6, 3, 7 }, { 4, 6, 7 }, { 6, 5, 11 }, { 2, 5, 12 }, { 5, 3, 20 }, { 2, 4, 8 },
//				{ 4, 3, 9 } };

		int result = getMinFare(n, s, a, b, fares);
		System.out.println("예상되는 최저 택시요금 = " + result);

	}

	public static int getMinFare(int n, int s, int a, int b, int[][] fares) {
		int result = 0;

		s -= 1;
		a -= 1;
		b -= 1;

		int[][] f = new int[n][n];

		// 우선 모든 길을 최대 요금으로 설정
		int BIGNUM = 1000000;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) {
					f[i][j] = 0;
				} else {
					f[i][j] = BIGNUM;
				}
			}
		}

		// 주어진 택시 요금으로 설정
		int v1, v2, distance;
		for (int i = 0; i < fares.length; i++) {
			v1 = fares[i][0] - 1;
			v2 = fares[i][1] - 1;
			distance = fares[i][2];
			f[v1][v2] = distance;
			f[v2][v1] = distance;
		}

		// i->j vs i->k->j 중 최소 요금으로 설정
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					f[i][j] = Math.min(f[i][j], f[i][k] + f[k][j]);
				}
			}
		}

		// 합승하지 않는 경우를 result에 우선 저장
		int AFare, BFare;
		AFare = f[s][a];
		BFare = f[s][b];
		result = AFare + BFare;

		// 합승하는 비용이 적을 경우, result 업데이트
		int publicFare, publicAFare, publicBFare;
		for (int k = 0; k < n; k++) {
			publicFare = f[s][k];
			publicAFare = f[k][a];
			publicBFare = f[k][b];
			result = Math.min(result, publicFare + publicAFare + publicBFare);
		}

		return result;
	}

}
