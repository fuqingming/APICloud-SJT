package com.apicloud.moduleDemo;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.apicloud.moduleDemo.adapter.PictureSelectionAdapter;
import com.apicloud.moduleDemo.base.BaseAppCompatActivity;
import com.apicloud.moduleDemo.settings.AppSettings;
import com.apicloud.moduleDemo.util.RegexUtil;
import com.apicloud.moduleDemo.util.Utils;
import com.apicloud.sdk.moduledemo.R;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.bean.MediaBean;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultDisposable;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageMultipleResultEvent;

public class InformationActivity extends BaseAppCompatActivity {

    private PictureSelectionAdapter m_pictureSelectionAdapter;

    private List<MediaBean> m_arrDatas;
    private List<MediaBean> list = null;

    private GridView m_gridView;
    private EditText m_etName;
    private EditText m_etPhone;
    private EditText m_etTitle;
    private EditText m_etText;
    private Button m_btnCommit;

    private String m_strTitle;
    private String m_strText;
    private String m_strName;
    private String m_strPhone;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_informatioin;
    }

    @Override
    protected void setUpView() {
        Utils.initCommonTitle(this,"咨询与建议",true);

        m_gridView = (GridView) findViewById(R.id.gridview_functions);
        m_etName = (EditText) findViewById(R.id.et_name);
        m_etPhone = (EditText) findViewById(R.id.et_phone);
        m_etTitle = (EditText) findViewById(R.id.et_title);
        m_etText = (EditText) findViewById(R.id.et_text);
        m_btnCommit = (Button) findViewById(R.id.btn_commit);

        m_arrDatas = new ArrayList<>();
        m_arrDatas.add(null);

        m_etName.setText(AppSettings.getNickname());
        m_etPhone.setText(AppSettings.getPhone());

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int nWidth = (dm.widthPixels - Utils.dp2px(this,40))/3;

        m_pictureSelectionAdapter = new PictureSelectionAdapter(this, m_arrDatas, nWidth);
        m_gridView.setAdapter(m_pictureSelectionAdapter);
        m_gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));

        m_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if(list == null || list.isEmpty() || list.size() == 9){
                    openRadios();
                }else if(position == 0 && m_arrDatas.get(position) == null){
                    openRadios();
                }
            }
        });

        m_btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    /**
     * 多选
     */
    private void openRadios() {
        RxGalleryFinal rxGalleryFinal = RxGalleryFinal
                .with(InformationActivity.this)
                .image()
                .multiple();
        if (list != null && !list.isEmpty()) {
            rxGalleryFinal.selected(list);
        }
        rxGalleryFinal.maxSize(9)
                .imageLoader(ImageLoaderType.FRESCO)
                .subscribe(new RxBusResultDisposable<ImageMultipleResultEvent>() {

                    @Override
                    protected void onEvent(ImageMultipleResultEvent imageMultipleResultEvent) throws Exception {
                        list = imageMultipleResultEvent.getResult();
                        m_arrDatas.clear();
                        if(list.size() < 9){
                            m_arrDatas.add(null);
                        }
                        m_arrDatas.addAll(list);

                        m_pictureSelectionAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        Toast.makeText(getBaseContext(), "OVER", Toast.LENGTH_SHORT).show();
                    }
                })
                .openGallery();
    }

    // 检查输入项是否输入正确
    private boolean isInputValid() {
        m_strText = m_etText.getText().toString().trim();
        if (m_strText.isEmpty()) {
            Utils.showToast(InformationActivity.this, "请输入意见建议");
            m_etText.requestFocus();
            return false;
        }

        m_strTitle = m_etTitle.getText().toString().trim();
        if (m_strTitle.isEmpty()) {
            Utils.showToast(InformationActivity.this, "请输入标题");
            m_etTitle.requestFocus();
            return false;
        }

        m_strName = m_etName.getText().toString().trim();
        if (m_strName.isEmpty()) {
            Utils.showToast(InformationActivity.this, "请输入联系人");
            m_etName.requestFocus();
            return false;
        }

        m_strPhone = m_etPhone.getText().toString().trim();
        if(m_strPhone.isEmpty())
        {
            Utils.showToast(this, "请输入手机号码");
            m_etPhone.requestFocus();
            return false;
        }
        else if(m_strPhone.length() < 11)
        {
            Utils.showToast(this, "联系电话需要11位长度");
            m_etPhone.requestFocus();
            return false;
        }
        else if(!RegexUtil.checkMobile(m_strPhone))
        {
            Utils.showToast(this, "请输入正确的联系电话");
            m_etPhone.requestFocus();
            return false;
        }

        return true;
    }
}
