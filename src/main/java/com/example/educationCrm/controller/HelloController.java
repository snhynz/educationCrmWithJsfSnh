package com.example.educationCrm.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Scope(value = "view")
@Controller
public class HelloController implements Serializable {
	private String message = "test test";
	@PostConstruct
	public void init() {
		System.out.println("Hello bean init calisti.");
	}
	public void clickHelloWorldButton() {
		System.out.println("Merhaba Dünya Tıklandı !");
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
