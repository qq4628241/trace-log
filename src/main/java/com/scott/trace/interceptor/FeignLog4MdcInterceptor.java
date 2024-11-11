package com.scott.trace.interceptor;


import com.scott.trace.constants.TraceConstants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class FeignLog4MdcInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            String authHeader = request.getHeader(TraceConstants.TRACE_ID_HEADER);
            if (authHeader != null) {
                requestTemplate.header(TraceConstants.TRACE_ID_HEADER, authHeader);
            }
        }
    }
}

