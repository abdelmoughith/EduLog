package com.example.edulog.ui.home.subject;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import com.example.edulog.R;

public class TitleAdapter extends RecyclerView.Adapter<TitleAdapter.VH> {

    private List<String> stringList;
    private int focusedPosition;
    private FilterListenner filterListenner;

    public TitleAdapter(List<String> stringList, int focusedPosition, FilterListenner filterListenner) {
        this.stringList = stringList;
        this.focusedPosition = focusedPosition;
        this.filterListenner = filterListenner;
    }

    @NonNull
    @Override
    public TitleAdapter.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_group, parent, false);
        return new VH(v);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull TitleAdapter.VH holder, @SuppressLint("RecyclerView") int position) {
        String selected = stringList.get(position);
        holder.textView.setText(selected);
        // Set the background color based on the focused item
        if (position == focusedPosition) {
            holder.constraintLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.items_background_focused));
            holder.textView.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
        } else {
            holder.constraintLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.items_background));
            holder.textView.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
        }
        holder.itemView.setOnClickListener(v -> {
            if (focusedPosition == position) {
                focusedPosition = -1;
                //locationCityListenner.onChangeCity(null);
            } else {
                focusedPosition = position;
                //locationCityListenner.onChangeCity(selected);
            }
            notifyDataSetChanged();

        });
    }
    @SuppressLint("NotifyDataSetChanged")
    public void updateItems(List<String> newItems) {
        this.stringList = newItems;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return  (stringList!= null) ? stringList.size() : 0;
    }
    class VH extends RecyclerView.ViewHolder {
        TextView textView;
        ConstraintLayout constraintLayout;
        public VH(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.location_name);
            constraintLayout = itemView.findViewById(R.id.item_location);

        }
    }
}

