function deepClone(data) {
    if(typeof(data) !== 'object')
        return 
    var newObj = obj instanceof Array ? []:{}
    for(var key in obj){
        if(obj.hasOwnProperty(key)){
            newObj[key] = typeof obj[key] === 'object' ? deepClone(obj[key]) : obj[key]
        }
    }
    return newObj
}