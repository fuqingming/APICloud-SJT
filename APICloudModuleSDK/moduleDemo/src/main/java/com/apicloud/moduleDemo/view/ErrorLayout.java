package com.apicloud.moduleDemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apicloud.moduleDemo.util.NetUtil;
import com.apicloud.sdk.moduledemo.R;

public class ErrorLayout extends LinearLayout implements
        View.OnClickListener {// , ISkinUIObserver {

    public static final int HIDE_LAYOUT = 4;
    public static final int NETWORK_ERROR = 1;
    public static final int NETWORK_LOADING = 2;
    public static final int NODATA = 3;
    public static final int NODATA_ENABLE_CLICK = 5;
    public static final int NO_LOGIN = 6;

    private ProgressBar animProgress;
    private boolean clickEnable = true;
    private final Context context;
    public ImageView img;
    private OnClickListener listener;
    private int mErrorState;
    private RelativeLayout mLayout;
    private String strNoDataContent = "";
    private TextView tv;

    public ErrorLayout(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public ErrorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        View view = View.inflate(context, R.layout.view_error_layout, null);
        img = (ImageView) view.findViewById(R.id.img_error_layout);
        tv = (TextView) view.findViewById(R.id.tv_error_layout);
        mLayout = (RelativeLayout) view.findViewById(R.id.pageerrLayout);
        animProgress = (ProgressBar) view.findViewById(R.id.animProgress);
        setBackgroundColor(-1);
        setOnClickListener(this);
        img.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clickEnable) {
                    // setErrorType(NETWORK_LOADING);
                    if (listener != null)
                        listener.onClick(v);
                }
            }
        });
        addView(view);
    }

    @Override
    public void onClick(View v) {
        if (clickEnable) {
            // setErrorType(NETWORK_LOADING);
            if (listener != null)
                listener.onClick(v);
        }
    }

    public void setErrorType(int state) {
        setVisibility(View.VISIBLE);
        switch (state) {
        case NETWORK_ERROR:
            mErrorState = NETWORK_ERROR;
            // img.setBackgroundDrawable(SkinsUtil.getDrawable(context,"ic_page_failed"));
            if (NetUtil.isNetConnected(getContext())) {
                tv.setText(R.string.error_view_load_error_click_to_refresh);
                img.setBackgroundResource(R.mipmap.ic_page_failed);
            } else {
                tv.setText(R.string.error_view_network_error_click_to_refresh);
                img.setBackgroundResource(R.mipmap.ic_page_icon_network);
            }
            img.setVisibility(View.VISIBLE);
            animProgress.setVisibility(View.GONE);
            clickEnable = true;
            break;
        case NETWORK_LOADING:
            mErrorState = NETWORK_LOADING;
            // animProgress.setBackgroundDrawable(SkinsUtil.getDrawable(context,"loadingpage_bg"));
            animProgress.setVisibility(View.VISIBLE);
            img.setVisibility(View.GONE);
            tv.setText(R.string.error_view_loading);
            clickEnable = false;
            break;
        case HIDE_LAYOUT:
            setVisibility(View.GONE);
            break;
            case NODATA:
                img.setVisibility(View.GONE);
                animProgress.setVisibility(View.GONE);
                clickEnable = false;
                tv.setText(R.string.error_view_no_data);
                break;
        default:
            break;
        }
    }

    public void setNoDataContent(String noDataContent) {
        strNoDataContent = noDataContent;
    }

    public void setOnLayoutClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void setVisibility(int visibility) {
        if (visibility == View.GONE)
            mErrorState = HIDE_LAYOUT;
        super.setVisibility(visibility);
    }
}
