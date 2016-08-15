package me.bugsrain.multiplesectionrecycler.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import me.bugsrain.library.adapter.base.BaseRecyclerAdapter;
import me.bugsrain.library.adapter.base.Section;
import me.bugsrain.library.adapter.holder.BaseViewHolder;
import me.bugsrain.multiplesectionrecycler.provider.ItemViewBannerProvider;
import me.bugsrain.multiplesectionrecycler.provider.ItemViewProvider1;
import me.bugsrain.multiplesectionrecycler.provider.ItemViewProvider2;
import me.bugsrain.multiplesectionrecycler.provider.ItemViewProvider3;
import me.bugsrain.multiplesectionrecycler.provider.ItemViewProvider4;

/**
 * Created by quan on 16/8/12.
 */

public class MultipleAdapter extends BaseRecyclerAdapter {

    public MultipleAdapter(Context mContext, Object... data) {
        super(mContext);
        init(data);

    }

    @Override
    public void onViewAttachedToWindow(BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        GridLayoutManager manager = new GridLayoutManager(getContext(), 3);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = getItemViewType(position);
                if(type == 0){
                    return 3;
                }else if(isTypeNotItem(type)){
                    return 3;
                }else if( type == 2){
                    return 1;
                }
                return 1;
            }



            @Override
            public int getSpanGroupIndex(int adapterPosition, int spanCount) {
                return super.getSpanGroupIndex(adapterPosition, spanCount);
            }

        });
        recyclerView.setLayoutManager(manager);

        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    protected void sectionInit(Section section, Object data) {
        switch (section.getType()){
            case 0:

                section.setData(data, true);
                section.setItemViewProvider(ItemViewBannerProvider.class);
                break;
            case 1:
                section.setData(data, false);
                section.setItemViewProvider(ItemViewProvider1.class);
                break;
            case 2:
                section.setData(data, false);
                section.setItemViewProvider(ItemViewProvider2.class);
                break;
            case 3:
                section.setData(data, false);
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
