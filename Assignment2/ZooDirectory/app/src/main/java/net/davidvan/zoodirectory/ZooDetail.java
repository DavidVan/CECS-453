package net.davidvan.zoodirectory;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Created by David on 9/30/2016.
 */

public class ZooDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoo_detail);

        TextView call = (TextView) findViewById(R.id.zoo_call_detail);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = "+18888888888";
                Intent call = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(call);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_animal_listing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.information) {
            Intent information = new Intent(this, ZooDetail.class);
            startActivity(information);
            return true;
        }
        else if (id == R.id.uninstall) {
            Intent uninstall = new Intent(Intent.ACTION_DELETE, Uri.parse("package:net.davidvan.zoodirectory"));
            startActivity(uninstall);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
