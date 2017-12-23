package com.tomclaw.drawa.stock;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by solkin on 19/12/2017.
 */
public class StockItemHolder extends RecyclerView.ViewHolder {

    private StockItemView itemView;

    public StockItemHolder(StockItemView itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    public void bind(final StockItem item, final StockItemClickListener listener) {
        itemView.showImage(item.getImage());
        itemView.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(item);
                }
            }
        });
    }
}
