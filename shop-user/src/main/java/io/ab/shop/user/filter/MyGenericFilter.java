package io.ab.shop.user.filter;

import brave.Span;
import brave.Tracer;
import org.springframework.cloud.sleuth.instrument.web.SleuthWebProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * @Description 自定义链路过滤器，
 * 只有HTTP或者HTTPS请求才能访问接口，
 * 访问的链接不是静态文件时，将traceId放入HttpRequest中在服务端获取，
 * 并在响应结果中添加自定义Header，名称为SLEUTH-HEADER，值为traceId。
 * @Return
 * @Date 2023/1/19 18:49
 */
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE + 6)
public class MyGenericFilter extends GenericFilterBean {

    private Pattern skipPattern = Pattern.compile(SleuthWebProperties.DEFAULT_SKIP_PATTERN);

    private final Tracer tracer;
    public MyGenericFilter(Tracer tracer){
        this.tracer = tracer;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 只支持HTTP访问
        if (!(servletRequest instanceof HttpServletRequest) || !(servletResponse instanceof HttpServletResponse)){
            throw new ServletException("只支持HTTP访问");
        }

        Span currentSpan = this.tracer.currentSpan();
        if (currentSpan == null){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = ((HttpServletResponse) servletResponse);

        boolean skipFlag = skipPattern.matcher(httpServletRequest.getRequestURI()).matches();
        if (!skipFlag){
            String traceId = currentSpan.context().traceIdString();
            httpServletRequest.setAttribute("traceId", traceId);
            // 放到 header 中
            httpServletResponse.addHeader("SLEUTH-HEADER", traceId);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
