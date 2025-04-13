package com.example.edulog.ui.homework;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

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
        holder.more_actions.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(context, holder.more_actions);
            popupMenu.inflate(R.menu.more_info_item);
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                if (menuItem.getItemId() == R.id.menu_edit) {
                    Toast.makeText(context, "Edit: " + menuItem, Toast.LENGTH_SHORT).show();
                    return true;
                } else if (menuItem.getItemId() == R.id.menu_delete) {
                    Toast.makeText(context, "Delete: " + menuItem, Toast.LENGTH_SHORT).show();
                    return true;
                } else {
                    return false;
                }

            });
            popupMenu.show();
        });

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
        ImageView more_actions;
        public VH(@NonNull View itemView) {
            super(itemView);
            task = itemView.findViewById(R.id.task);
            date = itemView.findViewById(R.id.date);
            more_actions = itemView.findViewById(R.id.more_actions);
        }
    }
}
