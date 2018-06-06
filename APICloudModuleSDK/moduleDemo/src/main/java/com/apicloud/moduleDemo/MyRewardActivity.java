package com.apicloud.moduleDemo;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.apicloud.moduleDemo.base.BaseAppCompatActivity;
import com.apicloud.moduleDemo.util.Utils;
import com.apicloud.moduleDemo.view.NoScrollViewPager;
import com.apicloud.moduleDemo.view.ViewPagerIndicator;
import com.apicloud.sdk.moduledemo.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * */
public class MyRewardActivity extends BaseAppCompatActivity
{

	// ViewPager
	private NoScrollViewPager viewPager;
	private FragmentPagerAdapter pagerAdapter;

	// ViewPagerIndicator
	private ViewPagerIndicator viewPagerIndicator;
	private List<String> titles = Arrays.asList("我发布的悬赏", "我接受的悬赏");

	// Fragment
	private List<Fragment> fragments = new ArrayList<>();

	@Override
	protected int setLayoutResourceId()
	{
		return R.layout.activity_my_reward;
	}

	@Override
	protected void initView()
	{
		Utils.initCommonTitle(this,"我的悬赏",true);

		viewPager = (NoScrollViewPager)findViewById(R.id.view_pager);
		viewPagerIndicator = (ViewPagerIndicator)findViewById(R.id.indicator);

		viewPagerIndicator.setTabItemTitles(titles);
		viewPagerIndicator.setVisibleTabCount(2);

		fragments.add(new FragmentMyReleaseReward());
		fragments.add(new FragmentMyAcceptReward());

		// PagerAdapter
		pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
		{
			@Override
			public int getCount()
			{
				Log.d("",""+fragments.size());
				return fragments.size();
			}

			@Override
			public Fragment getItem(int position)
			{
				Log.d("",""+fragments.size());
				return fragments.get(position);
			}
		};

		// 设置数据适配器
		viewPager.setAdapter(pagerAdapter);
		viewPagerIndicator.setViewPager(viewPager, 0);
	}
}