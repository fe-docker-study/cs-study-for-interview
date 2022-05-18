import java.util.Stack;

class Solution {

    int[][] gameBoard;
    int[] moveHistory;

    Stack<Integer> stack = new Stack<>();

    int res = 0;

    public int solution(int[][] board, int[] moves) {
        this.gameBoard = board;
        this.moveHistory = moves;

        for (int i = 0; i < moveHistory.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                if (gameBoard[j][moveHistory[i] - 1] != 0) {
                    pick(j, moveHistory[i] - 1);
                    break;
                }
            }
        }

        return res;
    }

    public void pick(int j, int i) {
        int pickDoll = gameBoard[j][i];

        if (!check(pickDoll)) {
            stack.push(pickDoll);
        }else {
            stack.pop();
            res +=2;
        }
        gameBoard[j][i] = 0;
    }

    public boolean check(int pickDoll) {
        if(!stack.isEmpty()) {
            if (pickDoll == stack.peek()) {
                return true;
            }
        }
        return false;
    }

}
