/*
 * @des                               防抖函数
 * @param  {Number}    delay          延迟时间（ms），不传默认200ms
 * @param  {Function}  callback       回调函数
 * @param  {Boolen}    false          触发时是否立即执行一次，默认不执行
 * @return {Function}                 A new debounce function
 */
const debounce = (callback, delay = 200, im = false) => {
    let timeoutID = null
    return function() {
      // 第一次触发时是否立即执行
      if (im && !timeoutID) callback.apply(this, arguments)
      // 避免开启过多计时器
      if (timeoutID) clearTimeout(timeoutID)
      timeoutID = setTimeout(() => {
        // 借用外部第一个普通函数的this和arguments对象
        callback.apply(this, arguments)
        // 执行后将timeoutID置为null
        timeoutID = null
      }, delay)
    }
  }
  
  /*
   * @des                               节流函数
   * @param  {Number}    delay          延迟时间（ms），不传默认200ms
   * @param  {Function}  callback       回调函数
   * @param  {Boolen}    false          触发时是否立即执行第一次，默认不执行
   * @return {Function}                 A new throttle function
   */
  const throttle = (callback, delay = 200, im = false) => {
    let timeoutID = null
    return function() {
      // 第一次触发时是否立即执行
      if (im && !timeoutID) {
        // 立即执行
        callback.apply(this, arguments)
        // 执行后立即关闭
        im = false
      }
      if (!timeoutID) {
        timeoutID = setTimeout(() => {
          // 外部第一个普通函数this和arguments对象
          callback.apply(this, arguments)
          // 执行后将timeoutID置为null
          timeoutID = null
        }, delay)
      }
    }
  }