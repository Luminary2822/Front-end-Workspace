/*
 * @Description: 
 * @Author: Luminary
 * @Date: 2022-07-12 20:55:43
 * @LastEditTime: 2022-07-24 21:10:41
 */
const obj = {
    a: 1,
    b: {
        c: 2
    }
}
const {a, b:{c}} = obj;
console.log(a, b, c); 

// 输出
// Uncaught ReferenceError: b is not defined

/**
 * 多层对象(嵌套对象)的解构：嵌套对象的解构的语法就是：从原对象的最外层变量定位，一直到需要取值的那一层，每层之间用冒号:隔开，变量在冒号的右边。
 * 所以我们要解构的变量是c，此时b只是用来指明c的父节点，所以无法打印b
 * a和c是可以被解构出来的，如果单独打印a和c是可以得到1和2的
 */