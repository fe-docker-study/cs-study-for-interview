#include <string>
#include <vector>

using namespace std;

int solution(vector<vector<int>> board, vector<vector<int>> skill) {
    int X = board.size();
    int Y = board[0].size();

    // 누적합 기록
    int map[1001][1001] = {0};
    for (int i=0; i<skill.size(); i++) {
        int d = skill[i][0] == 1 ? -skill[i][5] : skill[i][5];
        int x1 = skill[i][1], y1 = skill[i][2], x2 = skill[i][3] + 1, y2 = skill[i][4] + 1;

        map[x1][y1] += d;
        map[x2][y1] -= d;
        map[x1][y2] -= d;
        map[x2][y2] += d;
    }

    int answer = 0;

    for (int i=1; i<X; i++) {
        for (int j=0; j<Y; j++) {
            map[i][j] += map[i-1][j];
        }
    }

    for (int i=0; i<X; i++) {
        for (int j=1; j<Y; j++) {
            map[i][j] += map[i][j-1];
        }
    }

    for (int i=0; i<X; i++) {
        for (int j=0; j<Y; j++) {
            if (board[i][j] + map[i][j] > 0) {
                answer++;
            }
        }
    }
    return answer;
}
