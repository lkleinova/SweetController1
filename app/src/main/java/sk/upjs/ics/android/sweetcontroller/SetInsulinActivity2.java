package sk.upjs.ics.android.sweetcontroller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;


public class SetInsulinActivity2 extends ActionBarActivity {
    ListView mealsListView;
    public static  TextView breakfastSettingsTextView;
   public static  TextView lunchSettingsTextView;
    public static TextView dinnerSettingsTextView;
private boolean resume=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_set_insulin_activity2);
        setTitle("Set Insulin");
        mealsListView = (ListView) findViewById(R.id.mealsListView);

        List<String> meals = Arrays.asList("Change Insulin for Breakfast", "Change Insulin for Lunch", "Change Insulin for Dinner");
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, meals);

        mealsListView.setAdapter(listAdapter);
        breakfastSettingsTextView = (TextView)findViewById(R.id.breakfastSettingsTextView);
       breakfastSettingsTextView.setText("Breakfast: 1");//ze na 1 SJ sa picha 1 inz. jednotka

        lunchSettingsTextView = (TextView) findViewById(R.id.lunchSettingsTextView);
        lunchSettingsTextView.setText("Lunch: 1");

        dinnerSettingsTextView = (TextView) findViewById(R.id.dinnerSettingsTextView);
        dinnerSettingsTextView.setText("Dinner: 1");

        if (savedInstanceState!=null) {
            breakfastSettingsTextView.setText((String)savedInstanceState.get("breakfast"));
            lunchSettingsTextView.setText((String)savedInstanceState.get("lunch"));
            dinnerSettingsTextView.setText((String)savedInstanceState.get("dinner"));

        }

        mealsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0) {
                    Intent intent = new Intent(view.getContext(), SetInsulinForBreakfastActivity.class);
                    startActivityForResult(intent, 1);
                }
                if (i==1) {
                    Intent intent = new Intent(view.getContext(), SetInsulinForBreakfastActivity.class);
                    startActivityForResult(intent, 2);
                }
                if (i==2) {
                    Intent intent = new Intent(view.getContext(), SetInsulinForBreakfastActivity.class);
                    startActivityForResult(intent, 3);
                }
            }
        });







    }

    @Override
    protected void onPause() {
        getPreferences().edit()
                .putString("breakfastState", breakfastSettingsTextView.getText().toString())
                .commit();
        getPreferences().edit()
                .putString("lunchState", lunchSettingsTextView.getText().toString())
                .commit();
        getPreferences().edit()
                .putString("dinnerState", dinnerSettingsTextView.getText().toString())
                .commit();

        super.onPause();
    }

    private SharedPreferences getPreferences() {
        return this
                .getPreferences(SetInsulinActivity2.MODE_PRIVATE);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(resume) {
            String breakfastState = getPreferences().getString("breakfastState", "Breakfast: 1");
            breakfastSettingsTextView.setText(breakfastState);

            String lunchState = getPreferences().getString("lunchState", "Lunch: 1");
            lunchSettingsTextView.setText(lunchState);

            String dinnerState = getPreferences().getString("dinnerState", "Dinner: 1");
            dinnerSettingsTextView.setText(dinnerState);

        }
        resume=true;
    }

    public void start(View v) {
        Intent intent = new Intent(this, ChooseMealActivity.class);
        //intent.putExtra("meal", "breakfast");
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        resume=false;
        if(requestCode==1) {
            if(resultCode == RESULT_OK){
                breakfastSettingsTextView.setText("Breakfast: "+data.getStringExtra("result"));
            }
        }
        if(requestCode==2) {
            if(resultCode == RESULT_OK){
                lunchSettingsTextView.setText("Lunch: "+data.getStringExtra("result"));
            }
        }
        if(requestCode==3) {
            if(resultCode == RESULT_OK){
                dinnerSettingsTextView.setText("Dinner: "+data.getStringExtra("result"));
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("breakfast", (String)breakfastSettingsTextView.getText());
        outState.putString("lunch", (String)lunchSettingsTextView.getText());
        outState.putString("dinner", (String)dinnerSettingsTextView.getText());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_set_insulin_activity2, menu);
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
