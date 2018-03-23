package com.devopslam.zuulfilters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.springframework.util.ReflectionUtils.rethrowRuntimeException;

public class QueryParamPortPreFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        // run after PreDecorationFilter
        return 5 + 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        return context.getRequest().getParameter("port") != null;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String port = request.getParameter("port");
        try {
            URL url = UriComponentsBuilder.fromUri(context.getRouteHost().toURI())
                    .port(port)
                    .build().toUri().toURL();
            context.setRouteHost(url);
        } catch (Exception e) {
            rethrowRuntimeException(e);
        }
        return null;
    }
}
