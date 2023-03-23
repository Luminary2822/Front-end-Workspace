/*
字节面试手撕
//将下面这个改写，解决回调地狱，注意时间 
let t = setTimeout(()=>{ 
    console.log(111) 
    let ti = setTimeout(()=>{ 
        console.log(222) 
        let t2 = setTimeout(()=>{ 
            console.log(333)
        },3000)
    },2000)
},1000)
*/
// 第一种写法
new Promise((resolve, reject) => {
    setTimeout(() => {
        console.log(111)
        resolve(222)
    }, 1000);
}).then(res => {
    setTimeout(() => {
        console.log(res)
    }, 2000);
    return 333
}).then(res => {
    setTimeout(()=>{
        console.log(res)
    }, 3000)
})

// 第二种写法
const sleep = time =>{
    return new Promise(resolve => setTimeout(resolve, time))
}
async function testPromise(){
    let res1 = await sleep(1000)
    console.log(111)
    let res2 = await sleep(2000)
    console.log(222)
    let res3 = await sleep(2000)
    console.log(333)
}
testPromise()