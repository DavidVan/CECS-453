package net.davidvan.languagesupportandscreenrotationlab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by David on 10/14/2016.
 */

public class GetName extends AppCompatActivity implements View.OnClickListener {

    EditText name;
    Button button;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.name_getter);
        name = (EditText) this.findViewById(R.id.editText1);
        button = (Button) this.findViewById(R.id.button1);

        button.setOnClickListener(this);
    }

    public void onClick(View arg0) {
        Intent myIntent = new Intent(this, MainActivity.class);
        myIntent.putExtra("userName", name.getText().toString());
        this.startActivity(myIntent);
    }

}