package net.davidvan.junittestlab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private RadioGroup radioGroup;
    private RadioButton celsiusButton;
    private RadioButton fahrenheitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.button:
                celsiusButton = (RadioButton) findViewById(R.id.toCelsius);
                fahrenheitButton = (RadioButton) findViewById(R.id.toFahrenheit);
                if (editText.getText().length() == 0) {
                    Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_LONG).show();
                    return;
                }
                float inputValue = Float.parseFloat(editText.getText().toString());
                if (celsiusButton.isChecked()) {
                    editText.setText(String.valueOf(ConverterUtil.convertFahrenheitToCelsius(inputValue)));
                }
                else {
                    editText.setText(String.valueOf(ConverterUtil.convertCelsiusToFahrenheit(inputValue)));
                }
                break;
        }
    }

}