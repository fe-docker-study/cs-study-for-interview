import java.util.Stack;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int n = board.length;
        Stack<Integer>[] stackArr = new Stack[n];
        Stack<Integer> basket = new Stack<>();
        
        for(int i=0; i<n; i++) {
        	stackArr[i] = new Stack<>();
        }
        
        for(int i=n-1; i>=0; i--) {
        	for(int j=0; j<n; j++) {
        		if(board[i][j] != 0) {
        			stackArr[j].push(board[i][j]);
        		}
        	}
        }
        
        int cnt = 0;
        int before=-1;
        for(int i=0; i<moves.length; i++) {
        	int num = moves[i];
        	if(!stackArr[num-1].isEmpty()) {
        		int doll = stackArr[num-1].pop();
            	basket.push(doll);
//            	System.out.println("num : "+num+", doll : "+doll);
            	// 이전과 같은 인형이면 제거
            	if(before == doll) {
        			basket.pop();
        			basket.pop();
        			cnt +=2;
        		}
            	
            	// 터뜨린 이후 
    			if(basket.isEmpty()) {
    				before = -1;
    			}else {
    				before = basket.peek();
    			}
        	}
        }
        
        answer = cnt;
        
        return answer;
    }
}
