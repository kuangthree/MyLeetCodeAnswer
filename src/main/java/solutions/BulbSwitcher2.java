package solutions;
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


 Runtime: 7 ms

 发现，如果n>3的话，无论n等于多少，结果都和n==3是相同的。m也一样。所以可以进行简单的优化。

 * */
public class BulbSwitcher2 {

    public int flipLights(int n, int m){
        if(m==0)return 1;
        if(m>3)m=3;
        if(n>3)n=3;
        int init = 0;
        for(int i=0;i<n;i++){
            init |= 1<<i;
        }
        int state = sonSet(init,n,m);
        int count = 0;
        for(int i=0;i<8;i++){
            count+= ((state&(1<<i))!=0)?1:0;
        }
        return count;
    }

    private static int[][][] dp;

    static {
        dp = new int[9][][];
        for(int i=0;i<9;i++){
            dp[i] = new int[4][];
            for(int j=0;j<4;j++){
                dp[i][j] = new int[4];
                for(int k=0;k<4;k++){
                    dp[i][j][k]=-1;
                }
            }
        }
    }

    private int sonSet(int state,int n,int m){
        if(dp[state][n][m]!=-1)return dp[state][n][m];

        if(m == 1){
            int set = 0;
            int mask = 0x7 >> (3-n);
            int state1 = state ^ mask;
            int state2 = state ^ (0x5&mask);
            int state3 = state ^ (0x2&mask);
            int state4 = state ^ (0x1&mask);
            set |= (1<<state1);
            set |= (1<<state2);
            set |= (1<<state3);
            set |= (1<<state4);
            dp[state][n][m] = set;
            return set;
        }
        int set = sonSet(state,n,1);
        int retSet = 0;
        for(int i=0;i<8;i++){
            if((set & (1<<i)) != 0){
                retSet |= sonSet(i,n,m-1);
            }
        }
        dp[state][n][m] = retSet;
        return retSet;
    }
}
