// 可以返回新数组的话就记这个
function unique(arr){
    var map = new Map()
    return arr.filter(item => {
        if(map.has(item))
            return false
        else{
            map.set(item, true)
            return true
        }
    })
}

// test
let arr =  [1, 1, 'true', 'true', true, true, 15, 15, false, false, undefined, undefined, null, null, NaN, NaN,
'NaN', 0, 0, 'a', 'a', {}, {}];
let res = unique(arr)
console.log(res) 
// [1,'true',true, 15,false, undefined,null,  NaN,'NaN', 0,'a', {},{}]