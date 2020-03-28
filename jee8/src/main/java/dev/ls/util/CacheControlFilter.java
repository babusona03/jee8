package dev.ls.util;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.ext.Provider;

@Provider
public class CacheControlFilter implements ContainerResponseFilter{
	// this filter will be invoked for both /credentials/credentials and /credentials/credentials/{id}
	// for find by id, the if condition wont match, will do for find all
	
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		 String method = requestContext.getMethod();
		 String path = requestContext.getUriInfo().getPath();
		 System.out.println("From Filter. method ="+method+" path = "+path);
		 if(method.equalsIgnoreCase("GET") && path.equalsIgnoreCase("/credentials/credentials")) {
			 CacheControl cacheControl = new CacheControl();
			 cacheControl.setMaxAge(100);
			 cacheControl.setPrivate(true);
			 responseContext.getHeaders().add("Cache-Control", cacheControl);
			 responseContext.getHeaders().add("Custom-Message","REST API");
		 }
	}
}
