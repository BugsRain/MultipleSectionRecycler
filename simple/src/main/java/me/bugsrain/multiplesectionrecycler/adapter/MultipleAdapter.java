package me.bugsrain.multiplesectionrecycler.adapter;

import android.content.Context;

import me.bugsrain.library.adapter.base.BaseRecyclerAdapter;
import me.bugsrain.library.adapter.base.Section;
import me.bugsrain.multiplesectionrecycler.provider.ItemViewBannerProvider;
import me.bugsrain.multiplesectionrecycler.provider.ItemViewProvider1;
import me.bugsrain.multiplesectionrecycler.provider.ItemViewProvider2;
import me.bugsrain.multiplesectionrecycler.provider.ItemViewProvider3;
import me.bugsrain.multiplesectionrecycler.provider.ItemViewProvider4;

/**
 * Created by quan on 16/8/12.
 */

public class MultipleAdapter extends BaseRecyclerAdapter {
    private Object[] mData;
    public MultipleAdapter(Context mContext, Object... data) {
        super(mContext);
        mData = data;
        init();
    }

    @Override
    protected int sectionTotalCount() {
        return 5;
    }

    @Override
    protected void sectionInit(Section section) {
        switch (section.getType()){
            case 0:

                section.setData(mData[0], true);
                section.setItemViewProvider(ItemViewBannerProvider.class);
                break;
            case 1:
                section.setData(mData[1], false);
                section.setItemViewProvider(ItemViewProvider1.class);
                break;
            case 2:
                section.setData(mData[2], false);
                section.setItemViewProvider(ItemViewProvider2.class);
                break;
            case 3:
                section.setData(mData[3], false);
                section.setItemViewProvider(ItemViewProvider3.class);
                break;
            case 4:
                section.setData("固定测试数据", false);
                section.setItemCount(2);
                section.setItemViewProvider(ItemViewProvider4.class);
                break;
        }
    }

    @Override
    public String initSectionHeaderLeftContent(Section section) {
        if(section.getType() == 1){
            return "测试标题";
        }
        return super.initSectionHeaderLeftContent(section);
    }

    @Override
    public String initSectionFooterLeftContent(Section section) {
        if(section.getType() == 1){
            return "测试底部";
        }
        return super.initSectionFooterLeftContent(section);
    }
}
