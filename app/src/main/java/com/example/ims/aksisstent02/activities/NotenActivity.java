package com.example.ims.aksisstent02.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.ims.aksisstent02.R;
import com.example.ims.aksisstent02.objects.MarkList;
import com.example.ims.aksisstent02.services.MarkListDataSource;

import java.util.List;


public class NotenActivity extends AppCompatActivity {

    public static final String LOG_TAG = NotenActivity.class.getSimpleName();
    MarkListDataSource dataSource;
    InputMethodManager inputMethodManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noten);

        Log.d(LOG_TAG, "Das Datenquellen-Objekt wird angelegt.");
        dataSource = new MarkListDataSource(this);

        Button buttonAddMark = (Button) findViewById(R.id.btnAdd);
        final EditText editTextSubject = (EditText) findViewById(R.id.txtSubject);
        final EditText editTextMark = (EditText) findViewById(R.id.txtMark);
        final EditText editTextDesc = (EditText) findViewById(R.id.txtDesc);

        buttonAddMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mark = editTextMark.getText().toString();
                String subject = editTextSubject.getText().toString();
                String desc = editTextDesc.getText().toString();

                if (TextUtils.isEmpty(subject)) {
                    editTextSubject.setError(getString(R.string.editText_errorMessage));
                    return;
                }
                if (TextUtils.isEmpty(mark)) {
                    editTextMark.setError(getString(R.string.editText_errorMessage));
                    return;
                }
                if (TextUtils.isEmpty(desc)) {
                    editTextDesc.setError(getString(R.string.editText_errorMessage));
                    return;
                }


                editTextSubject.setText("");
                editTextMark.setText("");
                editTextDesc.setText("");

                dataSource.createMarkList(mark, subject, desc);


                inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (getCurrentFocus() != null) {
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }

                showAllListEntries();
            }
        });
        initializeContextualActionBar();
    }

    private void showAllListEntries() {
        List<MarkList> markListList = dataSource.getAllMarkLists();

        ArrayAdapter<MarkList> markListArrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                markListList);

        ListView markListListView = (ListView) findViewById(R.id.listView);
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


    }

    private void initializeContextualActionBar() {

        final ListView markListListView = (ListView) findViewById(R.id.listView);
        markListListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        markListListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {


            int selCount = 0;

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                if (checked) {
                    selCount++;
                } else {
                    selCount--;
                }
                String cabTitle = selCount + " " + getString(R.string.cab_checked_string);
                mode.setTitle(cabTitle);
                mode.invalidate();
            }


            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                getMenuInflater().inflate(R.menu.menu_contextual_action_bar, menu);
                return true;
            }


            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                MenuItem item = menu.findItem(R.id.cab_change);
                if (selCount == 1) {
                    item.setVisible(true);
                } else {
                    item.setVisible(false);
                }

                return true;
            }


            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                boolean returnValue = true;
                SparseBooleanArray touchedMarkListPositions = markListListView.getCheckedItemPositions();

                switch (item.getItemId()) {
                    case R.id.cab_delete:
                        for (int i = 0; i < touchedMarkListPositions.size(); i++) {
                            boolean isChecked = touchedMarkListPositions.valueAt(i);
                            if (isChecked) {
                                int postitionInListView = touchedMarkListPositions.keyAt(i);
                                MarkList markList = (MarkList) markListListView.getItemAtPosition(postitionInListView);
                                Log.d(LOG_TAG, "Position im ListView: " + postitionInListView + " Inhalt: " + markList.toString());
                                dataSource.deleteMarkList(markList);
                            }
                        }
                        showAllListEntries();
                        mode.finish();
                        break;

                    case R.id.cab_change:
                        Log.d(LOG_TAG, "Eintrag ändern");
                        for (int i = 0; i < touchedMarkListPositions.size(); i++) {
                            boolean isChecked = touchedMarkListPositions.valueAt(i);
                            if (isChecked) {
                                int postitionInListView = touchedMarkListPositions.keyAt(i);
                                MarkList markList = (MarkList) markListListView.getItemAtPosition(postitionInListView);
                                Log.d(LOG_TAG, "Position im ListView: " + postitionInListView + " Inhalt: " + markList.toString());


                                // POSSIBLY WRONG ----> maybe (createEditMarkListDialog(markList)
                                AlertDialog ediitMarkListDialog = createEditMarkList(markList);
                                ediitMarkListDialog.show();

                            }
                        }

                        mode.finish();
                        break;

                    default:
                        returnValue = false;
                        break;
                }
                return returnValue;
            }


            @Override
            public void onDestroyActionMode(ActionMode mode) {
                selCount = 0;
            }

        });
    }


    private AlertDialog createEditMarkList(final MarkList markList) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        View dialogsView = inflater.inflate(R.layout.dialog_edit_mark_list, null);

        final EditText editTextNewMark = (EditText) dialogsView.findViewById(R.id.txtMark);
        editTextNewMark.setText(String.valueOf(markList.getMark()));

        final EditText editTextNewSubject = (EditText) dialogsView.findViewById(R.id.txtSubject);
        editTextNewSubject.setText(markList.getSubject());

        final EditText editTextNewDesc = (EditText) dialogsView.findViewById(R.id.txtDesc);
        editTextNewDesc.setText(markList.getDesc());

        builder.setView(dialogsView)
                .setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.dialog_button_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String mark = editTextNewMark.getText().toString();
                        String subject = editTextNewSubject.getText().toString();
                        String desc = editTextNewDesc.getText().toString();

                        if ((TextUtils.isEmpty(mark)) || (TextUtils.isEmpty(desc)) || (TextUtils.isEmpty(subject))) {
                            Log.d(LOG_TAG, "Ein Eintrag enthielt keinen Text. Daher Abbruch der Änderung.");
                            return;
                        }

                        int quantity = Integer.parseInt(mark);

                        // An dieser Stelle schreiben wir die geänderten Daten in die SQLite Datenbank
                        MarkList updatedMarkList = dataSource.updateMarkList(markList.getId(), desc, subject, mark);

                        Log.d(LOG_TAG, "Alter Eintrag - ID: " + markList.getId() + " Inhalt: " + markList.toString());
                        Log.d(LOG_TAG, "Neuer Eintrag - ID: " + updatedMarkList.getId() + " Inhalt: " + updatedMarkList.toString());

                        showAllListEntries();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.dialog_button_negative, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        return builder.create();
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
