package net.davidvan.lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Lab1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab1);

        Bundle myInput = this.getIntent().getExtras();

        TextView myText;
        myText = (TextView) this.findViewById(R.id.textView1);
        myText.setText("Hello " + myInput.getString("userName"));
    }
}
