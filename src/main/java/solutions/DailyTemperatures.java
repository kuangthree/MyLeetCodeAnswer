package solutions;

import java.util.HashMap;
import java.util.LinkedList;
/**
 * https://leetcode.com/problems/daily-temperatures/description/
 * Given a list of daily temperatures, produce a list that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.

 For example, given the list temperatures = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].

 Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
 * 哈希表搜索
 * Runtime: 142ms
 * */
public class DailyTemperatures {

    public int[] solve(int[] temperatures){
        return new Solution().dailyTemperatures(temperatures);
    }
    class Solution{
        public int[] dailyTemperatures(int[] temperatures) {
            HashMap<Integer,LinkedList<Integer>> map = new HashMap<>();
            for(int i = 0;i<temperatures.length;i++){
                if(map.containsKey(temperatures[i])){
                    map.get(temperatures[i]).addLast(i);
                }else{
                    LinkedList<Integer> integers = new LinkedList<>();
                    integers.addLast(i);
                    map.put(temperatures[i],integers);
                }
            }

            int[] ret = new int[temperatures.length];

            for(int i=0;i<temperatures.length;i++){
                int minIndex = Integer.MAX_VALUE;
                for(int k=temperatures[i]+1;k<=100;k++){
                    if(!map.containsKey(k))continue;
                    LinkedList<Integer> list = map.get(k);
                    while (!list.isEmpty()){
                        int m = list.getFirst();
                        if(m<=i){
                            list.removeFirst();
                            continue;
                        }
                        if(m<minIndex){
                            minIndex = m;
                        }
                        break;
                    }
                    if(list.isEmpty()){
                        map.remove(k);
                    }
                }
                if(minIndex == Integer.MAX_VALUE){
                    ret[i]=0;
                }else {
                    ret[i]=minIndex-i;
                }
            }
            return ret;
        }
    }
}
