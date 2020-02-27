package dev.ls.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
//import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="EMP")
public class Employee {
	@Id	
	//@GeneratedValue(strategy = GenerationType.AUTO)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@GeneratedValue(strategy =GenerationType.SEQUENCE, generator = "EMP_REST_SEQ")
	@SequenceGenerator(name="EMP_REST_SEQ",sequenceName = "EMP_REST_SEQ",allocationSize = 1)
	@Column(name = "EMP_ID")	
	private Integer empId;
	
	@Column(name="EMP_NAME")
	private String empName;
	
	@Column(name="EMP_ROLE")
	private String empRole;
	
	@Column(name = "COMPETENCE")
	private String competence;
	
	@Column(name="MOD_DATE")
	private LocalDate modificationDate;
	
	public String getCompetence() {
		return competence;
	}
	public void setCompetence(String competence) {
		this.competence = competence;
	}
	
	
	public LocalDate getModificationDate() {
		return modificationDate;
	}
	public void setModificationDate(LocalDate modificationDate) {
		this.modificationDate = modificationDate;
	}
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpRole() {
		return empRole;
	}
	public void setEmpRole(String empRole) {
		this.empRole = empRole;
	}
			
	//lifecycle callback methods
	@PrePersist
	public void stampAutoDate() {
		this.setModificationDate(LocalDate.now());
	}
	@PostPersist
	public void acknowledgeDateStamped() {		
		System.out.println("Date stamped and Object Persisted!");
	}
	
}
