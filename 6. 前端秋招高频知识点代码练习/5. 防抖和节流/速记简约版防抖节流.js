// 防抖和节流简约版
function debounce(func, wait) {
    var timer
    return function(){
        var _this = this
        var args = arguments
        if(timer) clearTimeout(timer)
        timer = setTimeout(() => {
            func.apply(_this, args)
        }, wait);
    }
}
// 节流时间戳版：立即执行，停止后不会执行
function throttle(func, wait) {
    var _this,args
    var pre = 0
    return function(){
        var now = +new Date()
        _this = this
        args = arguments
        if(now - pre > wait){
            func.apply(_this,args)
            pre = now
        }
    }
}
// 节流定时器版：n秒后执行，停止后会再执行一次
function throttle(func, wait) {
    var timer = null
    var _this, args
    return function () {
        _this = this
        args = arguments
        if(timer) return
        timer = setTimeout(() => {
            func.apply(_this, args)
            timer = null
        }, wait);
    }
}