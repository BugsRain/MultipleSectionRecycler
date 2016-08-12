package me.bugsrain.library.adapter.provider.base;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.bugsrain.library.R;
import me.bugsrain.library.adapter.base.Section;
import me.bugsrain.library.adapter.holder.BaseViewHolder;


/**
 * Created by quan on 16/8/10.
 */

public class ItemViewDefaultFooterProvider extends ItemViewProvider<ItemViewDefaultFooterProvider.ViewHolder, Section> {
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, int type) {
        return new ViewHolder(inflater.inflate(R.layout.item_section_footer, parent, false), type);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Section data) {
        holder.textView.setText(data.getFooterLeftContent());

    }

    class ViewHolder extends BaseViewHolder {
        TextView textView;
        ViewHolder(View itemView, int type) {
            super(itemView, type);
            textView = (TextView) itemView.findViewById(R.id.left);
        }
    }


}
