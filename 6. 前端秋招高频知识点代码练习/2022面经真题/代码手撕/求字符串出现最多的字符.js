/*
 * @Description: 
 * @Author: Luminary
 * @Date: 2022-08-01 21:09:16
 * @LastEditTime: 2022-08-01 22:05:53
 */

// 根据出现字符和个数构造字典
function getMax(str) {
    let obj = {};
    for(let s of str) {
        obj[s] = obj[s] ? obj[s] += 1 : 1 
    }
    let keys = Object.keys(obj); // 获取对象中所有key的值返回数组
    let values = Object.values(obj); // 获取所有value返回数组
    let maxVal = Math.max(...values); // Math.max可以找出value的最大值，出现的最多次数
    console.log(keys[values.indexOf(maxVal)], maxVal);
}

// 利用reduce方法获得字典
function useReduce(str) {
    var testArray = str.split('');
    var obj = testArray.reduce(function(prev,next){
        if(next in prev) {
          prev[next]++;
        }else {
          prev[next] = 1;
        }
        return prev
      },{})
      let keys = Object.keys(obj); 
      let values = Object.values(obj); 
      let maxVal = Math.max(...values);
      console.log(keys[values.indexOf(maxVal)],maxVal);
}

let str = 'aaaaaaaabbbcccccbbcccccdddd';
useReduce(str); 
getMax(str);