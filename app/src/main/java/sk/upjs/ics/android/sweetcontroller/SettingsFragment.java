package sk.upjs.ics.android.sweetcontroller;

import android.app.FragmentManager;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;

/**
 * Created by Lenka on 28. 5. 2015.
 */
public class SettingsFragment extends PreferenceFragment {

    public  static boolean switched=true;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings);
        SwitchPreference switchPreference = (SwitchPreference)findPreference("NotificationOnOff");
        //switchPreference.setChecked(true);
        switchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                switched = !((SwitchPreference) preference)
                        .isChecked();
                System.out.println("switch"+switched);

                return true;
            }
        });
    }
}
