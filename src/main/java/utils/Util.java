package com.kok.sport.utils;

import java.util.Set;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map.Entry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Util {

	public static int getPageCount(int rows, int pageSize) {
//		//总记录数
//		int rows=21;  
//		//每页显示的记录数
//		int pageSize=5;  
		// 页数
		int pageSum = (rows - 1) / pageSize + 1;
		return pageSum;
	}

	public static String processVars(String sql, JsonObject asJsonObject) {

		JsonObject JsonObject1 = asJsonObject;
		// new JsonParser().parse(t).getAsJsonObject();
		Set<Entry<String, JsonElement>> setE = JsonObject1.entrySet();
		for (Entry<String, JsonElement> entry : setE) {
//			System.out.println(entry.getKey());
//			System.out.println(entry.getValue());
			JsonElement value = entry.getValue();
			sql = sql.replace("@" + entry.getKey() + "@", "'" + value.getAsString() + "'");
		}
		return sql;

//        sql = sql.replace("@name_zh@", "'" + item.name_zh + "'")
//
//		        sql = sql.replace("@short_name_zh@", "'" + item.short_name_zh + "'")
//
//				        sql = sql.replace("@name_zht@", "'" + item.name_zht + "'")
//
//						        sql = sql.replace("@short_name_zht@", "'" + item.short_name_zht + "'")
//
//								        sql = sql.replace("@name_en@", "'" + item.name_en + "'")
//
//										        sql = sql.replace("@short_name_en@", "'" + item.short_name_en + "'")
//
//												         sql = sql.replace("@logo@", "'" + item.logo + "'")

		// return null;
	}

	public static Object getPageCount(Object object, Object object2) {
		// TODO Auto-generated method stub
		return getPageCount(Integer.parseInt(object.toString()), Integer.parseInt(object2.toString()));
	}

	public static void toFile(Object o, String f) {
		try {
			// 序列化对象到文件中
			// String f = "template";
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.writeObject(o);
			oos.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Object ObjfromFile(String f) {
		// 反序列化
		File file = new File(f);
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream(f));
			return ois.readObject();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		// System.out.println(newUser.toString());
	}

	public static void ifNotNullnEmptyConvertInt(Object object) {
		if (object != null && (object).toString().trim().length() > 0) {
//			try {
			int page = Integer.parseInt((object).toString().trim());
			System.out.println(page);
//			} catch (Exception e) {
//				throw new RuntimeException(arg0, e);
//			}

		}

	}

}
