package net.davidvan.zoodirectory;

import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AnimalListing extends AppCompatActivity implements ScaryAnimalAlertFragment.ScaryAnimalAlertListener {

    private List<Animal> animals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_listing);

        ListView list = (ListView) findViewById(R.id.animal_list);

        animals = new ArrayList<Animal>();

        animals.add(new Animal("Giant Panda", "Giant Pandas eat bamboo!", "Panda.jpg"));
        animals.add(new Animal("Humming Bird", "Humming Birds can flaps their wings 10-15 times a second!", "HummingBird.jpg"));
        animals.add(new Animal("Red Fox", "Foxes are clever, sneaky little creatures.", "RedFox.jpg"));
        animals.add(new Animal("Red Squirrel", "These pointy ears can kill!", "RedSquirrel.jpg"));
        animals.add(new Animal("Red Panda", "A red panda isn't closely related to the Giant Panda!", "RedPanda.jpg"));

        list.setAdapter(new AnimalAdapter(this, R.layout.animal_row, animals));

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == animals.size() - 1) {
                    ScaryAnimalAlertFragment alert = new ScaryAnimalAlertFragment();
                    alert.show(getFragmentManager(), "showScaryAnimal");
                }
                else {
                    Intent intent = new Intent(view.getContext(), AnimalDetail.class);
                    intent.putExtra("Name", animals.get(position).getName());
                    intent.putExtra("Description", animals.get(position).getDescription());
                    intent.putExtra("Image", animals.get(position).getImage());
                    startActivity(intent);
                }
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

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Intent intent = new Intent(this, AnimalDetail.class);
        intent.putExtra("Name", animals.get(animals.size() - 1).getName());
        intent.putExtra("Description", animals.get(animals.size() - 1).getDescription());
        intent.putExtra("Image", animals.get(animals.size() - 1).getImage());
        startActivity(intent);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // Do nothing!
    }

}
