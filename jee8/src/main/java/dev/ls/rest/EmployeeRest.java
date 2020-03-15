package dev.ls.rest;
import java.net.URI;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
	// here the service layer gets injected
	
	@Context
	UriInfo uriInfo;
	
	@Path("/employee")
	@POST
	/*public Response createEmployee(Employee employee) {
		employeeService.createEmployee(employee);
		return Response.ok(employee).build();
	}
	*/
	public Response createEmployee(@Valid Employee employee) {
		//without the @valid annotation , validation will be done just before persisting to the database,here createEmployee() was getting called.
		//with the @valid annotation, validation is done at the resource level.wont call createEmployee().
		employeeService.createEmployee(employee);
		URI uri =uriInfo.getAbsolutePathBuilder().path(employee.getEmpId().toString()).build();
		return Response.created(uri).status(Response.Status.CREATED).build();
	}
	
	@Path("/employee/update")
	@PUT
	public Response updateEmployee(Employee employee) {
		employeeService.updateEmployee(employee);
		//URI uri = uriInfo.getAbsolutePathBuilder().path(employee.getEmpId().toString()).build();
		return Response.ok(employee).build();
		//return Response.ok(employeeService.findEmployeeById(employee.getEmpId())).status(Response.Status.CREATED).build();
	}
	
	@GET
	//@Path("{id}")
	@Path("/employee/{id: \\d+}")
	//@Path("{id: ^[0-9]+$}")	
	/*public Employee findEmployeeById(@PathParam("id") int empId) {
		return employeeService.findEmployeeById(empId);
	}*/
	public Response findEmployeeById(@PathParam("id") @DefaultValue("1") Integer empId) {// the variable part of the url is injected into the variable id by the jaxrs runtime
//		System.out.println("value from non empty path param "+empId);
		System.out.println("from findEmployeeById method()"+empId);
		return Response.ok(employeeService.findEmployeeById(empId)).status(Response.Status.FOUND).build();
	}
	
	@GET
	@Path("/employee/")
	public Response findEmployeeEmptyPath(){
		int empId= 1;
		System.out.println("value from empty path param "+empId);
		return Response.ok(employeeService.findEmployeeById(empId)).status(Response.Status.FOUND).build();
	}
	
	
	
	@GET
	@Path("/employee/id")
	public Response findEmployeeByQueryId(@QueryParam("id") @DefaultValue("1") Integer empId) {
		System.out.println("value from query param "+empId);
		return Response.ok(employeeService.findEmployeeById(empId)).status(Response.Status.FOUND).build();		
	}
	//these set of 3 methods are there to resolve the issue of "URI should be, by definition, unique "
	//default values would contradict this paradigm because you could not distinctly say which resource answers which URI when omitting parts of the URI and replacing them with implicit default values.
	
	
	@Path("/employee/list")
	@GET
	/*public List<Employee> getEmployees(){
		return employeeService.getEmployees();
	}*/
	public Response getEmployees() {
		return Response.ok(employeeService.getEmployees()).status(Response.Status.OK).build();
	}
}
