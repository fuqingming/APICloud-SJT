package com.apicloud.moduleDemo.base;

import android.util.Log;

import com.apicloud.moduleDemo.settings.Const;
import com.apicloud.moduleDemo.util.Utils;

public class MyApplication extends MyTransApplication
{
	private static final String LOG_TAG = "MyApplication";
	
	private static MyApplication s_instance = null;
	
	/*****************************************************************************/
	private String m_strWorkKey = "";

	// Activity被回收时，保存全局变量的版本号（每保存一次，自增1）
	public int m_nSaveInstanceStateVersion = 0;
	/*****************************************************************************/

	//登录手机号
	public String m_strMobile = "";
	//登录用户Id
	public String m_strUserId = "";
	// 用户登录令牌
	public String m_strUserToken = "";
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		
		Log.d(LOG_TAG, "onCreate()");
		
//		if (TEST_ENVIRONMENT) 
//		{
//			CrashHandler crashHandler = CrashHandler.getInstance();
//			crashHandler.init(getApplicationContext());
//		}
		s_instance = this;
	}
	
	public static MyApplication getInstance()
	{
		return s_instance;
	}
	
	public void init()
	{
		/*****************************************************************************/
		m_strWorkKey = "";
		// Activity被回收时，保存全局变量的版本号（每保存一次，自增1）
		m_nSaveInstanceStateVersion = 0;
		/*****************************************************************************/

		m_strMobile = "";
		m_strUserId = "";
		m_strUserToken = "";
	}
	
	public String getWorkKey()
	{
		if(m_strWorkKey.isEmpty())
		{
			return Const.DEFAULT_WORK_KEY;
		}
		else
		{
			return m_strWorkKey;
		}
	}
	
	public void setWorkKey(String strWorkKey)
	{
		m_strWorkKey = strWorkKey;
	}

	public static String getAppVersion()
	{
		return "V" + String.valueOf(Utils.getAppVersionName(s_instance));
	}
	
	public void exit() 
	{
		// logout
		//UserService.doLogout(s_instance.getWorkKey());
		
		try
		{
			Thread.sleep(100);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		System.exit(0);
	}	
}
