package com.apicloud.moduleDemo.settings;

import android.content.Context;
import android.os.Bundle;

import com.apicloud.moduleDemo.base.MyApplication;
import com.apicloud.moduleDemo.http.HttpClient;
import com.apicloud.moduleDemo.util.Utils;

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

		outState.putString("USER_UUID", myApp.m_strUuid);
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

		myApp.m_strUuid = savedInstanceState.getString("USER_UUID");
		myApp.m_strUserToken = savedInstanceState.getString("USER_TOKEN");
		HttpClient.init(context.getApplicationContext(),true);
	}
}
