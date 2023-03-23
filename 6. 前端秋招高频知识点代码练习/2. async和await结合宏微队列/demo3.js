/*
 * @Description: 
 * @Author: Luminary
 * @Date: 2022-04-12 17:14:28
 * @LastEditTime: 2022-04-12 17:14:29
 */
async function async1() {
    console.log('async1')
    console.log(await async2())
    console.log(await async3())
  }

  async function async2() {
    console.log('async2')
    return await 'ssssss'
  }
  async function async3() {
    console.log('async3')
    return await '2222222'
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
    return ('promise4')
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
// promise2
// ssssss
// async3
// promise3
// promise4
// 2222222
// undefined
// setTimeOut