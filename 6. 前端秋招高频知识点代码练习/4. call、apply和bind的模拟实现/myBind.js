/*
 * @Description: 
 * @Author: Luminary
 * @Date: 2022-04-12 12:03:20
 * @LastEditTime: 2022-04-12 13:47:27
 */
Function.prototype.myBind = function (context) {
    if (typeof this !== "function") {
      throw new Error("Function.prototype.bind - what is trying to be bound is not callable");
    }
    // this指向调用myBind的对象，也就是绑定函数的 this
    var self = this;

    // 获取myBind函数从第二个参数到最后一个参数
    var args = Array.prototype.slice.call(arguments, 1);
    // 创建一个空对象
    var fNOP = function () {};

    var fBound = function () {
        // 这个时候的arguments是指myBind返回的函数传入的参数，也就是使用myBind返回函数时，再调用该函数传入的参数
        var bindArgs = Array.prototype.slice.call(arguments);
        // 当 bind 返回的函数作为构造函数的时候，解决bind 时指定的 this 值会失效问题
        // 用instanceof可判断一个对象是否是构造函数的实例
        // 当作为构造函数时，this 指向构造函数创造的实例，此时结果为 true，将绑定函数的 this 指向该实例，可以让实例获得来自绑定函数的值
        // 当作为普通函数时，this 指向 window，此时结果为 false，将绑定函数的 this 指向 context
        // 总结：构造函数apply将this指向实例，此时的this指的是调用MyBind的对象；普通函数时apply将this指向调用MyBind时传入的context，此时的this指向window
        return self.apply(this instanceof fBound ? this : context, args.concat(bindArgs));
    }
    // 修改返回函数的 prototype 为绑定函数的 prototype，fBound new出来的实例就可以继承绑定函数的原型中的值
    fNOP.prototype = self.prototype;
    // 设置空函数进行周转，以免'直接修改 fBound.prototype 的时候，也会直接修改绑定函数的 prototype'的问题
    fBound.prototype = new fNOP();
    return fBound;
}

// Test Function
var foo = {
    value: 1
};
function bar(name,age) {
    this.value = 2
    this.habit = 'shopping';
    console.log(this.value);
    console.log(name);
    console.log(age);
}
bar.prototype.friend = 'kevin';


// use myBind
// 作为构造函数时
var bindFoo = bar.myBind(foo,'Ailse');
var obj = new bindFoo('18');
// 2
// Ailse
// 18
console.log(obj.habit); // shopping
console.log(obj.friend); // Ailse //18

// 作为普通函数时
var bindFoo = bar.myBind(foo,'Ailse');
bindFoo('18') 
// 1
// daisy
// 18



// 按照以上作为构造函数的例子解析
// self此时指向bar
// args是['Ailse']
// bindArgs是['18']
// this instanceof fBound是true，此时this指向obj
// self.apply(obj, args.concat(bindArgs))
// fNOP.prototype = bar.prototype
// fBound.prototype = new fNOP

// 按照以上作为普通函数的例子解析
// self此时指向bar
// args是['Ailse']
// bindArgs是['18']
// this instanceof fBound是fasle，此时this指向window
// self.apply(foo, args.concat(bindArgs))
// fNOP.prototype = bar.prototype
// fBound.prototype = new fNOP