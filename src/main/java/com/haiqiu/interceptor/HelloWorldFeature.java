package com.haiqiu.interceptor;

import org.apache.cxf.Bus;
import org.apache.cxf.feature.AbstractFeature;
import org.apache.cxf.interceptor.InterceptorProvider;

/**
 * Created by T430S on 2016/1/23.
 */
public class HelloWorldFeature extends AbstractFeature {

    @Override
    public void initialize(InterceptorProvider provider, Bus bus) {
//        super.initialize(interceptorProvider, bus);
/*        provider.getInInterceptors().add(new LoggingInInterceptor());
        provider.getInInterceptors().add(new HelloInInterceptor());
        provider.getOutInterceptors().add(new LoggingOutInterceptor());*/
    }
}
