package com.example.edulog.ui.home.subject;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edulog.R;
import com.example.edulog.entity.Subject;
import com.example.edulog.ui.subjectActivity.SubjectDetailActivity;

import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.VH> {

    private Context context;
    private List<Subject> subjectList;

    public SubjectAdapter(Context context, List<Subject> subjectList) {
        this.context = context;
        this.subjectList = subjectList;
    }

    @NonNull
    @Override
    public SubjectAdapter.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subject, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectAdapter.VH holder, int position) {
        Subject current = subjectList.get(position);
        holder.title_post.setText(current.getTitle());
        holder.teacher_post.setText(current.getTeacher().getName());
        char s = (current.getStudentNumber() > 1) ? 's' : '\0';
        holder.studentNumber.setText(current.getStudentNumber() + " student" + s);
        if (current.isMissingWork())
            holder.warningCard.setVisibility(VISIBLE);
        else
            holder.warningCard.setVisibility(INVISIBLE);
        holder.semestre.setText(current.getGroup());

        String imageName = current.getImageUrl();  // Assuming this is the name of your raw resource, e.g., "jee"

        // Get the resource ID dynamically
        int resId = context.getResources().getIdentifier(imageName, "raw", context.getPackageName());
        // Check if the resource exists and load the image
        if (resId != 0) {  // If the resource exists
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
            holder.cover.setImageBitmap(bitmap);
        } else {
            // Handle the case where the resource doesn't exist
            Log.e("RecyclerView", "Image resource not found: " + imageName);
        }


        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, SubjectDetailActivity.class);
            intent.putExtra("subject", current.getTitle());
            intent.putExtra("cover", current.getImageUrl());
            context.startActivity(intent);
        });
    }
    @SuppressLint("NotifyDataSetChanged")
    public void updateData(List<Subject> newData) {
        this.subjectList = newData;
        notifyDataSetChanged(); // Or use DiffUtil for smoother updates
    }


    @Override
    public int getItemCount() {
        return (subjectList != null) ? subjectList.size() : 0;
    }
    class VH extends RecyclerView.ViewHolder {
        TextView title_post, teacher_post, studentNumber, semestre;
        CardView warningCard;
        ImageView cover;
        public VH(@NonNull View itemView) {
            super(itemView);
            title_post = itemView.findViewById(R.id.title_post);
            teacher_post = itemView.findViewById(R.id.teacher_post);
            studentNumber = itemView.findViewById(R.id.studentNumber);
            warningCard = itemView.findViewById(R.id.warningCard);
            semestre = itemView.findViewById(R.id.semestre);
            cover = itemView.findViewById(R.id.cover);
        }
    }
}
