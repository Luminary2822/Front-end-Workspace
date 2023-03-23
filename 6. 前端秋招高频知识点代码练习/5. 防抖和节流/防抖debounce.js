/*
 * @Description: 
 * @Author: Luminary
 * @Date: 2022-04-15 22:28:52
 * @LastEditTime: 2022-04-15 22:28:52
 */
// func是要调用的函数，wait防抖的时间，immediate判断是否立即执行的参数
function debounce(func, wait, immediate) {
    // timer是一个定时器
    var timer;
    return function () {
        var _this = this;
        var args = arguments;
		// 常规流程，间隔内触发时清掉重置定时
        // timer是定时器ID，初始化状态是null，后续在周期内触发db函数会清除定时器。
        if (timer) clearTimeout(timer); 
        
        // 立即执行触发
        if (immediate) {
            // callNow 初始值是 true, 同步立即执行；执行func.apply。随后 timer 开始执行
            var callNow = !timer; 
            // wait 期间，timer 是一个 ID 数字，所以 callNow 为 false，func 在此期间永远不会执行
            // wait 之后，timer 赋值 null，callNow 为 true，func 又开始立即执行。
            timer = setTimeout(function(){
                timer = null; 
            }, wait) 
            
            if (callNow) func.apply(_this, args)
        }
        // 没有立即执行的命令时，在周期内db函数被触发会更新定时器，延迟事件函数的执行
        else {
            timer = setTimeout(function(){
                func.apply(_this, args)
            }, wait);
        }
    }
}