package kr.kyle.study02.common;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author kyuchang
 *
 */
public class Entity implements Serializable, Cloneable {
	private static final long serialVersionUID = 6169160697200362751L;

	protected Entity() {
		// init();
	}

	/*
	private void init() {
		String fieldName = null;
		String dataType = null;
		Field[] fields = null;

		fields = this.getClass().getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			// private member access
			if (!f.isAccessible())
				f.setAccessible(true);
			fieldName = f.getName();

			if (fieldName.equals("serialVersionUID"))
				continue;
			try {
				dataType = f.getType().toString();
				if ("int".equals(dataType)) {
					f.setInt(this, 0);
				} else if (dataType.indexOf("java.lang.String") > -1) {
					f.set(this, "");
				} else if (dataType.startsWith("class")) {
					f.set(this, null);
				}
			} catch (Exception e) {
			}
		}
	}
	*/

	public Object createClone() {
		Object obj = null;

		try {
			obj = clone();
		} catch (CloneNotSupportedException e) {
		}
		return obj;
	}

	/**
	 * VO(Value Object)는 테이블과 1:1 관계로 생성 해야 하며 VO의 private member variable name
	 * 은 테이블의 필드명과 동일한 소문자로 생성해야 한다 이렇게 생성된 VO에 DB에서 가져온 값을 자동으로 매핑 시켜주는 메소드 현재는
	 * String type 과 int 2가지 타입만 지원한다
	 * 
	 * @param m
	 *            : 필드명 key, 데이터 value
	 */
	public void valueMapping(Map<String, Object> m) {
		String fieldName = null;
		String fieldValue = null;
		Field[] fields = null;

		fields = this.getClass().getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			// private member access
			if (!f.isAccessible())
				f.setAccessible(true);
			fieldName = f.getName();
			fieldValue = String.valueOf(m.get(fieldName));

			if (fieldName.equals("serialVersionUID"))
				continue;
			try {
				if ("int".equals(f.getType().toString())) {
					if (fieldValue == null || "".equals(fieldValue))
						fieldValue = "0";
					f.setInt(this, Integer.parseInt(fieldValue));
				} else {
					f.set(this, ("null".equals(fieldValue) ? "" : fieldValue));
				}
			} catch (Exception e) {
			}
		}
	}
	
	private Map<String, Object> getMap(){
		Map<String, Object> map = null;
		Method[] ma = null;
		
		map = new HashMap<String, Object>();
		ma = this.getClass().getMethods();
		
		for (Method m : ma){
			if (m.getName().indexOf("get") != -1){
				try {
					map.put(m.getName(), m.invoke(this, (Object[]) null));
				} catch (Exception e) {}
			}
		}

		return map;
	}

	public String toString() {
		StringBuffer sb = null;
		Map<String, Object> map = null;

		map = this.getMap();
		sb = new StringBuffer();

		Iterator<String> iter = map.keySet().iterator();
		
		sb.append("[ VO Name = ").append(this.getClass().getName()).append("\n");

		while (iter.hasNext()){
			String key = iter.next();
			
			if ("getClass".equals(key)) continue;

			sb.append(key).append("() = (").append(map.get(key)).append(")\n");
		}

		sb.append("]\n");

		return sb.toString();
	}

	public String toHTMLString() {
		StringBuffer sb = null;
		Map<String, Object> map = null;

		map = this.getMap();
		sb = new StringBuffer();

		Iterator<String> iter = map.keySet().iterator();
		
		sb.append("[ <h3>VO Name = ").append(this.getClass().getName()).append("</h3>");

		while (iter.hasNext()){
			String key = iter.next();

			sb.append(key).append("() = (").append(map.get(key)).append(")<br>\n");
		}

		sb.append("]<br>\n");

		return sb.toString();
	}

	public String toXMLString() {
		String className = null;
		String fieldName = null;
		String fieldValue = null;
		StringBuffer sb = null;
		Field[] fa = null;

		className = this.getClass().getName()
				.substring(this.getClass().getName().lastIndexOf(".") + 1);
		fa = this.getClass().getDeclaredFields();
		sb = new StringBuffer();

		sb.append("<").append(className).append(">");

		for (int i = 0; i < fa.length; i++) {
			Field field = fa[i];
			try {
				// private member access
				if (!field.isAccessible())
					field.setAccessible(true);

				fieldName = field.getName();

				// 멤버변수명이 serialVersionUID 인경우 스킵
				if (fieldName.equals("serialVersionUID"))
					continue;

				// type 이 다른 객체의 값을 String 으로 변환
				fieldValue = String.valueOf((field.get(this) == null) ? ""
						: field.get(this));

			} catch (Exception e) {
				// Exception 이 발생할 경우 쓰레기 값이 아닌 null 값으로 셋팅
				fieldValue = "";
			}
			sb.append("<").append(fieldName).append(">").append(fieldValue)
					.append("</").append(fieldName).append(">");
		}
		sb.append("</").append(className).append(">");

		return sb.toString();
	}
}

