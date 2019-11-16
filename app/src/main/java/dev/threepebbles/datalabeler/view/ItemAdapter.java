package dev.threepebbles.datalabeler.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.threepebbles.datalabeler.R;
import dev.threepebbles.datalabeler.model.DataLabel;

public class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {
    private static final String TAG = "ItemAdapter";
    
    private Context context;
    private List<DataLabel> dataLabels;
    private OnItemListener onItemListener;

    public ItemAdapter(
            Context context,
            List<DataLabel> dataLabels,
            OnItemListener onItemListener) {
        this.context = context;
        this.dataLabels = dataLabels;
        this.onItemListener = onItemListener;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int position) {
        DataLabel dataLabel = dataLabels.get(position);
        itemHolder.setItemDetails(dataLabel);
    }

    @Override
    public int getItemCount() {
        return dataLabels.size();
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(context)
                .inflate(R.layout.item_label, parent, false);

        return new ItemHolder(view, onItemListener);
    }

    public interface OnItemListener {
        void onItemClick(int position);
    }
}
