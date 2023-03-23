/*
 * @Description: 
 * @Author: Luminary
 * @Date: 2022-08-09 22:10:35
 * @LastEditTime: 2022-08-09 22:10:38
 */
var str1 = readline()
var str2 = readline()
var longestCommonSubsequence = function(text1, text2) {
    let m = text1.length
    let n = text2.length
    
    let dp = Array.from(Array(m+1), () => Array(n+1).fill(""));
    
    for(let i = 1;i < m + 1;i++){
        for(let j = 1;j < n + 1;j++){
            if(text1[i - 1] == text2[j - 1]){
                dp[i][j] = dp[i-1][j-1] + text1[i - 1] 
            }
            else{
                if(dp[i-1][j].length > dp[i][j-1].length)
                    dp[i][j] = dp[i-1][j]
                else
                    dp[i][j] = dp[i][j-1]
            }
        }
    } 
    return dp[m][n] == "" ? -1 : dp[m][n] 
};
let res = longestCommonSubsequence(str1,str2);
print(res)
