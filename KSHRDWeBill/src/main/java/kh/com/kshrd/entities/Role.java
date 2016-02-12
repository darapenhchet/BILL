package kh.com.kshrd.entities;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4429171405229541913L;
	
	private Integer id;
	
	private String name;
	
	private Boolean status; 
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the status
	 */
	public Boolean getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return name;
	}
	
}
