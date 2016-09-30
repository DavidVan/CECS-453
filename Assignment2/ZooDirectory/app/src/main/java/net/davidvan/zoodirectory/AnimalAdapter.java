package net.davidvan.zoodirectory;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by David on 9/29/2016.
 */

public class AnimalAdapter extends ArrayAdapter<Animal> {

    private List<Animal> animals;

    public AnimalAdapter(Context context, int resource, List<Animal> animals) {
        super(context, resource, animals);
        this.animals = animals;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Animal animal = animals.get(position);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.animal_row, null);

        TextView animalName = (TextView) row.findViewById(R.id.animal_name_row);
        animalName.setText(animal.getName());

        try {
            ImageView animalImage = (ImageView) row.findViewById(R.id.animal_image_row);
            InputStream inputStream = getContext().getAssets().open(animal.getImage());
            Drawable image = Drawable.createFromStream(inputStream, null);
            animalImage.setImageDrawable(image);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return row;
    }

}
