package me.bugsrain.library.adapter.base;

import android.text.TextUtils;

import java.util.List;

import me.bugsrain.library.adapter.provider.base.ItemViewDefaultFooterProvider;
import me.bugsrain.library.adapter.provider.base.ItemViewDefaultHeaderProvider;
import me.bugsrain.library.adapter.provider.base.ItemViewProvider;


/**
 * Created by quan on 16/8/12.
 */

public class Section<T> {
    private int type;

    private int itemCount;

    private int start;

    private int end;

    private Section preSection;

    private ItemViewProvider itemViewProvider, headerProvider, footerProvider;

    private T data;

    private String headerLeftContent;

    private int headerLeftImageId = -1;

    private String footerLeftContent;

    private int footerLeftImageId = -1;

    public String getFooterLeftContent() {
        return footerLeftContent;
    }

    public void setFooterLeftContent(String footerLeftContent) {
        this.footerLeftContent = footerLeftContent;
    }

    public int getFooterLeftImageId() {
        return footerLeftImageId;
    }

    public void setFooterLeftImageId(int footerLeftImageId) {
        this.footerLeftImageId = footerLeftImageId;
    }

    public int getHeaderLeftImageId() {
        return headerLeftImageId;
    }

    public void setHeaderLeftImageId(int headerLeftImageId) {
        this.headerLeftImageId = headerLeftImageId;
    }

    public String getHeaderLeftContent() {
        return headerLeftContent;
    }

    public void setHeaderLeftContent(String headerLeftContent) {
        this.headerLeftContent = headerLeftContent;
    }

    public T getData() {
        return data;
    }


    public void setData(T data, boolean isDefaultSingle) {
        this.data = data;
        if (data != null && data instanceof List) {
            List list = (List) data;
            if (isDefaultSingle) {
                setItemCount(1);
            } else {
                setItemCount(list.size());
            }
        } else if (data != null) {
            setItemCount(1);
        }
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
        if (isHasPreSection()) {

            this.start = preSection.getEnd() + 1;
            if (isHasHeader()) {
                this.itemCount++;
//                    this.start++;
            }

            if (isHasFooter()) {
                this.itemCount++;
            }

            this.end = this.start + this.itemCount - 1;

        } else {
            this.end = itemCount - 1;
        }
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public Section getPreSection() {
        return preSection;
    }

    public void setPreSection(Section preSection) {
        this.preSection = preSection;
    }

    public boolean isHasPreSection() {
        return getPreSection() != null;
    }

    public ItemViewProvider getItemViewProvider() {
        return itemViewProvider;
    }

    public void setItemViewProvider(Class<? extends ItemViewProvider> cls) {
        if (this.itemViewProvider == null) {
            try {
                this.itemViewProvider = cls.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ItemViewProvider getHeaderProvider() {
        return headerProvider;
    }

    public void setHeaderProvider(Class<? extends ItemViewProvider> cls) {
        if (this.headerProvider == null) {
            try {
                this.headerProvider = cls.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ItemViewProvider getFooterProvider() {
        return footerProvider;
    }

    public void setFooterProvider(Class<? extends ItemViewProvider> cls) {
        if (this.footerProvider == null) {
            try {
                this.footerProvider = cls.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void release() {
        if (data != null) data = null;
        if (preSection != null) preSection = null;
        if (itemViewProvider != null) itemViewProvider = null;
    }

    public boolean isHasHeader() {
        boolean b = !TextUtils.isEmpty(headerLeftContent) || headerLeftImageId != -1;
        if (b) {
            setHeaderProvider(ItemViewDefaultHeaderProvider.class);
        }
        return b;
    }

    public boolean isHasFooter() {
        boolean b = !TextUtils.isEmpty(footerLeftContent) || footerLeftImageId != -1;
        if (b) {
            setFooterProvider(ItemViewDefaultFooterProvider.class);
        }
        return b;
    }
}
