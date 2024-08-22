
var HashMap = function () {
    let map = {}; 

    return {
        put: function (k, v) {
            map[k] = v;
        },
        keys: function () {
            //Object.keys() 回傳一個陣列
            return Object.keys(map);            
        },
        contains: function (k) {
            //Array.includes()會判斷陣列是否包含特定的元素
            return Object.keys(map).includes(k);
        },
        get: function (k) {
            return map[k];
        },
        clear: function () {
            return map = {};
        }
    };
};