/*
 * @Description: 
 * @Author: Luminary
 * @Date: 2022-04-15 22:29:13
 * @LastEditTime: 2022-04-15 22:29:13
 */
function throttle(func, wait) {
    // 初始化定时器、上下文和参数
    var timeout, context, args;
    // 上一次调用时间
    var previous = 0;

    var throttled = function() {
        var now = +new Date();
        //下次触发 func 剩余的时间
        var remaining = wait - (now - previous);
        context = this;
        args = arguments;
         // 如果没有剩余的时间了或者你改了系统时间
        if (remaining <= 0 || remaining > wait) {
            // 定时器存在则清空定时器
            if (timeout) {
                clearTimeout(timeout);
                timeout = null;
            }
            // 调用func，并且将现在的时间设置为上一次执行时间
            previous = now;
            func.apply(context, args);
            // 还有剩余时间且定时器不存在的情况下，过了剩余时间执行最后一次fn
        } else if (!timeout) {
            timeout = setTimeout(function(){
                previous = now;
                timeout = null;
                func.apply(context, args)
            }, remaining);
        }
    };
    return throttled;
}