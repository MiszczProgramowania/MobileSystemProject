package com.example.root.mobilesystemproject2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class TaskDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
    }

    private EditText getName() {
        return findViewById(R.id.name);
    }

    private EditText getEndDate() {
        return findViewById(R.id.endDate);
    }

    private Spinner getPriority() {
        return findViewById(R.id.priority_spinner);
    }

    private EditText getDescription() {
        return findViewById(R.id.description);
    }

    private Button getSaveButton() {
        return findViewById(R.id.saveButton);
    }
}
