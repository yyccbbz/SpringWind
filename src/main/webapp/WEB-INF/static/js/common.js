/**
 * 日期格式化
 * @param time
 * @returns {*}
 */
function formatDate(time) {
    if (time == '' || time == null || time == undefined) return '';
    var datetime = new Date();
    datetime.setTime(time);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    var hour = datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime.getHours();
    var minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    var second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
    return year + "年" + month + "月" + date + "日 " + hour + ":" + minute + ":" + second;
}


/**
 * 下载文件,以POST的方式提交---同一个页面
 * @param options{url,data}
 * 使用方式
 * downLoadFile({
 *      url:'xxx.download', //请求的url
 *      data:{name:xxx,age:xxx}//要发送的数据
 *      callback:function(){}
 * });
 *
 */
var downLoadFile = function (options) {
    var config = $.extend(true, {method: 'post'}, options);
    var $form = $('<form target="_self" method="' + config.method + '" />');
    $form.attr('action', config.url);
    for (var key in config.data) {
        $form.append('<input type="hidden" name="' + key + '" value="' + config.data[key] + '" />');
    }
    $(document.body).append($form);
    if (config.callback != undefined) {
        config.callback();
    }
    $form[0].submit();
};

/**下载文件---新页面
 * options:{
 * url:‘‘,              //下载地址
 * data:{name:value},   //要发送的数据
 * method:‘post‘
 * }
 */
var DownLoadFile = function (options) {
    var config = $.extend(true, { method: 'post'}, options);
    var $iframe = $('<iframe id="down-file-iframe" />');
    var $form = $('<form target="down-file-iframe" method="' + config.method + '" />');
    $form.attr('action', config.url);
    for (var key in config.data) {
        $form.append('<input type="hidden" name="' + key + '" value="' + config.data[key] + '" />');
    }
    $iframe.append($form);
    $(document.body).append($iframe);
    $form[0].submit();
    $iframe.remove();
}

/**
 * ajax上传文件
 */
var ajaxFileUpload = function(url,fileId) {
    $.ajaxFileUpload({
        url: url,                              //用于文件上传的服务器端请求地址
        type: "post",                          //post请求方式
        secureuri: false,                      //一般设置为false
        fileElementId: "fileExcel",            //文件上传空间的id属性
        dataType: "json",                      //返回值类型 一般设置为json
        success: function (data, status) {     //服务器成功响应处理函数
            console.log(data);
            //刷新当前页
            reload();
            /** 0:失败,1:成功,2:未登录或session过期,3:无权限 */
            if (data.code == 0) {
                alert(data.msg);
            } else if (data.code == 1) {
                var excelName = data.obj.excelName;
                alert(excelName + " ,文件成功上传 ，" + data.msg);
            } else {
                alert(data.msg);
            }
        },
        error: function (data, status, e) {     //服务器响应失败处理函数
            alert(data.msg + "---" + e);
        }
    });
};
















