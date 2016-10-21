package net.davidvan.languagesupportandscreenrotationlab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle myInput = this.getIntent().getExtras();

        TextView myText;
        myText = (TextView) this.findViewById(R.id.textView1);
        myText.setText(getString(R.string.hello) + myInput.getString("userName"));
    }
}
