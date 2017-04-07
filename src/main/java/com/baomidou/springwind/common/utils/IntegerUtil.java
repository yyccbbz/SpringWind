package com.baomidou.springwind.common.utils;

public class IntegerUtil {

	public static Integer getInt(Object o) {
		if (o == null) {
			return null;
		}
		try {
			return Integer.parseInt(o.toString());
		} catch (Exception e) {
		}
		return null;
	}

	public static Integer getIntZero(Object o) {
		if (o == null) {
			return 0;
		}
		try {
			return Integer.parseInt(o.toString());
		} catch (Exception e) {
		}
		return 0;
	}

}
