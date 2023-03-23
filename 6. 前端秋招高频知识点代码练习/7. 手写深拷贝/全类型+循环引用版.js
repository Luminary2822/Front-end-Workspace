function deepClone(obj, hash = new WeakMap()) {
    // 一些前提条件判断
    if(obj === null) return obj
    if(typeof(obj) !== 'object') return obj
    if(obj instanceof Date) return new Date()
    if(obj instanceof RegExp) return new RegExp()
    // 出现循环引用，判断之前是否拷贝过，有直接返回之前拷贝过的内容
    if(hash.get(obj)) return hash.get(obj)

    let cloneObj = new obj.constructor()
    hash.set(obj, cloneObj)
    for(var key in obj){
        if(obj.hasOwnProperty(key)) {
             cloneObj[key] = deepClone(obj[key], hash)
        }
    }
    return cloneObj
}