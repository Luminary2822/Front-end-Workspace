/*
 * @Description: 
 * @Author: Luminary
 * @Date: 2022-05-23 21:44:18
 * @LastEditTime: 2022-05-23 21:54:39
 */
let a = {n : 1};
let b = a;
a.x = a = {n: 2};

console.log(a.x)
console.log(b.x)
// 输出
// undefined
// { n: 2 }

/*
一般的连续赋值的执行优先级是从右到左：a.x = a = { n:2 }; //  ===> a.x = (a = { n:2 });
new_1/new_2 临时给内存起的名字
. 运算符的执行优先级高于 =
先执行 a.x 在原先的内存(new_1)上添加一个新属性 x: undefined(b也指向这个内存，这是b也多了一个属性x:undefined)
然后执行 a = { n: 2 }; 此时 a 已经重新指向一块新的内存 new_2，所以a.x输出undefined
最后执行的a.x = a 其实是 new_1.x = new_2;就是将新内存new_2的值赋值给原先内存new_1中的属性x，所以b.x输出{ n: 2 }
*/