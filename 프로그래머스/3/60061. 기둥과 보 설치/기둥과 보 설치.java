import java.util.*;

class Solution {

    int n;
    int[][] map;
    final int GIDUNG = 1, BO = 2;

    public int[][] solution(int n, int[][] build_frame) {
        this.n = n;
        map = new int[n+1+2][n+1+2]; // 행열 2칸씩 늘림

        for(int i=0; i<build_frame.length; i++) {
            int x = build_frame[i][0] + 1;
            int y = build_frame[i][1] + 1;
            int a = build_frame[i][2]; // 기둥, 보 구분
            int b = build_frame[i][3]; // 삭제, 설치 구분

            if(a==0 && b==0) { // 기둥 삭제
                map[y][x] ^= (1<<GIDUNG);
                if(!remove()) {
                    map[y][x] ^= (1<<GIDUNG);
                }
            } else if(a==0 && b==1) { // 기둥 설치
                if(installGidung(y, x)) {
                    map[y][x] |= (1<<GIDUNG);
                }
            } else if(a==1 && b==0) { // 보 삭제
                map[y][x] ^= (1<<BO);
                if(!remove()) {
                    map[y][x] ^= (1<<BO);
                }
            } else if(a==1 && b==1) { // 보 설치
                if(installBo(y, x)) {
                    map[y][x] |= (1<<BO);
                }
            }

//            for(int j=n+1; j>=1; j--) {
//                for(int k=1; k<=n+1; k++) {
//                    System.out.print(map[j][k] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();
        }

        Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if(o1[0] != o2[0]) {
                return o1[0] - o2[0]; // x좌표 오름차순 정렬
            } else if(o1[1] != o2[1]) {
                return o1[1] - o2[1]; // y좌표 오름차순 정렬
            } else {
                return o1[2] - o2[2]; // 기둥먼저 정렬
            }
        });
        for(int i=1; i<=n+1; i++) {
            for(int j=1; j<=n+1; j++) {
                if((map[i][j] & (1<<GIDUNG)) != 0) {
                    pq.add(new int[] {j-1, i-1, 0});
                }
                if((map[i][j] & (1<<BO)) != 0) {
                    pq.add(new int[] {j-1, i-1, 1});
                }
            }
        }

        int[][] answer = new int[pq.size()][3];
        int idx = 0;
        while(!pq.isEmpty()) {
            answer[idx++] = pq.poll();
        }
        return answer;
    }

    public boolean installGidung(int y, int x) {
        // 아래가 바닥인 경우
        if(y == 1) {
            return true;
        }

        // 아래가 기둥인 경우
        if((map[y-1][x] & (1<<GIDUNG)) != 0) {
            return true;
        }

        // 양쪽에 하나라도 보가 있는 경우
        if((map[y][x-1] & (1<<BO)) != 0 || (map[y][x] & (1<<BO)) != 0) {
            return true;
        }

        return false;
    }

    public boolean installBo(int y, int x) {
        // 보의 양끝 아래에 기둥이 하나라도 있는 경우
        if((map[y-1][x] & (1<<GIDUNG)) != 0 || (map[y-1][x+1] & (1<<GIDUNG)) != 0) {
            return true;
        }

        // 보의 양쪽 끝 부분이 다른 보와 연결된 경우
        if((map[y][x-1] & (1<<BO)) != 0 && (map[y][x+1] & (1<<BO)) != 0) {
            return true;
        }

        return false;
    }

    public boolean removeGidung(int y, int x) {
        // 왼쪽으로 연결된 보가 있는 경우 확인
        if((map[y][x-1] & (1<<BO)) != 0 && !installBo(y, x-1)) return false;
        if((map[y+1][x-1] & (1<<BO)) != 0 && !installBo(y+1, x-1)) return false;


        // 오른쪽으로 연결된 보가 있는 경우 확인
        if((map[y][x+1] & (1<<BO)) != 0 && !installBo(y, x+1)) return false;
        if((map[y+1][x+1] & (1<<BO)) != 0 && !installBo(y+1, x+1)) return false;

        // 위로 연결된 기둥이 있는 경우 확인
        if((map[y+1][x] & (1<<GIDUNG)) != 0 && !installGidung(y+1, x)) return false;

        // 아래로 연결된 기둥이 있는 경우 확인
        if((map[y-1][x] & (1<<GIDUNG)) != 0 && !installGidung(y-1, x)) return false;

        return true;
    }
    
    public boolean remove() {
        for(int i=1; i<=n+1; i++) {
            for(int j=1; j<=n+1; j++) {
                if((map[j][i] & (1<<GIDUNG)) != 0 && !installGidung(j, i)) {
                    return false;
                }
                if((map[j][i] & (1<<BO)) != 0 && !installBo(j, i)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean removeBo(int y, int x) {
        // 왼쪽으로 연결된 보가 있는 경우
        if((map[y][x-1] & (1<<BO)) != 0 && !installBo(y, x-1)) return false;

        // 오른쪽으로 연결된 보가 있는 경우
        if((map[y][x+1] & (1<<BO)) != 0 && !installBo(y, x+1)) return false;

        // 아래로 연결된 기둥이 있는 경우
        if((map[y-1][x] & (1<<GIDUNG)) != 0 && !installGidung(y-1, x)) return false;
        if((map[y-1][x+1] & (1<<GIDUNG)) != 0 && !installGidung(y-1, x+1)) return false;

        // 위로 연결된 기둥이 있는 경우
        if((map[y][x] & (1<<GIDUNG)) != 0 && !installGidung(y, x)) return false;
        if((map[y][x+1] & (1<<GIDUNG)) != 0 && !installGidung(y, x+1)) return false;

        return true;
    }
}