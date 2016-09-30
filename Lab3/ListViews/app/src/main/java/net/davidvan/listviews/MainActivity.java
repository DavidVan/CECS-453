package net.davidvan.listviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private SimpleAdapter simpleAdapter;
    private List<Map<String, String>> planetList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initList();

        listView = (ListView) findViewById(R.id.list_view);

        simpleAdapter = new SimpleAdapter(this, planetList, android.R.layout.simple_list_item_1, new String[] {"planet"}, new int[] {android.R.id.text1});

        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView clickedView = (TextView) view;

                Toast.makeText(MainActivity.this, "Item with id [" + id + "] - Position [" + position + "] - Planet [" + clickedView.getText() + "]", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void initList() {
        planetList.add(createPlanet("planet", "Mercury"));
        planetList.add(createPlanet("planet", "Venus"));
        planetList.add(createPlanet("planet", "Mars"));
        planetList.add(createPlanet("planet", "Jupiter"));
        planetList.add(createPlanet("planet", "Saturn"));
        planetList.add(createPlanet("planet", "Uranus"));
        planetList.add(createPlanet("planet", "Neptune"));
    }

    private HashMap<String, String> createPlanet(String key, String value) {
        HashMap<String, String> planet = new HashMap<>();
        planet.put(key, value);
        return planet;
    }

}
