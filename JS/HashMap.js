
var HashMap = function () {
    let obj = {};

    return {
        put: function (k, v) {
            obj[k] = v;
        },
        keys: function () {
            const list = [];
            for (let key in obj) {
                list.push(key);
            }
            return list;
        },
        contains: function (k) {
            if (obj[k]) {
                return true;
            } else {
                return false;
            }
        },
        get: function (k) {
            return obj[k];
        },
        clear: function () {
            return obj = {};
        }
    };
};