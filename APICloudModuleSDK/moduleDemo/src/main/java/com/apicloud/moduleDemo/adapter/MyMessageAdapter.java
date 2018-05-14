package com.apicloud.moduleDemo.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.apicloud.moduleDemo.bean.base.MyMessageBean;
import com.apicloud.moduleDemo.util.recycler.BaseRecyclerAdapter;
import com.apicloud.moduleDemo.util.recycler.BaseRecyclerViewHolder;
import com.apicloud.sdk.moduledemo.R;

public class MyMessageAdapter extends BaseRecyclerAdapter<MyMessageBean> {

    public MyMessageAdapter() {
    }

    @Override
    protected int getContentView(int viewType) {
        return R.layout.item_my_message;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder, final MyMessageBean data, final int position) {
        TextView tvTime = holder.getView().findViewById(R.id.tv_time);
        TextView tvTitle = holder.getView().findViewById(R.id.tv_title);
        TextView tvText = holder.getView().findViewById(R.id.tv_text);
        CheckBox cbDelete = holder.getView().findViewById(R.id.cb_delete);
        tvTime.setText(data.getTime());
        tvTitle.setText(data.getTitle());
        tvText.setText(data.getText());

        if(data.isVisible()){
            cbDelete.setVisibility(View.VISIBLE);
        }else{
            cbDelete.setVisibility(View.GONE);
        }

        cbDelete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                data.setChecked(isChecked);
            }
        });
    }

}
