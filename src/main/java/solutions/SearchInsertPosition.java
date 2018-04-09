package solutions;
/**
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

 You may assume no duplicates in the array.*/
public class SearchInsertPosition {
    class Solution{
        public int searchInsert(int[] nums, int target) {
            int from = 0,to = nums.length-1;
            int index=-1;
            while (from<=to){
                int mid = (from+to)>>1;
                if(nums[mid]==target){
                    index = mid;
                    break;
                }
                if(target<nums[mid]){
                    to = mid-1;
                    continue;
                }
                from = mid+1;
            }
            if(index!=-1)return index;
            else return to+1;
        }
    }
}
