package cn.tedu.ttms.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class ObjectUtil {

	/**
	 * 获取属性名数组
	 */
	public static String[] getFiledName(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		String[] fieldNames = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			System.out.println(fields[i].getType());
			fieldNames[i] = fields[i].getName();
		}
		return fieldNames;
	}

	/**
	 * 获取属性名集合
	 */
	public static List<String> getFiledNameList(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		String[] fieldNames = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			System.out.println(fields[i].getType());
			fieldNames[i] = fields[i].getName();
		}
		return Arrays.asList(fieldNames);
	}

	/**
	 * 　　* @description: 使bean中为null的属性转换成空字符串
	 * 　　* @param [bean]
	 * 　　* @return void
	 * 　　* @throws
	 * 　　* @author TZH
	 * 　　* @date 2019/5/30 11:24
	 */
	public static <T> void nullToEmpty(T bean) {
		Field[] field = bean.getClass().getDeclaredFields();
		for (int j = 0; j < field.length; j++) {     //遍历所有属性
			String name = field[j].getName();    //获取属性的名字
			//将属性的首字符大写，方便构造get，set方法
			name = name.substring(0, 1).toUpperCase() + name.substring(1);
			String type = field[j].getGenericType().toString();    //获取属性的类型
			if (type.equals("class java.lang.String")) {   //如果type是类类型，则前面包含"class "，后面跟类名
				try {
					Method mGet = bean.getClass().getMethod("get" + name);
					String value = (String) mGet.invoke(bean);    //调用getter方法获取属性值
					if (value == null || "".equals(value)) {
						Method mSet = bean.getClass().getMethod("set" + name, new Class[]{String.class});
						mSet.invoke(bean, new Object[]{new String("")});
					}
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
