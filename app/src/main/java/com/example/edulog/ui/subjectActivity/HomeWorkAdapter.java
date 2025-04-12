package com.example.edulog.ui.subjectActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edulog.R;
import com.example.edulog.entity.HomeWork;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class HomeWorkAdapter extends RecyclerView.Adapter<HomeWorkAdapter.VH> {
    private Context context;
    private List<HomeWork> homeWorkList;
    private DateTimeFormatter formatter;

    public HomeWorkAdapter(Context context, List<HomeWork> homeWorkList) {
        this.context = context;
        this.homeWorkList = homeWorkList;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("MMM dd", Locale.ENGLISH);
        }
    }

    @NonNull
    @Override
    public HomeWorkAdapter.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_homework, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeWorkAdapter.VH holder, int position) {
        HomeWork homeWork = homeWorkList.get(position);
        holder.task.setText("New material: " + homeWork.getName());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            holder.date.setText(homeWork.getDateTime().format(formatter));
        }

    }
    @SuppressLint("NotifyDataSetChanged")
    public void updateItems(List<HomeWork> newItems) {
        this.homeWorkList = newItems;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (homeWorkList != null) ? homeWorkList.size() : 0;
    }
    class VH extends RecyclerView.ViewHolder {
        TextView task, date;
        public VH(@NonNull View itemView) {
            super(itemView);
            task = itemView.findViewById(R.id.task);
            date = itemView.findViewById(R.id.date);
        }
    }
}
