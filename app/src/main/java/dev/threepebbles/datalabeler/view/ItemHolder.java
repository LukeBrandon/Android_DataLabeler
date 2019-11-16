package dev.threepebbles.datalabeler.view;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import dev.threepebbles.datalabeler.R;
import dev.threepebbles.datalabeler.model.DataLabel;

public class ItemHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "ItemHolder";
    
    private TextView itemTitle;
    private TextView itemDescription;
    private TextView itemValue;

    public ItemHolder(View itemView, ItemAdapter.OnItemListener onItemListener) {
        super(itemView);

        this.itemTitle = itemView.findViewById(R.id.itemTitle);
        this.itemDescription = itemView.findViewById(R.id.itemDescription);
        this.itemValue = itemView.findViewById(R.id.itemValue);

        itemView.setOnClickListener(v -> onItemListener.onItemClick(getAdapterPosition()));
    }

    public void setItemDetails(DataLabel dataLabel) {
        // Add in when things are implemented
        this.itemTitle.setText(dataLabel.getCategoryName());
        this.itemDescription.setText(dataLabel.getDescription());
        this.itemValue.setText(Double.toString(dataLabel.getValue()));
    }
}
