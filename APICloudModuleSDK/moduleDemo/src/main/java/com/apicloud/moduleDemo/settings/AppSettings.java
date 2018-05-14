package com.apicloud.moduleDemo.settings;

import com.blankj.utilcode.util.SPUtils;

/**
 *
 */
//  保存本应用程序的各种设置值
public class AppSettings
{
	public final static String serverSp = "serverSp";

	private static final String AUTO_LOGIN = "AutoLogin";	// 头像ID
	private static final String HEAD_PIC = "HeadPic";		// 自动登录
	private static final String NICKNAME = "Nickname";		// 昵称
	private static final String USER_ID = "UserId";			// 用户id
	private static final String USER_PHONE = "UserPhone";	// 用户手机号

	private AppSettings()
	{
		// 不允许声明AppSettings对象
	}

	// 用户id
	public static String getUserId()
	{
		return SPUtils.getInstance(serverSp).getString(USER_ID,"");
	}
	public static void setUserId(String strUserId)
	{
		SPUtils.getInstance(serverSp).put(USER_ID,strUserId);
	}

	// 用户手机号
	public static String getPhone()
	{
		return SPUtils.getInstance(serverSp).getString(USER_PHONE,"");
	}
	public static void setPhone(String strPhone)
	{
		SPUtils.getInstance(serverSp).put(USER_PHONE,strPhone);
	}

	// 头像ID
	public static String getHeadPic()
	{
		return SPUtils.getInstance(serverSp).getString(HEAD_PIC,"");
	}
	public static void setHeadPic(String strPicId)
	{
		SPUtils.getInstance(serverSp).put(HEAD_PIC,strPicId);
	}

	// 昵称
	public static String getNickname()
	{
		return SPUtils.getInstance(serverSp).getString(NICKNAME,"");
	}
	public static void setNickname(String strNickname)
	{
		SPUtils.getInstance(serverSp).put(NICKNAME,strNickname);
	}
	
	// 自动登录
	public static boolean isAutoLogin()
	{
		return SPUtils.getInstance(serverSp).getBoolean(AUTO_LOGIN,false);
	}
	public static void setAutoLogin(boolean bState)
	{
		SPUtils.getInstance(serverSp).put(AUTO_LOGIN,bState);
	}
}

