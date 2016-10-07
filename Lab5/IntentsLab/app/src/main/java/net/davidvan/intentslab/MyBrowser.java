package net.davidvan.intentslab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by David on 9/30/2016.
 */

public class MyBrowser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_browser);

        TextView browserURL = (TextView) findViewById(R.id.web_browser_url);
        browserURL.setText(getIntent().getDataString());

    }

}
