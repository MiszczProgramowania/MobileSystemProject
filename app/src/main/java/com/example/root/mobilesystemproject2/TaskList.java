package com.example.root.mobilesystemproject2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import static android.view.View.inflate;

public class TaskList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fillTable();;
    }

    private void fillTable() {
        createTableHeader();

    }

    private void createTableHeader() {
        TableRow row = createTableRow();
        createTextInRow(row, "Data dodania");
        createTextInRow(row, "Nazwa");
        createTextInRow(row, "Termin zakończenia");
        createTextInRow(row, "Priorytet");
        getTableContainer().addView(row);
    }


    private void createTableContent() {
        TableRow row = createTableRow();
        createTextInRow(row, "Data dodania");
        createTextInRow(row, "Nazwa");
        createTextInRow(row, "Termin zakończenia");
        createTextInRow(row, "Priorytet");
        getTableContainer().addView(row);
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
