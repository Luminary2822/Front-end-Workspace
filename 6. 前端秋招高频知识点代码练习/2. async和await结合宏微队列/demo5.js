/*
 * @Description: 
 * @Author: Luminary
 * @Date: 2022-04-27 13:59:42
 * @LastEditTime: 2022-04-27 14:00:58
 */
async function async1() {
    console.log('async1 start');
    await async2();                
    console.log('async1 end');
}
async function async2() {
    await async3(); 
    console.log('async2');
}
async function async3() {
    console.log('async3');
}
console.log('script start');
setTimeout(function() {
    console.log('setTimeout');
}, 0)
async1();
new Promise(function(resolve) {
    console.log('promise1');
    resolve();
}).then(function() {
    console.log('promise2');
});
console.log('script end');


/*
script start
async1 start
async3
promise1
script end
async2
promise2
async1 end
undefined
setTimeout
*/


/*
async2
promise2
async1 end
*/
// 这一块顺序的解释
/*首先前面一部分的内容都差不多，但是在上述第3步的时候，执行async1();函数的时候，
先是执行了console.log('async1 start');输出了async1 start，然后await async2();首先执行async2()函数的内容，
然后返回了async2().then(()=>{console.log('async1 end');}),然后执行async2()函数的内容的时候，遇到了await async3();，
这里需要先执行async3(),执行console.log('async3');，输出async3，然后返回async3().then(()=>{console.log('async2')})，
有因为await async3()其实是在async2()函数中的，
所以这里可以抽象成async3().then(()=>{console.log('async2')}).then(()=>{console.log('async1 end');})，
这里命名为task1，然后中间的过程一样，promise.then(console.log('promise2');)，依旧为task2，
到了执行微任务的时候，首先执行async3().then(()=>{console.log('async2')})的时候，输出了async2，
然后这个时候到了.then()，他会再次生成一个微任务添加到微任务队列中，也就产生了task3，
其实是.then(()=>{console.log('async1 end');})，然后执行task2，输出promise2，最后再执行task3，输出了async1 end，
其他就和上面的基本一致了。
*/