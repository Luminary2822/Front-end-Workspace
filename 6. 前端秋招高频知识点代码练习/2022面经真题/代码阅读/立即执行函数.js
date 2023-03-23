/*
 * @Description: 
 * @Author: Luminary
 * @Date: 2022-06-23 21:31:18
 * @LastEditTime: 2022-06-23 21:43:45
 */

// 立即执行函数声明中变量用var和不用var声明
// https://blog.csdn.net/weixin_44039015/article/details/109383480

// 第一个例子
var a = 10;
(function () {
console.log(a); 
a = 5;
console.log(window.a); 
var a = 20;
console.log(window.a); 
console.log(a); 
})();
console.log(a); 
console.log(window.a); 

// 输出
// undefined
// 10
// 10
// 20
// 10
// 10

/*
因为立即执行函数是一个局部作用域，所以第一个打印的a是undefined，因为只有var的变量声明提前了
var声明的会声明提前，造成里面的同一个变量a为局部变量，所以a=5的a也变成局部变量了
所以在里面一切用window.a打印的都为全局对象上面的对应的值，即为10
*/


// 第二个例子（面经上的题）
var a = 3;
function a() { }
console.log(a);
a = 5;
console.log(a);
(function a() {
    console.log(a);
    a = 4;
    console.log(a);
})();
// 输出
// 3
// 5
// ƒ a() {
//     console.log(a);
//     a = 4;
//     console.log(a);
// }
// ƒ a() {
//     console.log(a);
//     a = 4;
//     console.log(a);
// }

/*
立即执行函数里面声明的a，没有用var声明，严格来说不属于全部变量，可以全局对象添加的属性
所以Windows.a是4，如果输出变量a，那就是立即执行函数f a()
*/


// 第三个例子
(function a() {
    console.log(a);
    a = 5;
    console.log(window.a);
    console.log(a);
})();
// 输出
// ƒ a() {
//     console.log(a);
//     a = 5;
//     console.log(window.a);
//     console.log(a);
// }
// 5
// ƒ a() {
//     console.log(a);
//     a = 5;
//     console.log(window.a);
//     console.log(a);
// }


