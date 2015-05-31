package sk.upjs.ics.android.sweetcontroller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Lenka on 28. 5. 2015.
 */
public class MenuBrowserFragment extends android.support.v4.app.Fragment {
public static final String ARG_REGION_NAME = "RegionName";
private TextView regionDetailTextView;

public static MenuBrowserFragment newInstance(String regionName) {
        Bundle args = new Bundle();
        args.putString(ARG_REGION_NAME, regionName);

        MenuBrowserFragment regionDetailFragment = new MenuBrowserFragment();
        regionDetailFragment.setArguments(args);

        return regionDetailFragment;
        }

@Nullable
@Override
public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu_browser, container, false);
        }

@Override
public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.regionDetailTextView = (TextView) view.findViewById(R.id.menuTextView);
        Bundle args = getArguments();
        if(args != null && args.containsKey(ARG_REGION_NAME)) {
        this.regionDetailTextView.setText(args.getString(ARG_REGION_NAME));
        }
        }
        }
