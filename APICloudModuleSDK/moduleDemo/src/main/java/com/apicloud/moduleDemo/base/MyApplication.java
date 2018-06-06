package com.apicloud.moduleDemo.base;

import android.util.Log;

import com.apicloud.moduleDemo.settings.Const;
import com.apicloud.moduleDemo.util.Utils;

public class MyApplication extends MyTransApplication
{
	private static final String LOG_TAG = "MyApplication";
	
	private static MyApplication s_instance = null;
	
	/*****************************************************************************/

	// Activity被回收时，保存全局变量的版本号（每保存一次，自增1）
	public int m_nSaveInstanceStateVersion = 0;
	/*****************************************************************************/

	//登录用户Id
	public String m_strUuid = "";
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
		// Activity被回收时，保存全局变量的版本号（每保存一次，自增1）
		m_nSaveInstanceStateVersion = 0;
		/*****************************************************************************/

		m_strUuid = "";
		m_strUserToken = "";
	}
}
