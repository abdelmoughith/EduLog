package com.example.edulog.ui.mark;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edulog.R;
import com.example.edulog.entity.SubjectTable;

import java.util.List;
import java.util.Map;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.VH> {

    private Context context;
    private List<SubjectTable> data;

    public TableAdapter(Context context, List<SubjectTable> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public TableAdapter.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_table_mark, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TableAdapter.VH holder, int position) {
        SubjectTable subjectTable = data.get(position);
        holder.title.setText(subjectTable.getTitle());
        String col1;
        String col2 = "Mark";
        String col3 = "Result"; // Third column label

        if (subjectTable.isHomeWork()) {
            col1 = "HomeWork";
        } else {
            col1 = "Subject";
        }

        // Check if the header already exists
        if (holder.tableLayout.getChildCount() == 0) {  // Only add header if it's not present already
            TableRow headerRow = new TableRow(context);

            TextView headerSubject = new TextView(context);
            headerSubject.setText(col1);
            headerSubject.setPadding(16, 8, 16, 8);
            headerSubject.setTextColor(context.getResources().getColor(R.color.fixedwhite, context.getTheme()));
            headerSubject.setBackgroundResource(R.drawable.cell_header);
            TableRow.LayoutParams subjectHeaderParams = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f);
            headerSubject.setLayoutParams(subjectHeaderParams);

            TextView headerMark = new TextView(context);
            headerMark.setText(col2);
            headerMark.setPadding(16, 8, 16, 8);
            headerMark.setTextColor(context.getResources().getColor(R.color.fixedwhite, context.getTheme()));
            headerMark.setBackgroundResource(R.drawable.cell_header);
            TableRow.LayoutParams markHeaderParams = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f);
            headerMark.setLayoutParams(markHeaderParams);


            headerRow.addView(headerSubject);
            headerRow.addView(headerMark);

            // Add third column header if it's not HomeWork
            if (!subjectTable.isHomeWork()) {
                holder.finalMark.setVisibility(View.VISIBLE);
                TextView headerAdditional = new TextView(context);
                headerAdditional.setText(col3);
                headerAdditional.setPadding(16, 8, 16, 8);
                headerAdditional.setTextColor(context.getResources().getColor(R.color.fixedwhite, context.getTheme()));
                headerAdditional.setBackgroundResource(R.drawable.cell_header);
                TableRow.LayoutParams additionalHeaderParams = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f);
                additionalHeaderParams.gravity = Gravity.END; // Align to the right
                headerAdditional.setLayoutParams(additionalHeaderParams);
                headerRow.addView(headerAdditional);
            } else {
                holder.finalMark.setVisibility(View.GONE);
            }



            // Add the header row to the table
            holder.tableLayout.addView(headerRow);
        }

        // Use the helper method to update the table rows
        updateTableLayout(holder.tableLayout, subjectTable.getSubjectMarks(), subjectTable.isHomeWork());
    }

    private void updateTableLayout(TableLayout tableLayout, Map<String, String> subjectMarks, boolean isHomeWork) {
        // Remove all rows except the header row, if there are any rows
        if (tableLayout.getChildCount() > 0) {
            tableLayout.removeViews(1, tableLayout.getChildCount() - 1); // Start at 1 to keep the header
        }

        // Add the rows for subject marks
        for (Map.Entry<String, String> entry : subjectMarks.entrySet()) {
            TableRow row = new TableRow(context);

            TextView subjectCell = new TextView(context);
            subjectCell.setText(entry.getKey());
            subjectCell.setPadding(16, 8, 16, 8);
            subjectCell.setTextColor(context.getResources().getColor(R.color.black, context.getTheme()));
            subjectCell.setBackgroundResource(R.drawable.cell_body);
            TableRow.LayoutParams subjectParams = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f);
            subjectCell.setLayoutParams(subjectParams);

            TextView markCell = new TextView(context);
            markCell.setText(entry.getValue());
            markCell.setPadding(16, 8, 16, 8);
            markCell.setTextColor(context.getResources().getColor(R.color.black, context.getTheme()));
            markCell.setBackgroundResource(R.drawable.cell_body);
            TableRow.LayoutParams markParams = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f);
            markCell.setLayoutParams(markParams);
            row.addView(subjectCell);
            row.addView(markCell);
            // Add third column if it's not HomeWork
            if (!isHomeWork) {
                TextView additionalCell = new TextView(context);
                if (Float.parseFloat(entry.getValue().substring(0,5)) >= 10.00F) {
                    additionalCell.setText("✅");
                } else {
                    additionalCell.setText("❌");
                }

                additionalCell.setPadding(16, 8, 16, 8);
                additionalCell.setTextColor(context.getResources().getColor(R.color.black, context.getTheme()));
                additionalCell.setBackgroundResource(R.drawable.cell_body);
                TableRow.LayoutParams additionalParams = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f);
                additionalCell.setLayoutParams(additionalParams);
                row.addView(additionalCell);
            }



            tableLayout.addView(row);
        }
    }


    @Override
    public int getItemCount() {
        return (data != null) ? data.size() : 0;
    }
    class VH extends RecyclerView.ViewHolder {
        TableLayout tableLayout;
        TextView title, finalMark;
        public VH(@NonNull View itemView) {
            super(itemView);
            tableLayout = itemView.findViewById(R.id.tableLayout);
            finalMark = itemView.findViewById(R.id.finalMark);
            title = itemView.findViewById(R.id.title);
        }
    }
}
