package com.apicloud.moduleDemo.settings;

import android.content.Context;
import android.os.Bundle;

import com.apicloud.moduleDemo.base.MyApplication;

// 用来在Activity被回收时保存和恢复公共数据
public class GlobalInstanceStateHelper
{
	// 保存数据
	public static void saveInstanceState(Bundle outState)
	{
		MyApplication myApp = MyApplication.getInstance();
		
		/** Activity被回收时，保存全局变量的版本号（每保存一次，自增1）  */
		//public int m_nSaveInstanceStateVersion = 0;
		outState.putInt("m_nSaveInstanceStateVersion", ++myApp.m_nSaveInstanceStateVersion);
		
		String strWorkKey = myApp.getWorkKey();
		if(strWorkKey.equals(Const.DEFAULT_WORK_KEY))
		{
			strWorkKey = "";
		}
		outState.putString("WORK_KEY", strWorkKey);

		outState.putString("USER_MOBILE", myApp.m_strMobile);
		outState.putString("USER_ID", myApp.m_strUserId);
		outState.putString("USER_TOKEN", myApp.m_strUserToken);
	}
	
	// 恢复数据
	public static void restoreInstanceState(Context context, Bundle savedInstanceState)
	{
		MyApplication myApp = MyApplication.getInstance();
		
		/** Activity被回收时，保存全局变量的版本号（每保存一次，自增1）  */
		//public int m_nSaveInstanceStateVersion = 0;
		int nVersion = savedInstanceState.getInt("m_nSaveInstanceStateVersion");
		if (nVersion > myApp.m_nSaveInstanceStateVersion)
		{
			myApp.m_nSaveInstanceStateVersion = nVersion;
		}
		else
		{
			return;	// 不需要恢复（当前数据更新，或与要恢复的数据相同）
		}
		
		String strWorkKey = savedInstanceState.getString("WORK_KEY");
		if(!strWorkKey.isEmpty())
		{
			myApp.setWorkKey(strWorkKey);
		}

		myApp.m_strMobile = savedInstanceState.getString("USER_MOBILE");
		myApp.m_strUserId = savedInstanceState.getString("USER_ID");
		myApp.m_strUserToken = savedInstanceState.getString("USER_TOKEN");
	}
}
