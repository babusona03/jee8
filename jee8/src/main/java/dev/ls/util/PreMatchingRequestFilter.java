package dev.ls.util;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
public class PreMatchingRequestFilter implements ContainerRequestFilter{
	
	@Inject
	Logger logger; 

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		logger.log(Level.INFO,"Original Method was:"+requestContext.getMethod());
		String httpMethod = requestContext.getHeaderString("X-Http-Method-Override");
		if(httpMethod != null && !httpMethod.isEmpty()) {
			logger.log(Level.INFO, "Method in header:"+httpMethod);
			requestContext.setMethod(httpMethod);
			logger.log(Level.INFO, "Altered method:"+requestContext.getMethod());
		}
		
	}

}
