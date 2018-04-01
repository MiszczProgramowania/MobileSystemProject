package com.example.root.mobilesystemproject2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.root.mobilesystemproject2.entity.TaskEntity;

import java.util.List;

public class TaskListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        registerAddNewTaskOnAddFloatingButton();
//        TaskEntity.deleteAll(TaskEntity.class);
        fillTable();
    }

    private void registerAddNewTaskOnAddFloatingButton() {
        final TaskListActivity self = this;
        getAddFloatingButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(self, TaskDetailActivity.class);
                startActivityForResult(
                        intent,
                        6
                );
//                TaskEntity taskEntity = new TaskEntity(
//                        new Date(),
//                        "name",
//                        new Date(),
//                        TaskPriorityEnum.MEDIUM,
//                        "description"
//                );
//                taskEntity.save();
//                createTableContent(taskEntity);
            }
        });
    }

    private FloatingActionButton getAddFloatingButton() {
        return (FloatingActionButton) findViewById(R.id.fab);
    }

    private void fillTable() {
        getTableContainer().removeAllViews();
        createTableHeader();
        List<TaskEntity> taskEntities = TaskEntity.listAll(TaskEntity.class);
        for(TaskEntity taskEntity: taskEntities) {
            createTableContent(taskEntity);
        }
    }

    private void createTableHeader() {
        TableRow row = createTableRow();
        createTextInRow(row, "Data dodania");
        createTextInRow(row, "Nazwa");
        createTextInRow(row, "Termin zakończenia");
        createTextInRow(row, "Priorytet");
        createTextInRow(row, "Usuń");
        getTableContainer().addView(row);
    }


    private void createTableContent(TaskEntity taskEntity) {
        TableRow row = createTableRow();
        createTextInRow(row, taskEntity.getAddDate().toString());
        createTextInRow(row, taskEntity.getName());
        createTextInRow(row, taskEntity.getEndDate().toString());
        createTextInRow(row, taskEntity.getPriority().toString());
        createRemoveButtonInRow(row, taskEntity);
        getTableContainer().addView(row);
        getTableContainer().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDetailTaskActivity();
            }
        });
    }

    private void openDetailTaskActivity() {
        startActivityForResult(
                new Intent(this, TaskDetailActivity.class),
                6
        );
    }

    private void createRemoveButtonInRow(final TableRow row, final TaskEntity taskEntity) {
        Button remove = new Button(this);
        remove.setPadding(10,10,10,10);
        remove.setText("Usuń");
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskEntity.delete();
                removeTableContent(row);
            }
        });
        row.addView(remove);
    }

    private void removeTableContent(TableRow row) {

        getTableContainer().removeView(row);
    }

    private void createTextInRow(TableRow row, String text) {
        TextView qty = new TextView(this);
        qty.setPadding(10,10,10,10);
        qty.setText(text);
        row.addView(qty);
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
