package me.bugsrain.multiplesectionrecycler.provider;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.bugsrain.library.adapter.base.Section;
import me.bugsrain.library.adapter.holder.BaseViewHolder;
import me.bugsrain.library.adapter.provider.base.ItemViewProvider;
import me.bugsrain.multiplesectionrecycler.R;


/**
 * Created by quan on 16/8/10.
 */

public class ItemViewProvider4 extends ItemViewProvider<ItemViewProvider4.ViewHolder, String> {
    @NonNull
    @Override
    public ItemViewProvider4.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, int type) {
        return new ItemViewProvider4.ViewHolder(inflater.inflate(R.layout.viewhoder4, parent, false), type);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, Section<String> section) {
        holder.textView.setText(section.getData());
    }



    class ViewHolder extends BaseViewHolder {
        TextView textView;

        ViewHolder(View itemView, int type) {
            super(itemView, type);
            textView = (TextView) itemView.findViewById(R.id.item);
        }
    }
}
