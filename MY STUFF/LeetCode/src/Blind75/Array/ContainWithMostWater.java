package Blind75.Array;

public class ContainWithMostWater {

    //You are given an integer array height of length n.
// There are n vertical lines drawn such that the two endpoints
// of the ith line are (i, 0) and (i, height[i]).
//Find two lines that together with the x-axis form a container,
// such that the container contains the most water.
//Return the maximum amount of water a container can store.
//Notice that you may not slant the container.
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int width = right - left;
            int length = Math.min(height[left], height[right]);
            maxArea = Math.max(maxArea, width * length);

            if (height[left] < height[right]) {
                left++;
            } else if (height[left] > height[right]) {
                right--;
            } else {
                left++;
                right--;
            }
        }

        return maxArea;
    }
}
