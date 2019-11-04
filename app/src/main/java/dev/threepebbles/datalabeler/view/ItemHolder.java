package dev.threepebbles.datalabeler.view;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import dev.threepebbles.datalabeler.R;
import dev.threepebbles.datalabeler.model.DataLabel;

public class ItemHolder extends RecyclerView.ViewHolder {
    public TextView itemTitle;
    public TextView itemDescription;
    public TextView itemValue;

    public ItemHolder(View itemView, ItemAdapter.OnItemListener onItemListener) {
        super(itemView);

        this.itemTitle = itemView.findViewById(R.id.itemTitle);
        this.itemDescription = itemView.findViewById(R.id.itemDescription);
        this.itemValue = itemView.findViewById(R.id.itemValue);

        itemView.setOnClickListener(v -> onItemListener.onItemClick(getAdapterPosition()));
    }

    public void setItemDetails(DataLabel dataLabel) {
        // Add in when things are implemented
//        this.itemTitle.setText(dataLabel.getTitle());
//        this.itemTitle.setText(dataLabel.getDescription());
//        this.itemTitle.setText(dataLabel.getValue());
    }
}
