class Solution {


    public int maxArea(int[] height) {


        int resMax = Integer.MIN_VALUE;

        int start = 0;
        int last = height.length - 1;

        while (last - start > 0) {

            resMax = Math.max(resMax, (last-start) * Math.min(height[start], height[last]));
            if(height[start] > height[last]) {
                last--;
            }else {
                start++;
            }
        }

        return resMax;
    }



}