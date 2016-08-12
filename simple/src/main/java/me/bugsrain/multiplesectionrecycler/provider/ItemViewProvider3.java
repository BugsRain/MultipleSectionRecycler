package me.bugsrain.multiplesectionrecycler.provider;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import me.bugsrain.library.adapter.base.Section;
import me.bugsrain.library.adapter.holder.BaseViewHolder;
import me.bugsrain.library.adapter.provider.base.ItemViewProvider;
import me.bugsrain.multiplesectionrecycler.R;


/**
 * Created by quan on 16/8/10.
 */

public class ItemViewProvider3 extends ItemViewProvider<ItemViewProvider3.ViewHolder, ArrayList<String>> {
    @NonNull
    @Override
    public ItemViewProvider3.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, int type) {
        return new ItemViewProvider3.ViewHolder(inflater.inflate(R.layout.viewhoder3, parent, false), type);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, Section<ArrayList<String>> section) {
        holder.textView.setText(section.getData().get(position) +", 下标: " + position);
    }


    class ViewHolder extends BaseViewHolder {
        TextView textView;

        ViewHolder(View itemView, int type) {
            super(itemView, type);
            textView = (TextView) itemView.findViewById(R.id.item);
        }
    }
}
