package sk.upjs.ics.android.sweetcontroller;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

//http://blog.teamtreehouse.com/add-navigation-drawer-android ....navigation drawer
public class MakingFoodActivity extends ActionBarActivity implements DialogForAmount.NoticeDialogListener{
    public static final String FRAGMENT_TAG_RESULT = "FragmentWithResult";
    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //////////
setTitle("Make Your Meal");

        ///////////////
        Intent i = getIntent();
        String meal = i.getExtras().getString("meal");
        if (meal.equals("breakfast")) {
            setContentView(R.layout.activity_making_food);
            mDrawerList = (ListView)findViewById(R.id.navList);
            addDrawerItems();
            mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if (i==0) {
                        Intent intent = new Intent(view.getContext(), MakingFoodActivity.class);
                        intent.putExtra("meal", "breakfast");
                        startActivity(intent);
                    }
                    if (i==1) {
                        Intent intent = new Intent(view.getContext(), MakingFoodActivity.class);
                        intent.putExtra("meal", "lunch");
                        startActivity(intent);
                    }
                    if(i==2) {
                        Intent intent = new Intent(view.getContext(), MakingFoodActivity.class);
                        intent.putExtra("meal", "dinner");
                        startActivity(intent);
                    }
                    if (i==3) {
                        Intent intent = new Intent(view.getContext(), ChooseMealActivity.class);
                        //intent.putExtra("meal", "dinner");
                        startActivity(intent);
                    }
                    if (i==4) {
                        Intent intent = new Intent(view.getContext(), MenuBrowserActivity.class);
                        //intent.putExtra("meal", "dinner");
                        System.out.println("menu");
                        startActivity(intent);
                    }
                }
            });
           // FragmentWithGrid fragmentWithGrid=(FragmentWithGrid)getFragmentManager().findFragmentById(R.id.gridFragment);
//            FragmentWithGrid fragmentWithGrid = new FragmentWithGrid();
//            Bundle args = new Bundle();
//            args.putString("key", "showBreakfast");
//            fragmentWithGrid.setArguments(args);
//            FragmentManager fm = getFragmentManager();
//            fm.beginTransaction()
//                    //.replace(placeholder, fragment, tabId)
//                    .commit();

        }
        if (meal.equals("lunch")) {
            setContentView(R.layout.activity_making_lunch);
            mDrawerList = (ListView)findViewById(R.id.navList);
            addDrawerItems();
            mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if (i==0) {
                        Intent intent = new Intent(view.getContext(), MakingFoodActivity.class);
                        intent.putExtra("meal", "breakfast");
                        startActivity(intent);
                    }
                    if (i==1) {
                        Intent intent = new Intent(view.getContext(), MakingFoodActivity.class);
                        intent.putExtra("meal", "lunch");
                        startActivity(intent);
                    }
                    if(i==2) {
                        Intent intent = new Intent(view.getContext(), MakingFoodActivity.class);
                        intent.putExtra("meal", "dinner");
                        startActivity(intent);
                    }
                    if (i==3) {
                        Intent intent = new Intent(view.getContext(), ChooseMealActivity.class);
                        //intent.putExtra("meal", "dinner");
                        startActivity(intent);
                    }
                    if (i==4) {
                        Intent intent = new Intent(view.getContext(), MenuBrowserActivity.class);
                        //intent.putExtra("meal", "dinner");
                        System.out.println("menu");
                        startActivity(intent);
                    }
                }
            });

        }
        if (meal.equals("dinner")) {
            setContentView(R.layout.activity_making_dinner);
            mDrawerList = (ListView)findViewById(R.id.navList);
            addDrawerItems();
            mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if (i==0) {
                        Intent intent = new Intent(view.getContext(), MakingFoodActivity.class);
                        intent.putExtra("meal", "breakfast");
                        startActivity(intent);
                    }
                    if (i==1) {
                        Intent intent = new Intent(view.getContext(), MakingFoodActivity.class);
                        intent.putExtra("meal", "lunch");
                        startActivity(intent);
                    }
                    if(i==2) {
                        Intent intent = new Intent(view.getContext(), MakingFoodActivity.class);
                        intent.putExtra("meal", "dinner");
                        startActivity(intent);
                    }
                    if (i==3) {
                        Intent intent = new Intent(view.getContext(), ChooseMealActivity.class);
                        //intent.putExtra("meal", "dinner");
                        startActivity(intent);
                    }
                    if (i==4) {
                        Intent intent = new Intent(view.getContext(), MenuBrowserActivity.class);
                        //intent.putExtra("meal", "dinner");
                        System.out.println("menu");
                        startActivity(intent);
                    }
                }
            });
        }
    }
    private void addDrawerItems() {
        String[] osArray = { "Breakfast", "Lunch", "Dinner", "Favourite Meals", "Menu" };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_making_food, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.set_insulin) {
            Intent intent = new Intent(this, SetInsulinActivity2.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.set_notifications) {
            Intent intent = new Intent(this, PreferenceActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDialogPositiveClick(double amount, String meal) {

        FragmentWithResult fragmentWithResult=(FragmentWithResult)getFragmentManager().findFragmentById(R.id.resultFragment);
//        if(fragmentWithResult == null) {
//            fragmentWithResult = new FragmentWithResult();
//        }
    fragmentWithResult.doPositiveClick(amount, meal);

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}
