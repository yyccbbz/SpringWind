package com.baomidou.springwind.service.impl;

import com.baomidou.framework.aop.LogPoint;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.Token;
import com.baomidou.kisso.common.IpHelper;
import com.baomidou.springwind.common.HttpHelper;
import com.baomidou.springwind.entity.SystemLog;
import com.baomidou.springwind.mapper.SystemLogMapper;
import com.baomidou.springwind.service.ISystemLogService;
import com.baomidou.springwind.service.support.BaseServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Enumeration;

/**
 * <p>
 * 操作日志表 服务实现类
 * </p>
 *
 * @author CuiCan
 * @since 2017-05-17
 */
@Service
public class SystemLogServiceImpl extends BaseServiceImpl<SystemLogMapper, SystemLog> implements ISystemLogService,LogPoint {

    private static final String LOG_CONTENT = "[类名]:%s,[方法]:%s,[参数]:%s,[IP]:%s";

    @Override
    public void saveLog(ProceedingJoinPoint joinPoint, String methodName, String operate) {
        /**
         * 日志入库
         */
        HttpServletRequest request = HttpHelper.getHttpServletRequest();
        SystemLog sl = new SystemLog();
        Token tk = SSOHelper.attrToken(request);
        if ( tk != null ) {
            sl.setUid(tk.getId());
        }
        sl.setContent(operateContent(joinPoint, methodName, request));
        sl.setOperation(operate);
        sl.setCreateTime(new Date());
        baseMapper.insert(sl);
    }

    /**
     * 获取当前执行的方法
     *
     * @param joinPoint
     *            连接点
     * @param methodName
     *            方法名称
     * @return 操作内容
     */
    @SuppressWarnings("unchecked")
    public String operateContent(ProceedingJoinPoint joinPoint, String methodName, HttpServletRequest request) {
        String className = joinPoint.getTarget().getClass().getName();
        Object[] params = joinPoint.getArgs();
        StringBuffer bf = new StringBuffer();
        if (params != null && params.length > 0) {
            Enumeration<String> paraNames = request.getParameterNames();
            while (paraNames.hasMoreElements()) {
                String key = paraNames.nextElement();
                bf.append(key).append("=");
                bf.append(request.getParameter(key)).append("&");
            }
            if (StringUtils.isBlank(bf.toString())) {
                bf.append(request.getQueryString());
            }
        }
        return String.format(LOG_CONTENT, className, methodName, bf.toString(), IpHelper.getIpAddr(request));
    }

}
