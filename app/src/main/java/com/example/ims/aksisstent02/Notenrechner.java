package ch.rechner.aksistent.notenrechner_2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.util.Log;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.List;

import android.view.inputmethod.InputMethodManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.widget.AbsListView;


public class Notenrechner extends AppCompatActivity {

    public static final String LOG_TAG = Notenrechner.class.getSimpleName();

    private MarkListDataSource dataSource;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notenrechner);

        Log.d(LOG_TAG, "Das Datenquellen-Objekt wird angelegt.");
        dataSource = new MarkListDataSource(this);

        activateAddButton();
        initializeContextualActionBar();
    }

    private void showAllListEntries () {
        List<MarkList> markListList = dataSource.getAllMarkLists();

        ArrayAdapter<MarkList> markListArrayAdapter = new ArrayAdapter<> (
                this,
                android.R.layout.simple_list_item_multiple_choice,
                markListList);

        ListView markListListView = (ListView) findViewById(R.id.listview_mark_lists);
        markListListView.setAdapter(markListArrayAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(LOG_TAG, "Die Datenquelle wird geöffnet.");
        dataSource.open();

        Log.d(LOG_TAG, "Folgende Einträge sind in der Datenbank vorhanden:");
        showAllListEntries();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(LOG_TAG, "Die Datenquelle wird geschlossen.");
        dataSource.close();
    }

    private void activateAddButton() {
        Button buttonAddMark = (Button) findViewById(R.id.button_add_mark);
        final EditText editTextSubject = (EditText) findViewById(R.id.editText_subject);
        final EditText editTextMark = (EditText) findViewById(R.id.editText_mark);
        final EditText editTextDesc = (EditText) findViewById(R.id.editText_desc);

        buttonAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mark = editTextMark.getText().toString();
                String subject = editTextSubject.getText().toString();
                String desc = editTextDesc.getText().toString();

                if(TextUtils.isEmpty(subject)) {
                    editTextSubject.setError(getString(R.string.editText_errorMessage));
                    return;
                }
                if(TextUtils.isEmpty(mark)) {
                    editTextMark.setError(getString(R.string.editText_errorMessage));
                    return;
                }
                if(TextUtils.isEmpty(desc)) {
                    editTextDesc.setError(getString(R.string.editText_errorMessage));
                    return;
                }


                editTextSubject.setText("");
                editTextMark.setText("");
                editTextDesc.setText("");

                dataSource.createMarkList(mark, subject, desc);

                InputMethodManager inputMethodManager;
                inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if(getCurrentFocus() != null) {
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }

                showAllListEntries();
            }
        });

    }

    private void initializeContextualActionBar() {

        final ListView markListListView = (ListView) findViewById(R.id.listview_mark_lists);
        markListListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        markListListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                getMenuInflater().inflate(R.menu.menu_contextual_action_bar, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.cab_delete:
                        SparseBooleanArray touchedMarkListPositions = markListListView.getCheckedItemPositions();
                        for (int i=0; i < touchedMarkListPositions.size(); i++) {
                            boolean isChecked = touchedMarkListPositions.valueAt(i);
                            if(isChecked) {
                                int postitionInListView = touchedMarkListPositions.keyAt(i);
                                MarkList markList = (MarkList) markListListView.getItemAtPosition(postitionInListView);
                                Log.d(LOG_TAG, "Position im ListView: " + postitionInListView + " Inhalt: " + markList.toString());
                                dataSource.deleteMarkList(markList);
                            }
                        }
                        showAllListEntries();
                        mode.finish();
                        return true;

                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notenrechner, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
