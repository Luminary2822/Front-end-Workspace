/*
 * @Description: 
 * @Author: Luminary
 * @Date: 2022-04-27 13:58:28
 * @LastEditTime: 2022-04-27 16:07:07
 */
console.log('script start')

async function async1() {
    await async2()
    console.log('async1 end')
}
async function async2() {
    console.log('async2 end')
return Promise.resolve().then(()=>{
    console.log('async2 end1')
  })
}
async1()

setTimeout(function() {
    console.log('setTimeout')
}, 0)

new Promise(resolve => {
    console.log('Promise')
    resolve()
})
.then(function() {
    console.log('promise1')
})
.then(function() {
    console.log('promise2')
})
.then(function() {
    console.log('promise3')
})

console.log('script end')

// script start
// async2 end
// Promise
// script end
// async2 end1
// promise1
// promise2
//async1 end
// undefined
// setTimeout

// await + Promise.resolve().then()相当于 
// async2.then(
//     Promise.resolve().then(()=>{
//         console.log('async2 end1')
//     })).then(()=>{
//         console.log('async1 end')
//         })
// 内部的Promise的.then是一级，外面的async.then是二级，最后的async1 end是三级的then
