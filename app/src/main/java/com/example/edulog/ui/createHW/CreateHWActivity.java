package com.example.edulog.ui.createHW;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import static com.example.edulog.utils.AnimationView.expandView;

import android.animation.ValueAnimator;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.OpenableColumns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.edulog.R;
import com.example.edulog.utils.AnimationView;
import com.google.android.material.materialswitch.MaterialSwitch;

import java.io.IOException;
import java.util.Calendar;

public class CreateHWActivity extends AppCompatActivity {

    private TextView textDeadline;
    private Button btnPickDeadline, btnPickFile;
    private ImageView pdfPreview;
    private MaterialSwitch aSwitch;
    private LinearLayout deadlineLayout;

    private final int PICK_FILE_REQUEST_CODE = 101;
    private Uri pickedFileUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_hwactivity);

        textDeadline = findViewById(R.id.textDeadline);
        btnPickDeadline = findViewById(R.id.btnPickDeadline);
        btnPickFile = findViewById(R.id.btnPickFile);
        pdfPreview = findViewById(R.id.pdfPreview);
        aSwitch = findViewById(R.id.switchExample);
        deadlineLayout = findViewById(R.id.deadline_layout);

        btnPickDeadline.setOnClickListener(view -> showDateTimePicker());

        btnPickFile.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            intent.putExtra(Intent.EXTRA_MIME_TYPES, new String[]{"application/pdf", "application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"});
            startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_FILE_REQUEST_CODE);
        });
        aSwitch.setOnCheckedChangeListener((buttoniew, isChecked) -> {
            if (isChecked) {
                AnimationView.expandView(deadlineLayout);
                aSwitch.setTrackTintList(ColorStateList.valueOf(getResources().getColor(R.color.greendark, getTheme())));
            } else {
                AnimationView.collapseView(deadlineLayout);
                aSwitch.setTrackTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray2, getTheme())));
            }
        });
    }

    private void showDateTimePicker() {
        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, month, dayOfMonth) -> {

                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                            (view1, hourOfDay, minute) -> {
                                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calendar.set(Calendar.MINUTE, minute);

                                String deadline = android.text.format.DateFormat.format("dd MMM yyyy, HH:mm", calendar).toString();
                                textDeadline.setText(deadline);

                            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                    timePickerDialog.show();

                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            pickedFileUri = data.getData();
            if (pickedFileUri != null) {
                String type = getContentResolver().getType(pickedFileUri);
                if (type != null && type.equals("application/pdf")) {
                    try {
                        previewPdf(pickedFileUri);
                    } catch (IOException e) {
                        Toast.makeText(this, "Error displaying PDF preview", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(this, "File selected: " + getFileName(pickedFileUri), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void previewPdf(Uri uri) throws IOException {
        ParcelFileDescriptor fileDescriptor = getContentResolver().openFileDescriptor(uri, "r");
        if (fileDescriptor != null) {
            PdfRenderer pdfRenderer = new PdfRenderer(fileDescriptor);
            if (pdfRenderer.getPageCount() > 0) {
                PdfRenderer.Page page = pdfRenderer.openPage(0);
                Bitmap bitmap = Bitmap.createBitmap(
                        page.getWidth(),
                        page.getHeight(),
                        Bitmap.Config.ARGB_8888);
                page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
                pdfPreview.setImageBitmap(bitmap);
                page.close();
            }
            pdfRenderer.close();
        }
    }

    private String getFileName(Uri uri) {
        String result = "Unknown";
        try (android.database.Cursor cursor = getContentResolver()
                .query(uri, null, null, null, null)) {
            if (cursor != null && cursor.moveToFirst()) {
                int index = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                result = cursor.getString(index);
            }
        }
        return result;
    }

}
