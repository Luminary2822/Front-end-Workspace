/*
 * @Description: 
 * @Author: Luminary
 * @Date: 2022-04-12 17:13:35
 * @LastEditTime: 2022-04-12 17:13:35
 */
async function async1() {
  console.log('async1')
  await async2()
  console.log('async1 end')
}
async function async2() {
  console.log('async2')
}
console.log('script start')    //1
setTimeout(() => {
  console.log('setTimeOut')
}, 0)
async1()
new Promise((resolve) => {
  console.log('promise')
  resolve('promise2')
}).then((data) => {
  console.log(data)
  return ('promise3')
}).then((data) => {
  console.log(data);
}).then((data) => {
  console.log(data);
})
console.log('script end')

// script start
// async1
// async2
// promise
// script end
// async1 end
// promise2
// promise3
// undefined
// setTimeOut