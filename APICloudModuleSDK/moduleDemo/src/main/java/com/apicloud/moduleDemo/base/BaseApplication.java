package com.apicloud.moduleDemo.base;

import com.apicloud.moduleDemo.http.HttpSetUrl;
import com.blankj.utilcode.util.Utils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import cn.finalteam.rxgalleryfinal.utils.ModelUtils;

public class BaseApplication extends MyApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);//shardPrefrences
        HttpSetUrl.setAppUrl("http://test.sgpt.dems.cc/");

        //图片选择
        ModelUtils.setDebugModel(true);
        Fresco.initialize(this);
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(this);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().init(config.build());
    }
}