package com.example.edulog.ui.people;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edulog.R;
import com.example.edulog.entity.HomeWork;
import com.example.edulog.entity.Person;

import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.VH> {
    private Context context;
    private List<Person> people;

    public PeopleAdapter(Context context, List<Person> people) {
        this.context = context;
        this.people = people;
    }

    @NonNull
    @Override
    public PeopleAdapter.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleAdapter.VH holder, int position) {
        Person person = people.get(position);
        holder.fullName.setText(person.getFullName());
    }

    @Override
    public int getItemCount() {
        return (people != null) ? people.size() : 0;
    }
    @SuppressLint("NotifyDataSetChanged")
    public void updateItems(List<Person> people) {
        this.people = people;
        notifyDataSetChanged();
    }
    class VH extends RecyclerView.ViewHolder {
        TextView fullName;
        public VH(@NonNull View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.person);
        }
    }
}
