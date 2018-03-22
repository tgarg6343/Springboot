package com.tarun.rest.restwebservice.user;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class User {
private Integer id;
@Size(min=2)
private String name;
@Past
private Date date;

public User() {
	
}
public User(int id, String name, Date date) {
	super();
	this.id = id;
	this.name = name;
	this.date = date;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
}
