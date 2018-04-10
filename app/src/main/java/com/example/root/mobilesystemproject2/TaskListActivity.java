package com.example.root.mobilesystemproject2;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.annimon.stream.Stream;
import com.example.root.mobilesystemproject2.entity.TaskEntity;
import com.example.root.mobilesystemproject2.notify.ScheduledNotifyHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class TaskListActivity extends AppCompatActivity {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private String sortBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScheduledNotifyHelper.scheduleNotification(
                ScheduledNotifyHelper.getNotification(
                        "5 second delay",this
                ),
                5000,
                this
        );
        setContentView(R.layout.activity_task_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        registerAddNewTaskOnAddFloatingButton();
//        TaskEntity.deleteAll(TaskEntity.class);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        fillTable();
    }

    private void registerAddNewTaskOnAddFloatingButton() {
        final TaskListActivity self = this;
        getAddFloatingButton().setOnClickListener(view -> {
            Intent intent = new Intent(self, TaskDetailActivity.class);
            startActivityForResult(
                    intent,
                    6
            );
        });
    }

    private FloatingActionButton getAddFloatingButton() {
        return (FloatingActionButton) findViewById(R.id.fab);
    }

    private void fillTable() {
        getTableContainer().removeAllViews();
        createTableHeader();
        List<TaskEntity> taskEntities;
        try {
            taskEntities = TaskEntity.listAll(TaskEntity.class);
        }
        catch (SQLiteException e){
            Log.e("SQL",e.toString());
            taskEntities = new ArrayList<>();
        }

        if(sortBy != null) {

                switch (sortBy) {
                    case "Dodano":
                        taskEntities = Stream.of(taskEntities).sortBy((e) ->e.getAddDate().getTime()).toList();
                        break;
                    case "Nazwa":
                        taskEntities = Stream.of(taskEntities).sortBy(TaskEntity::getName).toList();
                        break;
                    case "Zakończenie":
                        taskEntities = Stream.of(taskEntities).sortBy((e) ->e.getEndDate().getTime()).toList();
                        break;
                    case "Priorytet":
                        taskEntities = Stream.of(taskEntities).sortBy(TaskEntity::getPriority).toList();
                        break;
                }
        }

        for(TaskEntity taskEntity: taskEntities) {
            createTableContent(taskEntity);
        }
    }

    private void createTableHeader() {
        TableRow row = createTableRow();
        createTextInRow(row, "Dodano")
            .setOnClickListener( (e) -> setSortBy("Dodano"));
        createTextInRow(row, "Nazwa")
                .setOnClickListener( (e) -> setSortBy("Nazwa"));
        createTextInRow(row, "Zakończenie")
                .setOnClickListener( (e) -> setSortBy("Zakończenie"));
        createTextInRow(row, "Priorytet")
                .setOnClickListener( (e) -> setSortBy("Priorytet"));
        createTextInRow(row, "Edytuj");
        createTextInRow(row, "Oznacz");
        getTableContainer().addView(row);
    }

    private void setSortBy(String bywhat) {
        sortBy = bywhat;
        getTableContainer().removeAllViews();
        fillTable();
    }


    private void createTableContent(final TaskEntity taskEntity) {
        TableRow row = createTableRow();
        createTextInRow(row, dateFormat.format(taskEntity.getAddDate()));
        createTextInRow(row, taskEntity.getName());
        createTextInRow(row, dateFormat.format(taskEntity.getEndDate()));
        createTextInRow(row, taskEntity.getPriority().toString());
        createDoneButtonInRow(row, taskEntity);
        createEditButtonInRow(row, taskEntity);
        getTableContainer().addView(row);
    }

    private void openDetailTaskActivity(TaskEntity taskEntity) {
        Intent intent = new Intent(this, TaskDetailActivity.class);
        intent.putExtra("TaskEntityId", taskEntity.getId());
        startActivity(intent);
    }

    private void createDoneButtonInRow(final TableRow row, final TaskEntity taskEntity) {
        Button mark = new Button(this);
        mark.setPadding(10,10,10,10);
        updateMarkValue(taskEntity, mark);
        mark.setOnClickListener(view -> {
            taskEntity.setDone(!taskEntity.isDone());
            taskEntity.save();
            updateMarkValue(taskEntity, (Button) view);
        });
        row.addView(mark);
    }

    private void createEditButtonInRow(final TableRow row, final TaskEntity taskEntity) {
        Button edit = new Button(this);
        edit.setText("Edytuj");
        edit.setPadding(10,10,10,10);
        edit.setOnClickListener((view) -> {
            openDetailTaskActivity(taskEntity);
        });
        row.addView(edit);
    }

    private void updateMarkValue(TaskEntity taskEntity, Button remove) {
        if(taskEntity.isDone()) { remove.setText("Zrobione"); }
        else { remove.setText("Nie zrobione");}
        ;
    }

    private void removeTableContent(TableRow row) {

        getTableContainer().removeView(row);
    }

    private TextView createTextInRow(TableRow row, String text) {
        TextView qty = new TextView(this);
        qty.setPadding(10,10,10,10);
        qty.setText(text);
        row.addView(qty);
        return qty;
    }

    @NonNull
    private TableRow createTableRow() {
        TableRow row= new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);
        return row;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_task_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();//noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public TableLayout getTableContainer() {
        return findViewById(R.id.tableContainer);
    }
}
