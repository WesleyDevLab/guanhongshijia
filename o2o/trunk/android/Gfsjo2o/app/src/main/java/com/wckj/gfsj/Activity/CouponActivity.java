package com.wckj.gfsj.Activity;

import android.support.percent.PercentRelativeLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.wckj.gfsj.Adapter.CouponAdapter;
import com.wckj.gfsj.Bean.QueryCouponRequest;
import com.wckj.gfsj.Bean.QueryCouponResult;
import com.wckj.gfsj.Bean.entity.Coupon;
import com.wckj.gfsj.CustomUi.FrameLoadLayout;
import com.wckj.gfsj.CustomUi.TitleRelativeLayout;
import com.wckj.gfsj.GlobalUtils;
import com.wckj.gfsj.R;
import com.wckj.gfsj.Utils.HttpUtils;
import com.wckj.gfsj.Utils.IImpl.ICallBack;
import com.wckj.gfsj.Utils.LogUtil;
import com.wckj.gfsj.Utils.OwerToastShow;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by rayco on 2016/7/30.
 */
public class CouponActivity extends BaseNewActivity implements View.OnClickListener {

    private TitleRelativeLayout mRlTitle;
    private View view;
    private TextView mTvStatus, mTvType;
    private PercentRelativeLayout mRlStatusSelect, mRlTypeSelect;
    private TextView mTvStatusAll, mTvStatusNotUse, mTvStatusUsed, mTvStatusExpired;
    private TextView mTvTypeAll, mTvTypeVouchers, mTvTypeDiscount;
    private ListView mLvCoupon;

    private CouponAdapter mCouponAdapter;

    private List<Coupon> mCouponList = new ArrayList<Coupon>();

    @Override
    protected void init() {
    }

    @Override
    protected View onCreateTitleView(LayoutInflater inflater) {
        View titleView =  inflater.inflate(R.layout.layout_public_title_main, null);
        mRlTitle = (TitleRelativeLayout) titleView.findViewById(R.id.title_rl);
        mRlTitle.childView.findViewById(R.id.tv_go_back).setOnClickListener(this);

        TextView tv_content_desc = (TextView) mRlTitle.childView.findViewById(R.id.tv_content_desc);
        tv_content_desc.setVisibility(View.GONE);
        return titleView;
    }

    @Override
    protected View onCreateSuccessView() {
        view = inflater.inflate(R.layout.activity_coupon, null);
        initView();
        return view;
    }

    @Override
    protected void refreshOrLoadView() {

    }

    @Override
    protected void load() {
        showPageState(FrameLoadLayout.LoadResult.success);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_go_back:
                finish();
                break;
            case R.id.tv_status:
                if (mRlStatusSelect.getVisibility() == View.GONE) {
                    mRlStatusSelect.setVisibility(View.VISIBLE);
                } else {
                    mRlStatusSelect.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_type:
                if (mRlTypeSelect.getVisibility() == View.GONE) {
                    mRlTypeSelect.setVisibility(View.VISIBLE);
                } else {
                    mRlTypeSelect.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_status_all:
                mTvStatus.setText("全部（0）");
                mTvStatus.setTag(0);
                mRlStatusSelect.setVisibility(View.GONE);
                queryCoupon(0, (Integer) mTvType.getTag());
                break;
            case R.id.tv_status_not_use:
                mTvStatus.setText("未使用（1）");
                mTvStatus.setTag(1);
                mRlStatusSelect.setVisibility(View.GONE);
                queryCoupon(1, (Integer) mTvType.getTag());
                break;
            case R.id.tv_status_used:
                mTvStatus.setText("已使用（2）");
                mTvStatus.setTag(2);
                mRlStatusSelect.setVisibility(View.GONE);
                queryCoupon(2, (Integer) mTvType.getTag());
                break;
            case R.id.tv_status_expired:
                mTvStatus.setText("已过期（3）");
                mTvStatus.setTag(3);
                mRlStatusSelect.setVisibility(View.GONE);
                queryCoupon(3, (Integer) mTvType.getTag());
                break;
            case R.id.tv_type_all:
                mTvType.setText("全部（0）");
                mTvType.setTag(0);
                mRlTypeSelect.setVisibility(View.GONE);
                queryCoupon((Integer) mTvStatus.getTag(), 0);
                break;
            case R.id.tv_type_vouchers:
                mTvType.setText("代金券（1）");
                mTvType.setTag(1);
                mRlTypeSelect.setVisibility(View.GONE);
                queryCoupon((Integer) mTvStatus.getTag(), 1);
                break;
            case R.id.tv_type_discount:
                mTvType.setText("折扣券（2）");
                mTvType.setTag(2);
                mRlTypeSelect.setVisibility(View.GONE);
                queryCoupon((Integer) mTvStatus.getTag(), 2);
                break;
            default:
                break;
        }
    }

    private void initView() {
        mTvStatus = (TextView) view.findViewById(R.id.tv_status);
        mTvType = (TextView) view.findViewById(R.id.tv_type);
        mTvStatus.setTag(0);
        mTvType.setTag(0);
        mTvStatus.setOnClickListener(this);
        mTvType.setOnClickListener(this);
        mRlStatusSelect = (PercentRelativeLayout) view.findViewById(R.id.rl_status_select);
        mRlTypeSelect = (PercentRelativeLayout) view.findViewById(R.id.rl_type_select);
        mTvStatusAll = (TextView) view.findViewById(R.id.tv_status_all);
        mTvStatusNotUse = (TextView) view.findViewById(R.id.tv_status_not_use);
        mTvStatusUsed = (TextView) view.findViewById(R.id.tv_status_used);
        mTvStatusExpired = (TextView) view.findViewById(R.id.tv_status_expired);
        mTvTypeAll = (TextView) view.findViewById(R.id.tv_type_all);
        mTvTypeVouchers = (TextView) view.findViewById(R.id.tv_type_vouchers);
        mTvTypeDiscount = (TextView) view.findViewById(R.id.tv_type_discount);
        mTvStatusAll.setOnClickListener(this);
        mTvStatusNotUse.setOnClickListener(this);
        mTvStatusUsed.setOnClickListener(this);
        mTvStatusExpired.setOnClickListener(this);
        mTvTypeAll.setOnClickListener(this);
        mTvTypeVouchers.setOnClickListener(this);
        mTvTypeDiscount.setOnClickListener(this);

        mLvCoupon = (ListView) view.findViewById(R.id.lv_coupon);

        mCouponAdapter = new CouponAdapter(this, mCouponList);
        mLvCoupon.setAdapter(mCouponAdapter);

        queryCoupon(0, 0);
    }

    /**
     * 查询优惠券
     * @param status 状态(0-全部,1-未使用,2-已使用,3-已过期)
     * @param type   优惠券类型(0-全部,1-代金券,2-折扣券)
     */
    private void queryCoupon(int status, int type) {
        QueryCouponRequest request = new QueryCouponRequest();
        request.setStatus(status);
        request.setType(type);
        HttpUtils.getInstance().asyncPost(request, GlobalUtils.COUPON_QUERY_URL, new ICallBack() {
            @Override
            public void onError(Call call, Exception e) {
                LogUtil.e("{" + e.toString() + "}");
            }

            @Override
            public void onSuccess(String response) {
                QueryCouponResult result = JSON.parseObject(response, QueryCouponResult.class);
                LogUtil.i(response);

                if (result.getResultCode() == 0) {
                    if (result.getCouponList() != null && !result.getCouponList().isEmpty()) {
                        mCouponList.clear();
                        mCouponList.addAll(result.getCouponList());
                        mCouponAdapter.notifyDataSetChanged();
                    }
                } else {
                    OwerToastShow.show(result.getMessage());
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRlTitle.clearRegister();
    }
}
