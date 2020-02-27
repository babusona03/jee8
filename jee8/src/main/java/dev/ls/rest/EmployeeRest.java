package dev.ls.rest;
import java.net.URI;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import dev.ls.entity.Employee;
import dev.ls.service.EmployeeService;

@Path("employee")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeRest {
	
	@Inject
	EmployeeService employeeService;
	
	@Context
	UriInfo uriInfo;
	
	@Path("new")
	@POST
	/*public Response createEmployee(Employee employee) {
		employeeService.createEmployee(employee);
		return Response.ok(employee).build();
	}
	*/
	public Response createEmployee(Employee employee) {
		employeeService.createEmployee(employee);
		URI uri =uriInfo.getAbsolutePathBuilder().path(employee.getEmpId().toString()).build();
		return Response.created(uri).status(Response.Status.CREATED).build();
	}
	
	@Path("update")
	@PUT
	public Response updateEmployee(Employee employee) {
		employeeService.updateEmployee(employee);
		//URI uri = uriInfo.getAbsolutePathBuilder().path(employee.getEmpId().toString()).build();
		return Response.ok(employee).build();
		//return Response.ok(employeeService.findEmployeeById(employee.getEmpId())).status(Response.Status.CREATED).build();
	}
	
	//@Path("{id}")
	@Path("{id: \\d+}")
	//@Path("{id: ^[0-9]+$}")
	@GET
	/*public Employee findEmployeeById(@PathParam("id") int empId) {
		return employeeService.findEmployeeById(empId);
	}*/
	public Response findEmployeeById(@PathParam("id") @DefaultValue("0") int empId) {
		return Response.ok(employeeService.findEmployeeById(empId)).status(Response.Status.FOUND).build();
	}
	
	@Path("list")
	@GET
	/*public List<Employee> getEmployees(){
		return employeeService.getEmployees();
	}*/
	public Response getEmployees() {
		return Response.ok(employeeService.getEmployees()).status(Response.Status.OK).build();
	}
}
