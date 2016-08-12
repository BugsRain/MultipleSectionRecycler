package me.bugsrain.library.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import me.bugsrain.library.adapter.holder.BaseViewHolder;
import me.bugsrain.library.adapter.provider.base.ItemViewProvider;


/**
 * Created by quan on 16/8/9.
 */

public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {


    /**
     * 1111 1111 1111 1111 1111 1111 1111 11-------------------1-------------------1
     * (ITEM_HEADER_MASK_MIN ~ ITEM_HEADER_MASK]-------------------------|
     * (ITEM_FOOTER_MASK_MIN ~ ITEM_FOOTER_MASK]-------|
     */
    //块头部遮罩值
    private final static int ITEM_HEADER_MASK = Integer.MAX_VALUE;
    //块头部最小遮罩值
    private final static int ITEM_HEADER_MASK_MIN = Integer.MAX_VALUE >> 1;
    //块底部遮罩值
    private final static int ITEM_FOOTER_MASK = ITEM_HEADER_MASK_MIN;
    //块底部最小遮罩值
    private final static int ITEM_FOOTER_MASK_MIN = Integer.MAX_VALUE >> 2;

    private RecyclerView mView;

    private ArrayList<Section> mSections;

    private Context mContext;

    private LayoutInflater mInflater;

    public BaseRecyclerAdapter(Context mContext) {
        this.mContext = mContext;

        mInflater = LayoutInflater.from(mContext);
    }

    protected void init(){
        mSections = new ArrayList<>();
        int count = sectionTotalCount();
        if (count > 0) {
            Section tempSection = null;
            for (int i = 0; i < count; i++) {
                Section section = new Section();
                section.setType(i);
                if (tempSection != null) {
                    section.setPreSection(tempSection);
                }
                section.setHeaderLeftContent(initSectionHeaderLeftContent(section));
                section.setHeaderLeftImageId(initSectionHeaderLeftImage(section));
                section.setFooterLeftContent(initSectionFooterLeftContent(section));
                section.setFooterLeftImageId(initSectionFooterLeftImage(section));

                sectionInit(section);
                tempSection = section;
                mSections.add(section);
//                System.out.println("->>>>>>>>>>>>>>>> section index = " + i + ", start = " + section.start + ", end = " + section.end + ", count = " + section.itemCount);
            }
        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        Section section = mSections.get(viewType);
        Section section = mSections.get(getTypeCompat(viewType));

        if (viewType > ITEM_HEADER_MASK_MIN && section.isHasHeader()) {
            return section.getHeaderProvider().onCreateViewHolder(mInflater, parent, viewType);
        } else if (viewType > ITEM_FOOTER_MASK_MIN && viewType <= ITEM_FOOTER_MASK && section.isHasFooter()) {
            return section.getFooterProvider().onCreateViewHolder(mInflater, parent, viewType);
        } else {
            ItemViewProvider provider = section.getItemViewProvider();
            if (provider != null) {
                return provider.onCreateViewHolder(mInflater, parent, viewType);
            }
            return null;
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        int type = getItemViewType(position);
        Section section = mSections.get(getTypeCompat(type));

        if (section.isHasHeader() && section.getStart() == position) {
            section.getHeaderProvider().onBindViewHolder(holder, section.getStart(), section);
        } else if (section.isHasFooter() && section.getEnd() == position) {
            section.getFooterProvider().onBindViewHolder(holder, section.getEnd(), section);
        } else {
            int realPosition = position - section.getStart();
            if (section.isHasHeader()) realPosition--;
            section.getItemViewProvider().onBindViewHolder(holder, realPosition, section);
        }
    }

    private int getTypeCompat(int viewType) {
        if (viewType > ITEM_HEADER_MASK_MIN) {
            viewType ^= ITEM_HEADER_MASK;
        } else if (viewType > ITEM_FOOTER_MASK_MIN && viewType <= ITEM_HEADER_MASK_MIN) {
            viewType ^= ITEM_FOOTER_MASK;
        }
        return viewType;
    }

    @Override
    public int getItemViewType(int position) {
        int type = 0;
        if (!isSectionEmpty()) {
            for (Section section : mSections) {
                if (section.isHasHeader() && section.getStart() == position) {
                    type = section.getType() ^ ITEM_HEADER_MASK;
                } else if (section.isHasFooter() && section.getEnd() == position) {
                    type = section.getType() ^ ITEM_FOOTER_MASK;
                } else if (position >= section.getStart() && position <= section.getEnd()) {
                    type = section.getType();
                    break;
                }
            }
        }
        return type;
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (!isSectionEmpty()) {
            for (Section section : mSections) {
                count += section.getItemCount();
            }
        }
        return count;
    }

    public void refreshData() {
        for (Section section : mSections) {
            sectionInit(section);
//            notifyItemRangeChanged(section.getStart(), section.itemCount);
        }
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        mView = recyclerView;
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        mContext = null;
        mView = null;
        mInflater = null;
        if (mSections != null) {
            for (Section section : mSections) {
                if (section != null) section.release();
            }
            mSections.clear();
            mSections = null;
        }
        super.onDetachedFromRecyclerView(recyclerView);
    }

    public String initSectionHeaderLeftContent(Section section) {
        return null;
    }

    public int initSectionHeaderLeftImage(Section section) {
        return -1;
    }

    public String initSectionFooterLeftContent(Section section) {
        return null;
    }

    public int initSectionFooterLeftImage(Section section) {
        return -1;
    }

    protected abstract int sectionTotalCount();

    protected abstract void sectionInit(Section section);


    private boolean isSectionEmpty() {
        return mSections == null || mSections.isEmpty();
    }

    protected Context getContext() {
        return mContext;
    }

    protected RecyclerView getView() {
        return mView;
    }

}
