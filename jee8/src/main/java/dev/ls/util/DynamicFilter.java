package dev.ls.util;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.CacheControl;


/*
 * first , the annotation is created
 * next, this dynamic filter is created, without the provider annotation
 * then, an implementation of dynamicfeature is created;which is annotated @Provider;and the dynamic filter is also registered here in the configure method
 * finally, the method upon which the filter is to be applied, is annotated with @MaxAge
 * 
 * */



public class DynamicFilter implements ContainerResponseFilter{

	int age;
	
	public DynamicFilter() {		
	}

	public DynamicFilter(int age) {		
		this.age = age;
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		if(requestContext.getMethod().equalsIgnoreCase("GET")) {
			CacheControl cacheControl = new CacheControl();
			cacheControl.setPrivate(true);
			cacheControl.setMaxAge(age);
			responseContext.getHeaders().add("Cache-Control", cacheControl);
			responseContext.getHeaders().add("Message", "Dynamic Filter.");
		}		
	}	
}
