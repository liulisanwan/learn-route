package com.iot.util;

import java.util.UUID;

/**
 * @Author hanxiaowei
 * @Description 获取UUID
 * @Date 13:38 2023/9/8
 **/
public class UUIDUtils {

	public static String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
