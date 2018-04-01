package com.example.root.mobilesystemproject2;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.Date;

public class TaskDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        registerDatePicker();
    }

    private void registerDatePicker() {
        final TaskDetailActivity self = this;
        getEndDate().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog =
                        new DatePickerDialog(
                                self,
                                new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker datePicker, int yeat, int month, int day) {
                                        Date d = new Date(yeat, month, day);
                                        getEndDate().setText(d.toString());
                                    }
                                },
                                mYear,
                                mMonth,
                                mDay
                        );
                dialog.show();
            }
        });
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
