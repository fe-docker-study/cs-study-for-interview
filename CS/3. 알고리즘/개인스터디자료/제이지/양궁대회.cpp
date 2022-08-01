#include <string>
#include <vector>

using namespace std;

vector<int> INFO;
vector<int> ANS(11);
int MAX_GRADE_DIFF = 0;

void dfs(int grade, int gradeDiff, int arrow, vector<int> cur) {
    if (grade == 11) {
        if (gradeDiff > 0 && gradeDiff > MAX_GRADE_DIFF) {
            cur[10] += arrow;
            MAX_GRADE_DIFF = gradeDiff;
            ANS.assign(cur.begin(), cur.end());
        }
        return;
    }

    // 점수 땀
    int used = INFO[10 - grade] + 1;
    if (arrow - used >= 0) {
        cur[10 - grade] = used;
        dfs(grade + 1, gradeDiff + grade, arrow - used, cur);
    }

    // 점수 못땀
    int lose = INFO[10 - grade] ? grade : 0;
    cur[10 - grade] = 0;
    dfs(grade + 1, gradeDiff - lose, arrow, cur);

}

vector<int> solution(int n, vector<int> info) {
    INFO = info;
    vector<int> cur(11);
    dfs(0, 0, n, cur);

    if (MAX_GRADE_DIFF == 0) {
        return {-1};
    }
    return ANS;
}
