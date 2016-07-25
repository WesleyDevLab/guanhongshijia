package com.wckj.gfsj.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.wckj.gfsj.Adapter.CommonAdapter;
import com.wckj.gfsj.Adapter.ViewHolder;
import com.wckj.gfsj.Bean.Commodity_level_three;
import com.wckj.gfsj.Bean.TimeEvent;
import com.wckj.gfsj.CustomUi.FrameLoadLayout;
import com.wckj.gfsj.R;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 商品三级列表
 */
public class CommodityLevelThreeActivity extends BaseNewActivity implements View.OnClickListener {
    private TextView tv_time;
    private View view;
    private ArrayList<Commodity_level_three> mList;
    private GridView gv_commodity_three;
    private CommonAdapter mlvAdapter;
    private TextView tv_brand_1,tv_brand_2,tv_brand_3;
    private ImageView iv_more_left;

    @Override
    protected void init() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected View onCreateTitleView(LayoutInflater inflater) {
        View titleView = inflater.inflate(R.layout.layout_title_main_go_back, null);
        titleView. findViewById(R.id.rl_brand).setVisibility(View.VISIBLE);
        titleView. findViewById(R.id.tv_go_back).setOnClickListener(this);
        tv_time = (TextView) titleView.findViewById(R.id.tv_time);
        titleView.findViewById(R.id.iv_more_right).setOnClickListener(this);
        iv_more_left = (ImageView) titleView.findViewById(R.id.iv_more_left);
        iv_more_left.setOnClickListener(this);
        tv_brand_3 = (TextView) titleView.findViewById(R.id.tv_brand_3);
        tv_brand_2 = (TextView) titleView.findViewById(R.id.tv_brand_2);
        tv_brand_1 = (TextView) titleView.findViewById(R.id.tv_brand_1);

        return titleView;
    }

    @Override
    protected View onCreateSuccessView() {
        view = inflater.inflate(R.layout.activity_commodity_three, null);
        gv_commodity_three = (GridView) view.findViewById(R.id.gv_commodity_three);
        bindData();
        return view;
    }

    private void bindData() {
        if(mlvAdapter==null){
            mlvAdapter=  new CommonAdapter<Commodity_level_three>(this,mList,R.layout.item_gv_commodity_three) {
                @Override
                public void convert(ViewHolder helper, Commodity_level_three item, int position) {
                    helper.setText(R.id.tv_title_desc,"凳子");


                }
            };
            gv_commodity_three.setAdapter(mlvAdapter);
        }else {
            mlvAdapter.notifyDataSetChanged();
        }

    }


    @Override
    protected void refreshOrLoadView() {

    }
    protected void load() {
        mList = new ArrayList<>();
        for (int i = 0; i <8 ; i++) {
            mList.add(new Commodity_level_three());
        }
        tv_brand_1.setText("啊啊啊");
        tv_brand_1.setText("帮不帮");
        tv_brand_1.setText("错错错");
        showPageState(FrameLoadLayout.LoadResult.success);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_go_back:
                finish();
                break;
            case R.id.iv_more_left://下一页

                break;
            case R.id.iv_more_right://上一页

                break;
            case R.id.tv_brand_3:
                setColorBackground(R.id.tv_brand_3);
                break;
            case R.id.tv_brand_2:
                setColorBackground(R.id.tv_brand_2);
                break;
            case R.id.tv_brand_1:
                setColorBackground(R.id.tv_brand_1);
                break;
        }
    }

    /**
     * 设置分类的颜色
     */
    private  void setColorBackground(int id){
        tv_brand_3.setBackgroundResource(R.id.tv_main==id?R.drawable.icon_main_bg:0);
        tv_brand_2.setBackgroundResource(R.id.tv_main_classification==id?R.drawable.icon_main_bg:0);
        tv_brand_1.setBackgroundResource(R.id.tv_main_recommend==id?R.drawable.icon_main_bg:0);
        tv_brand_3.setTextColor(getResources().getColor(R.id.tv_main==id?R.color.color_fffffe:R.color.color_767f8e));
        tv_brand_2.setTextColor(getResources().getColor(R.id.tv_main_classification==id?R.color.color_fffffe:R.color.color_767f8e));
        tv_brand_1.setTextColor(getResources().getColor(R.id.tv_main_recommend==id?R.color.color_fffffe:R.color.color_767f8e));
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void  onMainTimeEvent(TimeEvent time){
        if(tv_time!=null){
            tv_time.setText(time.getTime());
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}