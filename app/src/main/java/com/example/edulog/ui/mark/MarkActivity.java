package com.example.edulog.ui.mark;

import static com.example.edulog.utils.ColorGenerator.getRandomColor;

import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edulog.R;
import com.example.edulog.entity.SubjectTable;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarkActivity extends AppCompatActivity {
    RecyclerView listTables;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mark);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        assignList();

    }


    private void assignList() {

        listTables = findViewById(R.id.listTables);
        TableAdapter tableAdapter = new TableAdapter(this, getTables());
        listTables.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listTables.setAdapter(tableAdapter);
    }
    private List<SubjectTable> getTables() {
        List<SubjectTable> subjectTables = new ArrayList<>();
        Map<String, String> subjectMarks = new HashMap<>();
        subjectMarks.put("BIG DATA", "16.50/20");
        subjectMarks.put("Génie Logiciel", "14.75/20");
        subjectMarks.put("Entrepreneuriat", "18.00/20");
        subjectMarks.put("Administration Windows", "12.00/20");
        subjectMarks.put("Administration ORACLE 2", "17.50/20");
        subjectMarks.put("Communication professionnelle 2", "15.00/20");
        subjectMarks.put("Programmation Mobile", "09.00/20");
        subjectMarks.put("DEVOPS", "13.50/20");
        subjectMarks.put("Sécurité des Réseaux", "16.25/20");
        subjectMarks.put("Virtualisation", "14.00/20");
        subjectMarks.put("Datawarehouse", "07.00/20");
        subjectMarks.put("J2EE1(Strust, Hibernate)", "18.50/20");
        subjectMarks.put("Anglais 8", "16.00/20");
        subjectMarks.put("Management", "13.75/20");
        subjectMarks.put("PFA", "14.50/20");

        // Add the first element with "Subject" type
        subjectTables.add(new SubjectTable("Semester Results", false, subjectMarks));

        // Add other elements with true type
        subjectTables.add(new SubjectTable("BIG DATA", true, Map.of(
                "Big Data: Hadoop Introduction", "18.00/20",
                "Big Data: MapReduce Algorithms", "19.00/20",
                "Big Data: Data Lakes", "16.50/20"
        )));

        subjectTables.add(new SubjectTable("Génie Logiciel", true, Map.of(
                "Software Engineering: UML Diagrams", "17.50/20",
                "Software Engineering: Design Patterns", "18.75/20",
                "Software Engineering: Agile Methodologies", "16.00/20"
        )));

        subjectTables.add(new SubjectTable("Entrepreneuriat", true, Map.of(
                "Entrepreneurship: Business Plan", "19.00/20",
                "Entrepreneurship: Marketing Strategy", "18.25/20",
                "Entrepreneurship: Startup Pitch", "17.50/20"
        )));

        subjectTables.add(new SubjectTable("Administration Windows", true, Map.of(
                "Windows Admin: User Management", "20.00/20",
                "Windows Admin: Server Configuration", "19.50/20",
                "Windows Admin: Security Settings", "18.00/20"
        )));

        subjectTables.add(new SubjectTable("Administration ORACLE 2", true, Map.of(
                "Oracle DB: SQL Queries", "17.00/20",
                "Oracle DB: Performance Tuning", "18.00/20",
                "Oracle DB: Backup and Recovery", "16.50/20"
        )));

        subjectTables.add(new SubjectTable("Communication professionnelle 2", true, Map.of(
                "Communication: Presentation Skills", "18.00/20",
                "Communication: Negotiation Techniques", "19.00/20",
                "Communication: Professional Writing", "17.25/20"
        )));

        subjectTables.add(new SubjectTable("Programmation Mobile", true, Map.of(
                "Mobile Programming: Android UI", "16.00/20",
                "Mobile Programming: Database Integration", "17.50/20",
                "Mobile Programming: Performance Optimization", "18.00/20"
        )));

        subjectTables.add(new SubjectTable("DEVOPS", true, Map.of(
                "DevOps: Git and Version Control", "19.00/20",
                "DevOps: Jenkins CI/CD", "18.75/20",
                "DevOps: Docker Containers", "17.50/20"
        )));

        subjectTables.add(new SubjectTable("Sécurité des Réseaux", true, Map.of(
                "Network Security: Firewall Configuration", "18.50/20",
                "Network Security: VPN Setup", "19.00/20",
                "Network Security: Intrusion Detection", "16.75/20"
        )));

        subjectTables.add(new SubjectTable("Virtualisation", true, Map.of(
                "Virtualization: Hyper-V Setup", "18.00/20",
                "Virtualization: VMware Configuration", "17.50/20",
                "Virtualization: Resource Allocation", "16.00/20"
        )));

        subjectTables.add(new SubjectTable("Datawarehouse", true, Map.of(
                "Data Warehouse: ETL Process", "19.00/20",
                "Data Warehouse: Data Modeling", "18.25/20",
                "Data Warehouse: OLAP vs OLTP", "17.50/20"
        )));

        subjectTables.add(new SubjectTable("J2EE1(Struts, Hibernate)", true, Map.of(
                "J2EE: Struts Framework", "17.75/20",
                "J2EE: Hibernate ORM", "19.00/20",
                "J2EE: Web Services", "16.50/20"
        )));

        subjectTables.add(new SubjectTable("Anglais 8", true, Map.of(
                "English: Grammar Exercises", "18.50/20",
                "English: Essay Writing", "19.00/20",
                "English: Vocabulary Test", "17.00/20"
        )));

        subjectTables.add(new SubjectTable("Management", true, Map.of(
                "Management: Leadership Styles", "20.00/20",
                "Management: Project Management", "19.25/20",
                "Management: Time Management", "17.50/20"
        )));

        subjectTables.add(new SubjectTable("PFA", true, Map.of(
                "PFA: Final Project Report", "18.00/20",
                "PFA: Presentation", "19.00/20",
                "PFA: Research and Analysis", "16.75/20"
        )));

        return subjectTables;
    }

}