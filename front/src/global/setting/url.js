
let baseUrl = "";
if (process.env.NODE_ENV === 'production') {
    // 打包环境
    baseUrl = 'http://123.57.92.187:8082'
};
if (process.env.NODE_ENV === 'development') {
    // 开发环境
    baseUrl = '/api'
};

let url = {}

url.url = function(url) {
    return baseUrl + url
};

url.updateUrl = url.url("/common/uploadFile"); // 单文件上传


url.imgFile = function(url, gurl) {
        return {
            "name": url,
            "url": gurl,
            "response": {
                "msg": "操作成功",
                "code": 0,
                "data": {
                    "url": url
                }
            }
        }
    }

export default url;