package net.davidvan.sqlitedatabase;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // Spinner element
    Spinner spinner;

    // Add button
    Button btnAdd;

    // Input text EditText
    EditText inputLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Spinner element
        spinner = (Spinner) findViewById(R.id.spinner);

        // Add button
        btnAdd = (Button) findViewById(R.id.btn_add);

        // New input label field
        inputLabel = (EditText) findViewById(R.id.input_label);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Loading spinner data from database
        loadSpinnerData();

        // Add new label button click listener
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String label = inputLabel.getText().toString();

                if (label.trim().length() > 0) {
                    // Database handler
                    DatabaseHandler db = new DatabaseHandler(getApplicationContext());

                    // Inserting new label into database
                    db.insertLabel(label);

                    // Clear input field text
                    inputLabel.setText("");

                    // Hiding the keyboard
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(inputLabel.getWindowToken(), 0);

                    // Loading spinner with newly loaded data
                    loadSpinnerData();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please enter a label name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Function to lead the spinner data from SQLite database
    private void loadSpinnerData() {
        // Database handler
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());

        // Spinner dropdown elements
        List<String> labels = db.getAllLabels();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, labels);

        // Dropdown layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String label = parent.getItemAtPosition(position).toString();

        // SHowing selected spinner item
        Toast.makeText(parent.getContext(), "You selected: " + label, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Nothing needed here.
    }
}
