package com.tarun.rest.restwebservice.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService userDaoService;
	
	@RequestMapping(method=RequestMethod.GET,path="/users")
	public List<User> getAllUsers(){
		return userDaoService.findAll();
	}
	
	@RequestMapping(method=RequestMethod.GET,path="/users/{id}")
	public Resource<User> getUser(@PathVariable int id) {
		User user=userDaoService.findOne(id);
		if(user==null) {
			throw new UserNotFoundException("id="+id);
		}
		
	Resource<User> resource=new Resource<User>(user);
	ControllerLinkBuilder linkTo=linkTo(methodOn(this.getClass()).getAllUsers());
	
	resource.add(linkTo.withRel("all-users"));
		
		
		
		
		return resource;
	}
	
	@RequestMapping(method=RequestMethod.POST,path="/users")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
		User addingUser=userDaoService.save(user);
		
		URI location=ServletUriComponentsBuilder.
		fromCurrentRequest().path("/{id}").buildAndExpand(addingUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@RequestMapping(method=RequestMethod.DELETE, path="/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user=userDaoService.deleteOne(id);
		if(user==null) {
			throw new UserNotFoundException("id-"+id);
		}
		
	}

}
