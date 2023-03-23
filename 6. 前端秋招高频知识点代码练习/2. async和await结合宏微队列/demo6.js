/*
 * @Description: 
 * @Author: Luminary
 * @Date: 2022-04-27 16:01:12
 * @LastEditTime: 2022-04-27 17:21:55
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
}).then(function() {
    console.log('promise1')
}).then(function() {
    console.log('promise2')
})
async function async3() {
    await async4()
    console.log('async3 end')
}
async function async4() {
    console.log('async4 end')
    return Promise.resolve().then(()=>{
        console.log('async4 end1')
    })
}
async3()
console.log('script end')

/*
    'script start'
    'async2 end'
    'Promise'
    'async4 end'
    'script end'
    'async2 end1'
    'promise1'
    'async4 end1'
    'promise2'
    'async1 end'
    'async3 end'
    'setTimeout'
 */