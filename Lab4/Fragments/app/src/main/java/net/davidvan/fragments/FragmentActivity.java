package net.davidvan.fragments;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // WindowManager wm = getWindowManager();
        // Display d = wm.getDefaultDisplay();
        // Avoid using deprecated d.getWidth() and d.getHeight() methods!
        DisplayMetrics display = this.getResources().getDisplayMetrics();
        int width = display.widthPixels;
        int height = display.heightPixels;
        if (width > height) {
            Fragment1 fragment1 = new Fragment1();
            fragmentTransaction.replace(android.R.id.content, fragment1);
        }
        else {
            Fragment2 fragment2 = new Fragment2();
            fragmentTransaction.replace(android.R.id.content, fragment2);
        }

        fragmentTransaction.commit();

        setContentView(R.layout.activity_main);
    }
}
