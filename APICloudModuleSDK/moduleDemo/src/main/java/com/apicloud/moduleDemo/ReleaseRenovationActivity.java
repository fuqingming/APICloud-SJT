package com.apicloud.moduleDemo;

import android.app.Activity;
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
import com.apicloud.moduleDemo.bean.base.DataBean;
import com.apicloud.moduleDemo.util.Utils;
import com.apicloud.moduleDemo.util.pickers.AddressPickTask;
import com.apicloud.moduleDemo.util.pickers.PopUitls;
import com.apicloud.sdk.moduledemo.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cn.addapp.pickers.common.LineConfig;
import cn.addapp.pickers.entity.City;
import cn.addapp.pickers.entity.County;
import cn.addapp.pickers.entity.Province;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.picker.DatePicker;
import cn.addapp.pickers.picker.SinglePicker;
import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.bean.MediaBean;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultDisposable;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageMultipleResultEvent;

public class ReleaseRenovationActivity extends BaseAppCompatActivity {

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
    private TextView m_tvHouseType;
    private TextView m_tvStyle;

    private String[] m_strArrStyle = new String[]{"现代简约", "美式", "地中海", "田园", "新古典", "中式", "混搭风", "其他" };

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_release_renovation;
    }

    @Override
    protected void setUpView() {
        Utils.initCommonTitle(this,"发布活动",true);

        m_llCity = (LinearLayout)findViewById(R.id.ll_city);
        m_gridView = (GridView) findViewById(R.id.gridview_functions);
        m_btnCommit = (Button) findViewById(R.id.btn_commit);
        m_rgCity = (RadioGroup) this.findViewById(R.id.rg_city);
        m_rbCityAll = (RadioButton) this.findViewById(R.id.rb_city_all);
        m_rbCityIndex = (RadioButton) this.findViewById(R.id.rb_city_index);
        m_tvStartData = (TextView)findViewById(R.id.tv_start_data);
        m_tvEndData = (TextView)findViewById(R.id.tv_end_data);
        m_tvJoinCount = (TextView)findViewById(R.id.tv_join_count);
        m_tvCity = (TextView)findViewById(R.id.tv_city);
        m_tvHouseType = (TextView)findViewById(R.id.tv_house_type);
        m_tvStyle = (TextView)findViewById(R.id.tv_style);

        m_arrDatas = new ArrayList<>();
        m_arrDatas.add(null);

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

        onCLickView();
    }

    private void onCLickView() {
        //开始日期选择
        m_tvStartData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopUitls.showDataSelect(ReleaseRenovationActivity.this, new OnTaskSuccessComplete() {
                    @Override
                    public void onSuccess(Object obj) {
                        DataBean dataBean = (DataBean) obj;
                        m_tvStartData.setText(dataBean.getYear() + "-" + dataBean.getMonth() + "-" + dataBean.getDay());
                    }
                });
            }
        });
        //结束日期选择
        m_tvEndData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopUitls.showDataSelect(ReleaseRenovationActivity.this, new OnTaskSuccessComplete() {
                    @Override
                    public void onSuccess(Object obj) {
                        DataBean dataBean = (DataBean) obj;
                        m_tvEndData.setText(dataBean.getYear() + "-" + dataBean.getMonth() + "-" + dataBean.getDay());
                    }
                });
            }
        });
        //装修风格
        m_tvStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopUitls.showStyleSelect(ReleaseRenovationActivity.this, m_strArrStyle, "装修风格", new OnTaskSuccessComplete() {
                    @Override
                    public void onSuccess(Object obj) {
                        m_tvStyle.setText(obj.toString());
                    }
                });
            }
        });
        //户型
        m_tvHouseType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SinglePicker<String> picker = new SinglePicker<>(ReleaseRenovationActivity.this,  new String[]{"一房", "二房", "三房", "复式", "平层公寓", "别墅", "其他" });
                picker.setCanLoop(false);//不禁用循环
                picker.setTopBackgroundColor(0xFFEEEEEE);
                picker.setTopHeight(50);
                picker.setTopLineColor(0xFF33B5E5);
                picker.setTopLineHeight(1);
                picker.setTitleText("户型结构");
                picker.setTitleTextColor(0xFF999999);
                picker.setTitleTextSize(12);
                picker.setCancelTextColor(0xFF33B5E5);
                picker.setCancelTextSize(13);
                picker.setSubmitTextColor(0xFF33B5E5);
                picker.setSubmitTextSize(13);
                picker.setSelectedTextColor(0xFFEE0000);
                picker.setUnSelectedTextColor(0xFF999999);
                picker.setWheelModeEnable(false);
                LineConfig config = new LineConfig();
                config.setColor(Color.BLUE);//线颜色
                config.setAlpha(120);//线透明度
//        config.setRatio(1);//线比率
                picker.setLineConfig(config);
                picker.setItemWidth(200);
                picker.setBackgroundColor(0xFFE1E1E1);
//                picker.setSelectedItem( "不限");
                picker.setSelectedIndex(0);
                picker.setOnItemPickListener(new OnItemPickListener<String>() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        m_tvHouseType.setText(item);
                    }
                });
                picker.show();
            }
        });
        //参与商家数
        m_tvJoinCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SinglePicker<String> picker = new SinglePicker<>(ReleaseRenovationActivity.this,
                        new String[]{
                                "不限", "1家", "2家", "3家", "4家", "5家",
                                "6家", "7家", "8家", "9家", "10家"
                        });
                picker.setCanLoop(false);//不禁用循环
                picker.setTopBackgroundColor(0xFFEEEEEE);
                picker.setTopHeight(50);
                picker.setTopLineColor(0xFF33B5E5);
                picker.setTopLineHeight(1);
                picker.setTitleText("参与商家数量");
                picker.setTitleTextColor(0xFF999999);
                picker.setTitleTextSize(12);
                picker.setCancelTextColor(0xFF33B5E5);
                picker.setCancelTextSize(13);
                picker.setSubmitTextColor(0xFF33B5E5);
                picker.setSubmitTextSize(13);
                picker.setSelectedTextColor(0xFFEE0000);
                picker.setUnSelectedTextColor(0xFF999999);
                picker.setWheelModeEnable(false);
                LineConfig config = new LineConfig();
                config.setColor(Color.BLUE);//线颜色
                config.setAlpha(120);//线透明度
//        config.setRatio(1);//线比率
                picker.setLineConfig(config);
                picker.setItemWidth(200);
                picker.setBackgroundColor(0xFFE1E1E1);
//                picker.setSelectedItem( "不限");
                picker.setSelectedIndex(0);
                picker.setOnItemPickListener(new OnItemPickListener<String>() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        m_tvJoinCount.setText(item);
                    }
                });
                picker.show();
            }
        });
        m_tvCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddressPickTask task = new AddressPickTask(ReleaseRenovationActivity.this);
                task.setHideProvince(false);
                task.setHideCounty(false);
                task.setCallback(new AddressPickTask.Callback() {
                    @Override
                    public void onAddressInitFailed() {
                        showToast("数据初始化失败");
                    }

                    @Override
                    public void onAddressPicked(Province province, City city, County county) {
                        if (county == null) {
                            m_tvCity.setText(province.getAreaName() + city.getAreaName());
                        } else {
                            m_tvCity.setText(province.getAreaName() + city.getAreaName() + county.getAreaName());
                        }
                    }
                });
                task.execute("上海", "上海", "浦东");
            }
        });
        //提交
        m_btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //全国or选择城市
        m_rgCity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup rg, int checkedId) {
                // TODO Auto-generated method stub
                if(checkedId == m_rbCityAll.getId()){
                    m_llCity.setVisibility(View.GONE);
                    m_tvCity.setText("");
                }else if(checkedId == m_rbCityIndex.getId()){
                    m_llCity.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 多选
     */
    private void openRadios() {
        RxGalleryFinal rxGalleryFinal = RxGalleryFinal
                .with(ReleaseRenovationActivity.this)
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
