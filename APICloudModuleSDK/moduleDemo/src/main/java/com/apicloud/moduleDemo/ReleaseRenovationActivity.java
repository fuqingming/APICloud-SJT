package com.apicloud.moduleDemo;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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
import com.apicloud.moduleDemo.util.TimeUtils;
import com.apicloud.moduleDemo.util.Utils;
import com.apicloud.moduleDemo.util.pickers.AddressPickTask;
import com.apicloud.moduleDemo.util.pickers.PopUitls;
import com.apicloud.sdk.moduledemo.R;
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
    private TextView m_tvStartDate;
    private TextView m_tvEndDate;
    private TextView m_tvJoinCount;
    private TextView m_tvCity;
    private TextView m_tvHouseType;
    private TextView m_tvStyle;
    private TextView m_tvCityLocation;

    private EditText m_etAmount;
    private EditText m_etTitle;
    private EditText m_etBudget;

    private String m_strTitle;
    private String m_lonStartDate = "";
    private String m_lonEndDate = "";
    private String m_strAddress;
    private String m_strBudget;
    private String m_strAmount;

    private String m_strArrStyle[]  =  null;
    private String m_strArrHouseType[]  = null;
    private String m_strArrNoSelect[]  = null;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_release_renovation;
    }

    @Override
    protected void setUpView() {
        Utils.initCommonTitle(this,"发布活动",true);

        m_strArrStyle  =  getResources().getStringArray(R.array.house_style_text);
        m_strArrHouseType  = getResources().getStringArray(R.array.house_type_text);
        m_strArrNoSelect  = getResources().getStringArray(R.array.house_no_text);

        m_llCity = (LinearLayout)findViewById(R.id.ll_city);
        m_gridView = (GridView) findViewById(R.id.gridview_functions);
        m_btnCommit = (Button) findViewById(R.id.btn_commit);
        m_rgCity = (RadioGroup) this.findViewById(R.id.rg_city);
        m_rbCityAll = (RadioButton) this.findViewById(R.id.rb_city_all);
        m_rbCityIndex = (RadioButton) this.findViewById(R.id.rb_city_index);
        m_tvStartDate = (TextView)findViewById(R.id.tv_start_date);
        m_tvEndDate = (TextView)findViewById(R.id.tv_end_date);
        m_tvJoinCount = (TextView)findViewById(R.id.tv_join_count);
        m_tvCity = (TextView)findViewById(R.id.tv_city);
        m_tvHouseType = (TextView)findViewById(R.id.tv_house_type);
        m_tvStyle = (TextView)findViewById(R.id.tv_style);
        m_tvCityLocation = (TextView)findViewById(R.id.tv_city_location);
        m_etTitle = (EditText)findViewById(R.id.et_title);
        m_etBudget = (EditText)findViewById(R.id.et_budget);
        m_etAmount = (EditText)findViewById(R.id.et_amount);

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
        m_tvStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopUitls.showDataSelect(ReleaseRenovationActivity.this, new OnTaskSuccessComplete() {
                    @Override
                    public void onSuccess(Object obj) {
                        DateBean dataBean = (DateBean) obj;
                        m_lonStartDate = dataBean.getYear() + "-" + dataBean.getMonth() + "-" + dataBean.getDay();
                        m_tvStartDate.setText(m_lonStartDate);
                    }
                });
            }
        });
        //结束日期选择
        m_tvEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopUitls.showDataSelect(ReleaseRenovationActivity.this, new OnTaskSuccessComplete() {
                    @Override
                    public void onSuccess(Object obj) {
                        DateBean dataBean = (DateBean) obj;
                        m_lonEndDate = dataBean.getYear() + "-" + dataBean.getMonth() + "-" + dataBean.getDay();
                        m_tvEndDate.setText(m_lonEndDate);
                    }
                });
            }
        });
        //装修风格
        m_tvStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopUitls.showPopSelect(ReleaseRenovationActivity.this, m_strArrStyle, "装修风格", new OnTaskSuccessComplete() {
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
                PopUitls.showPopSelect(ReleaseRenovationActivity.this, m_strArrHouseType, "户型结构", new OnTaskSuccessComplete() {
                    @Override
                    public void onSuccess(Object obj) {
                        m_tvHouseType.setText(obj.toString());
                    }
                });
            }
        });
        //参与商家数
        m_tvJoinCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopUitls.showPopSelect(ReleaseRenovationActivity.this, m_strArrNoSelect, "参与商家数量", new OnTaskSuccessComplete() {
                    @Override
                    public void onSuccess(Object obj) {
                        m_tvJoinCount.setText(obj.toString());
                    }
                });
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
                if(isInputValid()){

                }
            }
        });
        //全国or选择城市
        m_rgCity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup rg, int checkedId) {
                // TODO Auto-generated method stub
                if(checkedId == m_rbCityAll.getId()){
                    m_llCity.setVisibility(View.GONE);
                    m_strAddress = "";
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
        m_strTitle = m_etTitle.getText().toString().trim();
        if (m_strTitle.isEmpty()) {
            Utils.showToast(ReleaseRenovationActivity.this, "请输入活动标题");
            m_etTitle.requestFocus();
            return false;
        }

        if(m_lonStartDate.isEmpty()){
            Utils.showToast(ReleaseRenovationActivity.this, "请选择活动开始时间");
            return false;
        }

        if(m_lonEndDate.isEmpty()){
            Utils.showToast(ReleaseRenovationActivity.this, "请选择活动结束时间");
            return false;
        }

        if(!TimeUtils.time2Time(m_lonStartDate,m_lonEndDate,TimeUtils.TIME_FORMAT)){
            Utils.showToast(ReleaseRenovationActivity.this, "结束时间不能早于开始时间");
            return false;
        }
//
        if(!m_rbCityAll.isChecked() && !m_rbCityIndex.isChecked()){
            Utils.showToast(ReleaseRenovationActivity.this, "请选择活动范围");
            return false;
        }

        if(m_rbCityIndex.isChecked()){
            m_strAddress = m_tvCity.getText().toString().trim();
            if(m_strAddress.isEmpty()){
                Utils.showToast(ReleaseRenovationActivity.this, "请选择指定城市");
                return false;
            }
        }

        m_strBudget = m_etBudget.getText().toString().trim();
        if (m_strBudget.isEmpty()) {
            Utils.showToast(ReleaseRenovationActivity.this, "请输入装修预算");
            m_etBudget.requestFocus();
            return false;
        }

        m_strAmount = m_etAmount.getText().toString().trim();
        if (m_strAmount.isEmpty()) {
            Utils.showToast(ReleaseRenovationActivity.this, "请输入量房金");
            m_etAmount.requestFocus();
            return false;
        }

        return true;
    }
}
