# Longest Palindromic Substring

[:link: Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/)  
<br>

### 풀이 방식


```java
public class Solution {
	
	int index;
	int maxLen;
	
	public String longestPalindrome(String s) {
		int len = s.length();

		if(len < 2) {
			return s;
		}

		for(int i = 0; i < len - 1; i++) {
			find(s, i, i);
			find(s, i, i+1);
		}
		
		return s.substring(index, index + maxLen);
	}

	private void find(String s, int i, int j) {
		while(i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
			i--;
			j++;
		}
		
		if(maxLen < j - i - 1) {
			index = i + 1;
			maxLen = j - i -1;
		}
	}
}

```
