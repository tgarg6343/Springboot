package com.tarun.rest.restwebservice.hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import com.tarun.rest.restwebservice.hello.HelloBean;

@RestController
public class HelloController {
	
	@Autowired
	private MessageSource messageSource;
	@RequestMapping(method=RequestMethod.GET, path="/hello")
	public String getMessage() {
		return "Hello World";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/hello-bean")
	public HelloBean getBeanMessage() {
		return new HelloBean("hello Bean");
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/hello-bean/{id}")
	public HelloBean getBeanPara(@PathVariable String id) {
		return new HelloBean("hello bean"+id);
	}
	@RequestMapping(method=RequestMethod.GET, path="/hello-locale")
	public String getBeanLocale() {
		return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
	}
}
