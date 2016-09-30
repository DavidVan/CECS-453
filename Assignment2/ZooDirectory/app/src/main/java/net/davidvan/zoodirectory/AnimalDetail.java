package net.davidvan.zoodirectory;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by David on 9/30/2016.
 */

public class AnimalDetail extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_detail);

        Bundle myInput = this.getIntent().getExtras();

        ImageView image = (ImageView) findViewById(R.id.animal_image_detail);
        TextView name = (TextView) findViewById(R.id.animal_name_detail);
        TextView description = (TextView) findViewById(R.id.animal_description_detail);

        try {
            InputStream inputStream = getAssets().open(myInput.getString("Image"));
            Drawable imageDrawable = Drawable.createFromStream(inputStream, null);
            image.setImageDrawable(imageDrawable);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        name.setText(myInput.getString("Name"));
        description.setText(myInput.getString("Description"));
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
