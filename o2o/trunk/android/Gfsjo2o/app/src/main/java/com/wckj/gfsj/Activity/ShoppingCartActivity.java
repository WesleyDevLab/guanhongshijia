package com.wckj.gfsj.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.wckj.gfsj.Adapter.CommonAdapter;
import com.wckj.gfsj.Adapter.ViewHolder;
import com.wckj.gfsj.Bean.Commodity_level_details;
import com.wckj.gfsj.CustomUi.FrameLoadLayout;
import com.wckj.gfsj.CustomUi.TitleRelativeLayout;
import com.wckj.gfsj.R;

import java.util.ArrayList;

/**
 * 购物车页面
 */
public class ShoppingCartActivity extends BaseNewActivity implements View.OnClickListener {
    private TextView tv_time,tv_content_desc;
    private View view;
    private ListView lv_shopping;
    private CommonAdapter mlvAdapter;
    private ArrayList<Commodity_level_details> mList;
    private TitleRelativeLayout title_rl;

    @Override
    protected void init() {
        loadPage.iv_networktext.setImageResource(R.drawable.icon_big_cart);
        loadPage.textView1.setText("你还没有相关订单");
        loadPage.textView1.setText("快去商品购物页选择其他商品吧！！！");
    }

    @Override
    protected View onCreateTitleView(LayoutInflater inflater) {
        View titleView = inflater.inflate(R.layout.layout_public_title_main, null);
        title_rl = (TitleRelativeLayout) titleView.findViewById(R.id.title_rl);
        title_rl.childView. findViewById(R.id.tv_go_back).setOnClickListener(this);
        title_rl.childView.findViewById(R.id.tv_content_desc).setVisibility(View.GONE);
        title_rl.childView.findViewById(R.id.tv_shopping_desc).setVisibility(View.GONE);
        return titleView;
    }

    @Override
    protected View onCreateSuccessView() {
        view = inflater.inflate(R.layout.activity_shopping_cart, null);
        lv_shopping = (ListView) view.findViewById(R.id.lv_shopping);
        binData();
        return view;
    }

    private void binData() {
        if(mlvAdapter==null){
            mlvAdapter=  new CommonAdapter<Commodity_level_details>(this,mList,R.layout.item_shopping_cart) {
                @Override
                public void convert(ViewHolder helper, Commodity_level_details item, int position) {


                }
            };
            lv_shopping.setAdapter(mlvAdapter);
        }else {
            mlvAdapter.notifyDataSetChanged();
        }
    }
    protected void load() {
        mList = new ArrayList<>();
        for (int i = 0; i <8 ; i++) {
            mList.add(new Commodity_level_details());
        }

        showPageState(FrameLoadLayout.LoadResult.success);
    }
    @Override
    protected void refreshOrLoadView() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_go_back:
                finish();
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        title_rl. clearRegister();
    }
}
