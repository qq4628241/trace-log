package com.scott.trace.filter;

import com.scott.trace.constants.TraceConstants;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;

import java.io.IOException;
import java.util.UUID;

public class TraceMDCFilter implements Filter{
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            if (request instanceof HttpServletRequest) {
                String traceId = ((HttpServletRequest) request).getHeader(TraceConstants.TRACE_ID_HEADER);

                if (traceId == null || traceId.isEmpty()) {
                    traceId = UUID.randomUUID().toString().replace(TraceConstants.STR_HYPHEN, TraceConstants.STR_EMPTY);  // Generate a fallback request ID
                }

                // Set request ID in MDC
                MDC.put(TraceConstants.TRACE_ID, traceId);
            }

            // Proceed with the next filter in the chain
            chain.doFilter(request, response);
        } finally {
            // Clear MDC to avoid memory leaks
            MDC.remove(TraceConstants.TRACE_ID);
        }
    }

    @Override
    public void destroy() {}
}
