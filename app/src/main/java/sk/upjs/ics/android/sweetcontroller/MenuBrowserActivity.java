package sk.upjs.ics.android.sweetcontroller;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MenuBrowserActivity extends ActionBarActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_browser);
setTitle("Menu");
        final String[] regionNames = //getResources().getStringArray(R.array.mealNames);
                {"Kurací vývar s rezancami- 250 ml = 1 SJ", "Šošovicová polievka- 250 ml = 2 SJ", "Bravčové mäso s cestovinami (140 g) a omáčkou = 5 SJ", "Pstruh na masle so zemiakmi (150 g) = 3 SJ",
                        "Pečené kura s ryžou (100 g) = 2 SJ", "Rizoto s bravčovým mäsom a syrom (130 g) = 3 SJ"};
        this.viewPager = (ViewPager) findViewById(R.id.regionViewPager);
        this.viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return MenuBrowserFragment.newInstance(regionNames[i]);
            }

            @Override
            public int getCount() {
                return regionNames.length;
            }
        });
    }

}
