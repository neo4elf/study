package kr.kyle.testing;

import static java.lang.System.out;

enum Type{
	ADMIN("관리자"), DEV("개발자"), USER("사용자");
	
	String name;
	
	Type(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}

public class EnumTest {

	public static void main(String[] args) {


		for (Type type:Type.values()) {
			out.println("type : " + type);
			out.println("type : " + type.getName());
		}

	}

}
