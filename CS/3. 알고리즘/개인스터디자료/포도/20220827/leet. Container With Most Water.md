# Container With Most Water

[:link: Container With Most Water](https://leetcode.com/problems/container-with-most-water/)  
<br>

### 풀이 방식
- 면적의 넓이는 두 개의 막대 중 짧은 막대에 의해 결정됨

```java
class Solution {
	public int maxArea(int[] height) {
		int left = 0;
		int right = height.length - 1;

		int areaSize = (right - left) * Math.min(height[left], height[right]); // 면적의 세로는 두 개의 막대 중 짧은 막대에 의해 결정됨

		while(left < right) {

			// 큰 막대는 두고 짧은 막대가 있는 쪽은 다음으로 이동
			if(height[left] <= height[right]) {
				left++;
			} else {
				right--;
			}

			areaSize = Math.max(areaSize, (right - left) * Math.min(height[left], height[right])); // 다시 계산한 값과 원래 있던 값 중 큰 값을 저장
		}

		return areaSize;
	}
}
```
