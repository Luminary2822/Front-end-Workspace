// 数组扁平化
function flatten(){
    var res = []
    arr.map(item => {
        if(Array.isArray(item))
            res = res.concat(flatten(item))
        else
            res.push(item)
    })
    return res
}

function flat(arr){
    return arr.reduce((res, item) => {
        return res.concat(item instanceof Array ? flat(item):item)
    })
}

// 模拟实现call、apply和bind
Function.prototype.myCall = function(obj){
    obj = obj ? Object(obj) : window
    obj.fn = this
    let args = [...arguments].slice(1)
    let result = obj.fn(...args)
    delete obj.fn
    return result
}

Function.prototype.myApply = function(obj){
    obj = obj || window
    obj.fn = this
    let result
    if(arguments[1])
         result = obj.fn(...arguments[1])
    else
        result = obj.fn()
    delete obj.fn
    return result
}

Function.prototype.myBind = function(obj){
    obj = obj || window
    let self = this
    // 获取调用myBind的参数
    let args = Array.prototype.slice.call(arguments, 1)
    const F = function(){}
    const fn = function(){
        // 调用myBind返回函数时传入的参数
        let bindArgs = Array.prototype.slice.call(arguments)
        return self.apply(this instanceof fn ? this : obj, args.concat(bindArgs))
    }
    F.prototype = self.prototype
    fn.prototype = new F()
    return fn
}

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

// 防抖与节流全面版
function debounce(func, wait, immediate) {
    var timer 
    return function(){
        var _this = this
        var args = arguments
        if(timer) clearTimeout(timer)
        if(immediate){
            let callNow = !timer
            timer = setTimeout(() => {
                timer = null
            }, wait);
            if(callNow) func.apply(_this, args)
        }
        else{
            timer = setTimeout(() => {
               func.apply(_this, args) 
            }, wait);
        }
    }
}
function throttle(func, wait) {
    var timer, _this, args
    var previous = 0
    return function(){
        var now = +new Date()
        var remaining = wait - (now - previous)
        _this = this
        args = arguments
        if(remaining <= 0 || remaining > wait){
            if(timer){
                clearTimeout(timer)
                timer = null
            }
            previous = now 
            func.apply(_this, args)
        }
        else if(!timer){
            timer = setTimeout(function() {
                previous = now
                timer = null
                func.apply(_this, args)
            }, remaining)
        }
    }
}

// 深拷贝
function deepClone(obj) {
    if(typeof(obj) !== 'object') return
    var newObj = obj instanceof Array ? [] : {}
    for(var key in obj){
        if(obj.hasOwnProperty(key)){
            newObj[key] = typeof(obj[key]) === 'object' ? deepClone(obj[key]) : obj[key]
        }
    }
    return newObj
}






