package sk.upjs.ics.android.sweetcontroller;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentWithResult extends Fragment {


    private View view;
    private TextView result;
    private double amount;
    private ListView selectedListView;
    List<String> meals = new ArrayList<>();
    private boolean showNotification;

    public FragmentWithResult() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.fragment_with_result, container, false);
        selectedListView=(ListView)view.findViewById(R.id.selectedListView);
        result = (TextView) view.findViewById(R.id.resultTextView);
        if (savedInstanceState!=null) {
         meals = (List)savedInstanceState.get("key");
            this.amount=(double)savedInstanceState.get("amount");
            ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, meals);
            //selectedListView=(ListView)view.findViewById(R.id.selectedListView);
            selectedListView.setAdapter(listAdapter);
            result.setText("Insulin needed: "+String.valueOf(this.amount));
            System.out.println("meals: "+meals.toString());
        }
        //view = inflater.inflate(R.layout.fragment_with_result, container, false);
//        result = (TextView) view.findViewById(R.id.resultTextView);
        else {
            result.setText("Insulin needed: ");
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
       outState.putStringArrayList("key", (ArrayList)meals);
        outState.putDouble("amount", this.amount);
        //System.out.println("meals:"+Arrays.toString(meals.toArray()));
//        String[] values = listAdapter.getValues();
//        outState.putStringArray("myKey", values);
        //outState.putParcelable("key", selectedListView.onSaveInstanceState());
    }

    public void doPositiveClick(double amount, String meal) {
//        Toast.makeText(view.getContext(), "Hi, "+amount, Toast.LENGTH_SHORT).show();
this.amount=this.amount+Math.round(amount);
        if (this.amount>14 && SettingsFragment.switched) {
            System.out.println("switch"+SettingsFragment.switched);
            showNotification();
        }
    result.setText("Insulin needed: "+String.valueOf(Math.round(this.amount)));
        // meals = Arrays.asList("Insulin for Breakfast", "Insulin for Lunch", "Insulin for Dinner");
        meals.add(meal+": "+Math.round(amount)+" Insulin Units");//pocet inzulinovych jednotiek k danemu jedlu (konkretnemu vybranemu mnozstvu)
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, meals);

        selectedListView.setAdapter(listAdapter);

    }

    private void showNotification() {
        String newline = System.getProperty("line.separator");
        Notification notification = new Notification.Builder(this.getActivity())
                .setContentTitle("SweetController")
                .setContentText(this.amount+newline+" exceeded recommended amount of 14 Insulin Units!")
                .setContentIntent(getEmptyNotificationContentIntent())
                .setTicker("SweetController")
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.insulin)
                .getNotification();

        NotificationManager notificationManager
                = (NotificationManager) this.getActivity().getSystemService(
                Context.NOTIFICATION_SERVICE);
        notificationManager.notify("SweetController", 0, notification);
    }

    public PendingIntent getEmptyNotificationContentIntent() {
        int REQUEST_CODE = 0;
        int NO_FLAGS = 0;

        PendingIntent contentIntent = PendingIntent.getActivity(this.getActivity(), REQUEST_CODE, new Intent(), NO_FLAGS);
        return contentIntent;
    }


}
