package com.wckj.gfsj.Bean;


import com.wckj.gfsj.Bean.Base.PageResult;
import com.wckj.gfsj.Bean.entity.Recommend;

import java.util.List;

/**
 * Created by Administrator on 2016/7/25.
 */
public class RecommendNewGoodsResult extends PageResult {

    //新品推荐列表
    private List<Recommend> newList;


    public List<Recommend> getNewList() {
        return newList;
    }

    public void setNewList(List<Recommend> newList) {
        this.newList = newList;
    }


    @Override
    public String toString() {
        return "RecommendGoodsResult{" +
                "newList=" + newList +
                '}';
    }
}