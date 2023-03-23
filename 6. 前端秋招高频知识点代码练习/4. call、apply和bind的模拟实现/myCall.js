/*
 * @Description: 
 * @Author: Luminary
 * @Date: 2022-04-12 11:15:09
 * @LastEditTime: 2022-05-15 20:06:59
 */
Function.prototype.myCall = function (obj) {
    // 取得传入的对象（执行上下文），不传默认是window
    obj = obj ? Object(obj) : window;
    // 将函数变成对象的内部属性fn = this，此时的this指向调用myCall的函数
    obj.fn = this;
    // 通过展开运算符和解构赋值取出obj后面的参数，取出第二个到最后一个参数放到args数组内
    let args = [...arguments].slice(1);
    // 执行函数
    let result = obj.fn(...args);
    // 删除函数

    delete obj.fn
    return result;
};
  

// Test
let obj1 = {
    basicNum: 1,
    sum(a, b) {
        console.log(this)
        return this.basicNum + a + b
}
}
let obj2 = {
    basicNum: 9
}

// Use myCall
// console.log(obj1.sum.call(obj2, 2, 3))  // 14
console.log(obj1.sum.myCall(obj2, 2, 3))  // 14
  