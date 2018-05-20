package com.apicloud.moduleDemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apicloud.sdk.moduledemo.R;

/**
 * RecyclerView的HeaderView，简单的展示一个TextView
 */
public class EntrepreneurshipHeader extends RelativeLayout {

    public EntrepreneurshipHeader(Context context) {
        super(context);
        init(context);
    }

    public EntrepreneurshipHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public EntrepreneurshipHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {

        View convertView = inflate(context, R.layout.common_entrepreneurship, this);
//        TextView textView = convertView.findViewById(R.id.common_entrepreneurship);
//        textView.setText("111111");
    }
}