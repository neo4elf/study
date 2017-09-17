package kr.kyle.study03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class Hello {
	String name;
	Printer printer;
	
	@Autowired
	ApplicationContext ctx;
	
	public ApplicationContext getContext() {
		return this.ctx;
	}
	
	public String sayHello() {
		return "Hello " + name;
	}
	
	public void print() {
		this.printer.print(sayHello());
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrinter(Printer printer) {
		this.printer = printer;
	}
}
