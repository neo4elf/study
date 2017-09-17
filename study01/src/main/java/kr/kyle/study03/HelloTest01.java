package kr.kyle.study03;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.context.support.StaticApplicationContext;

public class HelloTest01 {
	
	public static void main(String[] args) {
		HelloTest01 self = new HelloTest01();
		
//		self.registerBeanWithDependency();
		
//		self.genericApplicationContext();
		
		self.hierarchicalApplicationContext();
		
	}
	
	public void hierarchicalApplicationContext() {
		Hello hello = null;
		ApplicationContext ctx = null;

		ApplicationContext parent = new GenericXmlApplicationContext("classpath:/kr/kyle/study03/parent-context.xml");
		GenericApplicationContext child = new GenericApplicationContext(parent);
		
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(child);
		reader.loadBeanDefinitions("classpath:/kr/kyle/study03/child-context.xml");
		
		child.refresh();
		
		hello = parent.getBean("hello", Hello.class);
		hello.print();
		
		ctx = hello.getContext();
		
		hello = ctx.getBean("hello", Hello.class);
		hello.print();
		
		hello = child.getBean("hello", Hello.class);
		hello.print();
		
		ctx = hello.getContext();
		
		hello = ctx.getBean("hello", Hello.class);
		hello.print();

		ctx = ctx.getParent();
		hello = ctx.getBean("hello", Hello.class);
		hello.print();
	}
	
	public void genericApplicationContext() {
		GenericApplicationContext context = new GenericApplicationContext();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
		
		// classpath 를 기준으로 찾는다
		// "classpath:", "file:", "http:" 와 같은 지정자를 사용 할 수 있다
		reader.loadBeanDefinitions("classpath:/kr/kyle/study03/hello-context.xml");
		context.refresh();
		
		Hello hello = context.getBean("hello", Hello.class);
		hello.print();
		context.close();
	}
	
	public void registerBeanWithDependency() {
		StaticApplicationContext ac = new StaticApplicationContext();
		
		ac.registerBeanDefinition("printer", new RootBeanDefinition(ConsolePrinter.class));
		
		BeanDefinition helloDef = new RootBeanDefinition(Hello.class);
		helloDef.getPropertyValues().addPropertyValue("name", "Spring");
		helloDef.getPropertyValues().add("printer", new RuntimeBeanReference("printer"));
		
		ac.registerBeanDefinition("hello", helloDef);
		
		Hello hello = ac.getBean("hello", Hello.class);
		hello.print();
		
//		assertThat(ac.getBean("printer").toString(), is("Hello Spring"));
		ac.close();

	}
}
