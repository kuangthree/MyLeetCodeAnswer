package solutions;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
* From https://leetcode.com/problems/bulb-switcher-ii/description/
 There is a room with n lights which are turned on initially and 4 buttons on the wall. After performing exactly m unknown operations towards buttons, you need to return how many different kinds of status of the n lights could be.

 Suppose n lights are labeled as number [1, 2, 3 ..., n], function of these 4 buttons are given below:

 Flip all the lights.
 Flip lights with even numbers.
 Flip lights with odd numbers.
 Flip lights with (3k + 1) numbers, k = 0, 1, 2, ...
 Example 1:
 Input: n = 1, m = 1.
 Output: 2
 Explanation: Status can be: [on], [off]
 Example 2:
 Input: n = 2, m = 1.
 Output: 3
 Explanation: Status can be: [on, off], [off, on], [off, off]
 Example 3:
 Input: n = 3, m = 1.
 Output: 4
 Explanation: Status can be: [off, on, off], [on, off, on], [off, off, off], [off, on, on].
 Note: n and m both fit in range [0, 1000].


 Runtime: 743 ms

 一个写坏了的版本。思想很简单，就是把某个状态下经过1步操作所能得到的所有状态列举出来，然后递归。

 优化版
 * @see BulbSwitcher2

* */
public class BulbSwitcher1 {

    static class BulbState{
        boolean[] lights;
        public boolean equals(Object o){
            if(o instanceof BulbState){
                BulbState bs = (BulbState)o;
                return Arrays.equals(lights,bs.lights);
            }
            return false;
        }
        public int hashCode(){
            return Arrays.hashCode(lights);
        }
        public BulbState copy(){
            BulbState bs = new BulbState();
            bs.lights = lights.clone();
            return bs;
        }
    }

    public int flipLights(int n, int m) {
        if(!ndp.containsKey(n))
            dp = new HashMap<BulbState, HashMap<Integer, HashSet<BulbState>>>();
        else
            dp = ndp.get(n);
        if(m == 0)return 1;
        BulbState bulbState = new BulbState();
        bulbState.lights = new boolean[n];
        for(int i=0;i<n;i++){
            bulbState.lights[i] = true;
        }
        ndp.put(n,dp);
        return flipLights(bulbState,m).size();
    }

    private static HashMap<Integer,HashMap<BulbState,HashMap<Integer,HashSet<BulbState>>>> ndp = new HashMap<Integer, HashMap<BulbState, HashMap<Integer, HashSet<BulbState>>>>();

    private static HashMap<BulbState,HashMap<Integer,HashSet<BulbState>>> dp;



    private HashSet<BulbState> flipLights(BulbState a,int m){
        boolean c = dp.containsKey(a);
        HashMap<Integer,HashSet<BulbState>> map = null;
        if(c){
            map = dp.get(a);
            if(map.containsKey(m)) {
                return map.get(m);
            }
        }

        if(m == 1){
            HashSet<BulbState> bsSet = new HashSet<BulbState>();
            BulbState bs1 = a.copy();
            for(int i=0;i<bs1.lights.length;i++){
                bs1.lights[i] = !bs1.lights[i];
            }
            bsSet.add(bs1);
            BulbState bs2 = a.copy();
            for(int i=0;i<bs2.lights.length;i+=2){
                bs2.lights[i] = !bs2.lights[i];
            }
            bsSet.add(bs2);
            BulbState bs3 = a.copy();
            for(int i=1;i<bs3.lights.length;i+=2){
                bs3.lights[i] = !bs3.lights[i];
            }
            bsSet.add(bs3);
            BulbState bs4 = a.copy();
            for(int k=0;3*k<bs4.lights.length;k++){
                bs4.lights[3*k] = !bs4.lights[3*k];
            }
            bsSet.add(bs4);
            if(c){
                map.put(1,bsSet);
            }else{
                HashMap<Integer,HashSet<BulbState>> map1 = new HashMap<Integer, HashSet<BulbState>>();
                map1.put(1,bsSet);
                dp.put(a,map1);
            }
            return bsSet;
        }

        HashSet<BulbState> states = flipLights(a,1);

        HashSet<BulbState> ret = new HashSet<BulbState>();
        for(BulbState bs : states){
            ret.addAll(flipLights(bs,m-1));
        }
        if(c){
            map.put(m,ret);
        }else{
            HashMap<Integer,HashSet<BulbState>> map1 = new HashMap<Integer, HashSet<BulbState>>();
            map1.put(m,ret);
            dp.put(a,map1);
        }
        return ret;
    }
}
