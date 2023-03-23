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