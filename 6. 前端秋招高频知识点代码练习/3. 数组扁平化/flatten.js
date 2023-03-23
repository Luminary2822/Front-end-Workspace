/*
 * @Description: 
 * @Author: Luminary
 * @Date: 2022-04-15 22:27:58
 * @LastEditTime: 2022-05-22 21:40:51
 */
let arr = [1, [2, [3, [4, 5]]], 6];

//1.直接调用flat方法
newarr = arr.flat(arr);


//2.递归实现
function flatten(arr) {
    var res = [];
    arr.map(item => {
        if(Array.isArray(item)) {
            res = res.concat(flatten(item));
        } else {
            res.push(item);
        }
    });
    return res;
}
// 不知道这个算不算递归优化，因为map本身就会返回新数组
function flatten(arr){
    return [].concat(...arr.map(item => Array.isArray(item) ? flatten(item) : item))
}
// 上面这个函数写成箭头函数一行
const flatten = (arr) => [].concat(...arr.map(item => Array.isArray(item) ? flatten(item) : item))

//3.reduce
function flat(arr) {
    return arr.reduce((arr,item)=>{
        return arr.concat(item instanceof Array ? flat(item):item)
    }, [])
}
flat(arr)