package dev.ls.service;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ConstrintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException>{

	@Override
	public Response toResponse(ConstraintViolationException exception) {
		final Map<String, String> constraintViolations = new HashMap<>();
		for(ConstraintViolation<?> cv : exception.getConstraintViolations()) {
			String path = cv.getPropertyPath().toString();
			constraintViolations.put(path, cv.getMessage());
		}
		return Response.status(Response.Status.PRECONDITION_FAILED).entity(constraintViolations).build();		
	}

}
