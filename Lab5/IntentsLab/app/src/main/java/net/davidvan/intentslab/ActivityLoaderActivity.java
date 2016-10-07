package net.davidvan.intentslab;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityLoaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);

        Button webBrowser = (Button) findViewById(R.id.web_browser);
        Button makeCall = (Button) findViewById(R.id.make_calls);

        webBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String website = "http://www.amazon.com";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(website));
                Intent chooser = Intent.createChooser(intent, "Load " + website + " with:");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(chooser);
                }
            }
        });

        makeCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "tel:+194912344444";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(phoneNumber));
                startActivity(intent);
            }
        });

    }
}
