/**
 * 时间格式化处理
 *  调用方式： xxx.dateFtt("yyyy-MM-dd hh:mm:ss",crtTime);
 * @param fmt
 * @param date
 * @returns {*}
 */
function dateFtt(fmt, time) {
    var date = new Date(time);
    var o = {
        "M+": date.getMonth() + 1,               //月份
        "d+": date.getDate(),                    //日
        "h+": date.getHours(),                   //小时
        "m+": date.getMinutes(),                 //分
        "s+": date.getSeconds(),                 //秒
        "q+": Math.floor((date.getMonth() + 3) / 3), //季度
        "S": date.getMilliseconds()              //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}


/**
 * 日期格式化[yyyy-MM-dd HH:mm:ss]
 * @param time
 * @returns {*}
 */
function formatDateTime(time) {
    if (time == '' || time == null || time == undefined) return '';
    var datetime = new Date(time);
    // datetime.setTime(time);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    var hour = datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime.getHours();
    var minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    var second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
    return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
}

/**
 * 日期格式化[yyyy-MM-dd]
 * @param time
 * @returns {*}
 */
function formatDate(time) {
    if (time == '' || time == null || time == undefined) return '';
    var datetime = new Date(time);
    // datetime.setTime(time);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    return year + "-" + month + "-" + date;
}

/**
 * 用户 User
 * '2、普通用户 1、管理员 3、超级管理员'
 */
function formatUserType(type){
    if (type == 2) {
        return '普通用户';
    } else if (type == 1) {
        return '管理员';
    } else if (type == 3) {
        return '超级管理员';
    } else {
        return '';
    }
}


/**
 * 权限 Privilege
 *      类型：0、菜单 1、功能
 *
 * @param type
 * @returns {*}
 */
function formatPrivilegeType(type){
    if (type == 0) {
        return '菜单';
    } else if (type == 1) {
        return '功能';
    } else {
        return '';
    }
}

/**0、禁用 1、正常*/
function formatStatus(status){
    if (status == 0) {
        return '禁用';
    } else if (status == 1) {
        return '正常';
    } else {
        return '';
    }
}

/**正式名单*/
/**
 * 客户类别：1上报；2分配；3未分配vip
 * @param type
 * @returns {*}
 */
function formatFinalUserType(type){
    if (type == 1) {
        return '上报';
    } else if (type == 2) {
        return '分配';
    } else if (type == 3) {
        return '未分配vip';
    } else {
        return '';
    }
}


/**正式名单*/
/**
 * 客户类别：1上报；2分配；3未分配vip
 * @param type
 * @returns {*}
 */
function formatImportUserType(type){
    if (type == 1) {
        return '上报';
    } else if (type == 2) {
        return '分配';
    } else if (type == 3) {
        return '未分配vip';
    } else {
        return '';
    }
}

/**
 * 是否格式化：1是、0否
 * @param isyes
 * @returns {*}
 */
function formatWhether(isyes) {
    if (isyes == 1) {
        return '是';
    } else if (isyes == 0) {
        return '否';
    } else {
        return '';
    }
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


//日期插件
function calendarWithoutTime(id) {
    $(id).fdatepicker({
//            initialDate: '2017-01-01',
        format: 'yyyy-mm-dd',
        language: 'zh-CN',
        disableDblClickSelection: true,
        leftArrow:'<<',
        rightArrow:'>>',
        closeIcon:'X',
        closeButton: false
    });
}


function calendarWithTime(id) {
    $(id).fdatepicker({
//            initialDate: '2017-01-01',
        format: 'yyyy-mm-dd hh:ii:ss',
        pickTime: true,
        language: 'zh-CN',
        disableDblClickSelection: true,
        leftArrow:'<<',
        rightArrow:'>>',
        closeIcon:'X',
        closeButton: false
    });
}















