// https://school.programmers.co.kr/learn/courses/30/lessons/72413
// https://velog.io/@hiyoojeong/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-2021-KAKAO-BLIND-RECRUITMENT-%ED%95%A9%EC%8A%B9-%ED%83%9D%EC%8B%9C-%EC%9A%94%EA%B8%88-JAVA

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
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