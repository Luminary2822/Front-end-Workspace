/*
 * @Description: 
 * @Author: Luminary
 * @Date: 2022-05-04 20:48:27
 * @LastEditTime: 2022-05-04 21:11:57
 */
// 4.12 字节前端实习面试题
var a = {
    name: 'A',
    fun: function(){
        console.log(this.name);
    }
};
a.fun();
a.fun.call({name: 'B'});
var fun1 = a.fun;
fun1();

// 输出
// A
// B
// undefined

/* 
普通函数的this，谁调用指向谁，输出A
通过call可以改变this指向，输出B
此时this已经指向全局对象，在浏览器里是windows，浏览器的windows对象有name属性，值为''，所以在浏览器的控制台这里会输出''
*/


var a = {
    name: 'A',
    fun: () => {console.log(this.name);}
};
a.fun();
a.fun.call({name: 'B'});
var fun1 = a.fun;
fun1();

// 输出
// undefined
// undefined
// undefined

/* 箭头函数没有自己的this，它的this指向上下文的this，在这里就是window全局
箭头函数的this不能通过call改变，所以一直都是window，都是undefined*/