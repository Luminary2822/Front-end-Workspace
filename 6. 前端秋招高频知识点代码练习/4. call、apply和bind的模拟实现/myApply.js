/*
 * @Description: 
 * @Author: Luminary
 * @Date: 2022-04-12 11:52:59
 * @LastEditTime: 2022-04-21 15:56:35
 */
Function.prototype.myApply = function(context) {
    var context = context || window;
    context.fn = this;
    var result;
  
    // 判断第二个参数是否存在，是一个数组
    // 如果存在，则需要展开第二个参数
    if (arguments[1]) {
      result = context.fn(...arguments[1]);
    } else {
      result = context.fn();
    }
  
    delete context.fn;
    return result;
}


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

// Use myApply
console.log(obj1.sum.apply(obj2, [2, 3]))  // 14
console.log(obj1.sum.myApply(obj2, [2, 3]))  // 14