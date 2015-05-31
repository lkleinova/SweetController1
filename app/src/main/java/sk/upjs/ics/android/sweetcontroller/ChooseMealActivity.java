package sk.upjs.ics.android.sweetcontroller;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class ChooseMealActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_meal);
        setTitle("Choose Meal");

    }

    public void makeBreakfast(View view) {
        Toast.makeText(this, "breakfast", Toast.LENGTH_SHORT)
                .show();
        Intent intent = new Intent(this, MakingFoodActivity.class);
        intent.putExtra("meal", "breakfast");
        startActivity(intent);
    }

    public void makeLunch(View view) {
        Toast.makeText(this, "lunch", Toast.LENGTH_SHORT)
                .show();
        Intent intent = new Intent(this, MakingFoodActivity.class);
        intent.putExtra("meal", "lunch");
        startActivity(intent);
    }

    public void makeDinner(View view) {
        Toast.makeText(this, "dinner", Toast.LENGTH_SHORT)
                .show();
        Intent intent = new Intent(this, MakingFoodActivity.class);
        intent.putExtra("meal", "dinner");
        startActivity(intent);
    }

    public void takePhotoBreakfast(View v) {
        Intent intent = new Intent(this, PhotoBreakfastActivity.class);
        startActivity(intent);
    }

    public void takePhotoLunch(View v) {
        Intent intent = new Intent(this, PhotoLunchActivity.class);
        startActivity(intent);
    }

    public void takePhotoDinner(View v) {
        Intent intent = new Intent(this, PhotoDinnerActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choose_meal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
