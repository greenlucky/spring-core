package com.devopslam.zuulfilters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

import static org.springframework.util.ReflectionUtils.rethrowRuntimeException;

public class UppercaseRequestEntityFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 6;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        return context.getRequest().getParameter("service") == null;
    }

    @Override
    public Object run() throws ZuulException {
        try {
            RequestContext context = RequestContext.getCurrentContext();
            InputStream inputStream = (InputStream) context.get("requestEntity");
            if(inputStream == null) inputStream = context.getRequest().getInputStream();

            String body = StreamUtils.copyToString(inputStream, Charset.forName("UTF-8"));
            body = body.toUpperCase();
            context.set("requestEntity", new ByteArrayInputStream(body.getBytes("UTF-8")));
        } catch (Exception e) {
            rethrowRuntimeException(e);
        }
        return null;
    }
}
