package com.apicloud.moduleDemo;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.apicloud.moduleDemo.adapter.PictureSelectionAdapter;
import com.apicloud.moduleDemo.backhandler.OnTaskSuccessComplete;
import com.apicloud.moduleDemo.base.BaseAppCompatActivity;
import com.apicloud.moduleDemo.bean.base.DateBean;
import com.apicloud.moduleDemo.util.Utils;
import com.apicloud.moduleDemo.util.pickers.AddressPickTask;
import com.apicloud.moduleDemo.util.pickers.PopUitls;
import com.apicloud.sdk.moduledemo.R;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import cn.addapp.pickers.entity.City;
import cn.addapp.pickers.entity.County;
import cn.addapp.pickers.entity.Province;
import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.bean.MediaBean;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultDisposable;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageMultipleResultEvent;

public class ReleaseSmokingActivity extends BaseAppCompatActivity
{

    private PictureSelectionAdapter m_pictureSelectionAdapter;

    private List<MediaBean> m_arrDatas;
    private List<MediaBean> list = null;

    private LinearLayout m_llCity;
    private GridView m_gridView;
    private Button m_btnCommit;
    private RadioGroup m_rgCity;
    private RadioButton m_rbCityAll;
    private RadioButton m_rbCityIndex;
    private TextView m_tvStartData;
    private TextView m_tvEndData;
    private TextView m_tvJoinCount;
    private TextView m_tvCity;
    private TextView m_tvCityLocation;
    private TextView m_tvClosingDate;

    private String m_strArrNoSelect[]  = null;

    @Override
    protected int setLayoutResourceId()
    {
        return R.layout.activity_release_smoking;
    }

//    @Override
//    protected void setUpView() {
//        Utils.initCommonTitle(this,"发布活动",true);
//
//        m_strArrNoSelect  = getResources().getStringArray(R.array.house_no_text);
//
//        m_llCity = (LinearLayout)findViewById(R.id.ll_city);
//        m_gridView = (GridView) findViewById(R.id.gridview_functions);
//        m_btnCommit = (Button) findViewById(R.id.btn_commit);
//        m_rgCity = (RadioGroup) this.findViewById(R.id.rg_city);
//        m_rbCityAll = (RadioButton) this.findViewById(R.id.rb_city_all);
//        m_rbCityIndex = (RadioButton) this.findViewById(R.id.rb_city_index);
//        m_tvStartData = (TextView)findViewById(R.id.tv_start_data);
//        m_tvEndData = (TextView)findViewById(R.id.tv_end_data);
//        m_tvJoinCount = (TextView)findViewById(R.id.tv_join_count);
//        m_tvCity = (TextView)findViewById(R.id.tv_city);
//        m_tvCityLocation = (TextView)findViewById(R.id.tv_city_location);
//        m_tvClosingDate = (TextView)findViewById(R.id.tv_closing_date);
//
//        m_arrDatas = new ArrayList<>();
//        m_arrDatas.add(null);
//
//        DisplayMetrics dm = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
//        int nWidth = (dm.widthPixels - Utils.dp2px(this,40))/3;
//
//        m_pictureSelectionAdapter = new PictureSelectionAdapter(this, m_arrDatas, nWidth);
//        m_gridView.setAdapter(m_pictureSelectionAdapter);
//        m_gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
//
//        m_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                if(list == null || list.isEmpty() || list.size() == 9){
//                    openRadios();
//                }else if(position == 0 && m_arrDatas.get(position) == null){
//                    openRadios();
//                }
//            }
//        });
//
//        onCLickView();
//    }

    private void onCLickView()
    {
        //参与截止时间
        m_tvClosingDate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                PopUitls.showDataSelect(ReleaseSmokingActivity.this, new OnTaskSuccessComplete() {

                    @Override
                    public void onSuccess(Object obj)
                    {
                        DateBean dataBean = (DateBean) obj;
                        m_tvClosingDate.setText(MessageFormat.format("{0}-{1}-{2}", dataBean.getYear(), dataBean.getMonth(), dataBean.getDay()));
                    }
                });
            }
        });
        //开始日期选择
        m_tvStartData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopUitls.showDataSelect(ReleaseSmokingActivity.this, new OnTaskSuccessComplete()
                {
                    @Override
                    public void onSuccess(Object obj)
                    {
                        DateBean dataBean = (DateBean) obj;
                        m_tvStartData.setText(MessageFormat.format("{0}-{1}-{2}", dataBean.getYear(), dataBean.getMonth(), dataBean.getDay()));
                    }
                });
            }
        });
        //结束日期选择
        m_tvEndData.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                PopUitls.showDataSelect(ReleaseSmokingActivity.this, new OnTaskSuccessComplete()
                {
                    @Override
                    public void onSuccess(Object obj)
                    {
                        DateBean dataBean = (DateBean) obj;
                        m_tvEndData.setText(MessageFormat.format("{0}-{1}-{2}", dataBean.getYear(), dataBean.getMonth(), dataBean.getDay()));
                    }
                });
            }
        });

        //参与商家数
        m_tvJoinCount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                PopUitls.showPopSelect(ReleaseSmokingActivity.this, m_strArrNoSelect, "参与商家数量", new OnTaskSuccessComplete()
                {
                    @Override
                    public void onSuccess(Object obj)
                    {
                        m_tvJoinCount.setText(obj.toString());
                    }
                });
            }
        });
        m_tvCity.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AddressPickTask task = new AddressPickTask(ReleaseSmokingActivity.this);
                task.setHideProvince(false);
                task.setHideCounty(false);
                task.setCallback(new AddressPickTask.Callback()
                {
                    @Override
                    public void onAddressInitFailed()
                    {
                        showToast("数据初始化失败");
                    }

                    @Override
                    public void onAddressPicked(Province province, City city, County county)
                    {
                        if (county == null)
                        {
                            m_tvCity.setText(MessageFormat.format("{0}{1}", province.getAreaName(), city.getAreaName()));
                        }
                        else
                        {
                            m_tvCity.setText(MessageFormat.format("{0}{1}{2}", province.getAreaName(), city.getAreaName(), county.getAreaName()));
                        }
                    }
                });
                task.execute("上海", "上海", "浦东");
            }
        });
        //提交
        m_btnCommit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });
        //全国or选择城市
        m_rgCity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {

            @Override
            public void onCheckedChanged(RadioGroup rg, int checkedId)
            {
                // TODO Auto-generated method stub
                if(checkedId == m_rbCityAll.getId())
                {
                    m_llCity.setVisibility(View.GONE);
                    m_tvCity.setText("");
                }
                else if(checkedId == m_rbCityIndex.getId())
                {
                    m_llCity.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void showToast(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 多选
     */
    private void openRadios()
    {
        RxGalleryFinal rxGalleryFinal = RxGalleryFinal
                .with(ReleaseSmokingActivity.this)
                .image()
                .multiple();
        if (list != null && !list.isEmpty())
        {
            rxGalleryFinal.selected(list);
        }
        rxGalleryFinal.maxSize(9)
                .imageLoader(ImageLoaderType.FRESCO)
                .subscribe(new RxBusResultDisposable<ImageMultipleResultEvent>()
                {

                    @Override
                    protected void onEvent(ImageMultipleResultEvent imageMultipleResultEvent) throws Exception
                    {
                        list = imageMultipleResultEvent.getResult();
                        m_arrDatas.clear();
                        if(list.size() < 9)
                        {
                            m_arrDatas.add(null);
                        }
                        m_arrDatas.addAll(list);

                        m_pictureSelectionAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onComplete()
                    {
                        super.onComplete();
                        Toast.makeText(getBaseContext(), "OVER", Toast.LENGTH_SHORT).show();
                    }
                })
                .openGallery();
    }

    // 检查输入项是否输入正确
    private boolean isInputValid()
    {
//        m_strText = m_etText.getText().toString().trim();
//        if (m_strText.isEmpty()) {
//            Utils.showToast(ReleaseRenovationActivity.this, "请输入意见建议");
//            m_etText.requestFocus();
//            return false;
//        }
//
//        m_strTitle = m_etTitle.getText().toString().trim();
//        if (m_strTitle.isEmpty()) {
//            Utils.showToast(ReleaseRenovationActivity.this, "请输入标题");
//            m_etTitle.requestFocus();
//            return false;
//        }
//
//        m_strName = m_etName.getText().toString().trim();
//        if (m_strName.isEmpty()) {
//            Utils.showToast(ReleaseRenovationActivity.this, "请输入联系人");
//            m_etName.requestFocus();
//            return false;
//        }
//
//        m_strPhone = m_etPhone.getText().toString().trim();
//        if(m_strPhone.isEmpty())
//        {
//            Utils.showToast(this, "请输入手机号码");
//            m_etPhone.requestFocus();
//            return false;
//        }
//        else if(m_strPhone.length() < 11)
//        {
//            Utils.showToast(this, "联系电话需要11位长度");
//            m_etPhone.requestFocus();
//            return false;
//        }
//        else if(!RegexUtil.checkMobile(m_strPhone))
//        {
//            Utils.showToast(this, "请输入正确的联系电话");
//            m_etPhone.requestFocus();
//            return false;
//        }

        return true;
    }
}
