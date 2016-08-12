package me.bugsrain.library.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by quan on 16/8/9.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    private int type;

    protected BaseViewHolder(View itemView, int type) {
        super(itemView);

    }

    public int getType() {
        return type;
    }
}
