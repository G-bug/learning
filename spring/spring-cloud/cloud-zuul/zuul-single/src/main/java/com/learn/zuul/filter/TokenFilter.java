/**
 * @author G-bug 2019/11/28
 */
package com.learn.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @auth Administrator
 */
@Component
public class TokenFilter extends ZuulFilter {

    @Override
    // 定义filter的类型, 四种 pre routing post error
    public String filterType() {
        return "pre";
    }

    @Override
    // filter执行顺序, 返回越小, 越先执行
    public int filterOrder() {
        return 10;
    }

    @Override
    // 是否需要执行该filter
    public boolean shouldFilter() {
        return true;
    }

    @Override
    // filter的具体操作
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String token = request.getParameter("token");
        if (StringUtils.isNotBlank(token)) {
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);
        } else {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(200);
            ctx.setResponseBody("token is empty");
            ctx.set("isSuccess", 200);
        }
        return null;
    }
}
