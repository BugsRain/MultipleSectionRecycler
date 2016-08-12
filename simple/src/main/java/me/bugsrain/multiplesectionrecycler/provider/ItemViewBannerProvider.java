package me.bugsrain.multiplesectionrecycler.provider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;

import java.util.List;

import me.bugsrain.library.adapter.base.Section;
import me.bugsrain.library.adapter.holder.BaseViewHolder;
import me.bugsrain.library.adapter.provider.base.ItemViewProvider;
import me.bugsrain.multiplesectionrecycler.R;


/**
 * Created by quan on 16/8/10.
 */

public class ItemViewBannerProvider extends ItemViewProvider<ItemViewBannerProvider.ViewHolder, List<Integer>> {

    private boolean isInit = false;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, int type) {
        return new ViewHolder(inflater.inflate(R.layout.banner, parent, false), type);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, Section<List<Integer>> section) {
        if(!isInit) {
            isInit = true;
            holder.convenientBanner.setPages(
                    new CBViewHolderCreator<LocalImageHolderView>() {
                        @Override
                        public LocalImageHolderView createHolder() {
                            return new LocalImageHolderView();
                        }
                    }, section.getData())
                    //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                    //设置指示器的方向
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
            holder.convenientBanner.startTurning(6000);
        }
    }



    class ViewHolder extends BaseViewHolder {
        ConvenientBanner convenientBanner;

        ViewHolder(View itemView, int type) {
            super(itemView, type);
            convenientBanner = (ConvenientBanner) itemView.findViewById(R.id.convenientBanner);
        }
    }

    private class LocalImageHolderView implements Holder<Integer> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            if (imageView == null)
                imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, final int position, Integer data) {
            imageView.setBackgroundColor(data);
        }
    }
}