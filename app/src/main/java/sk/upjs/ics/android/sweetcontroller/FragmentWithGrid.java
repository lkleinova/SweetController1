package sk.upjs.ics.android.sweetcontroller;


import android.app.DialogFragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.Arrays;

import static android.R.id.text1;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentWithGrid extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, AdapterView.OnItemClickListener
 {
    private GridView foodGrid;
     private GridView foodGridSQL;
    public static final String FRAGMENT_TAG_SOURCE = "SourceFragment";
     private static final int BREAKFASTTABLE_LOADER_ID = 0;
     private SimpleCursorAdapter adapter;

    // private String[] mPlanetTitles;
     //private DrawerLayout mDrawerLayout;
     //private ListView mDrawerList;

    public FragmentWithGrid() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
//        String value = getArguments().getString("key");
  //      System.out.println("zobrazujem "+value);
//        foodGrid = (GridView) getView().findViewById(R.id.foodGridView);
      //  String[] adapterData = { "0905223223", "0905123456", "112", "055123456" };
        getLoaderManager().initLoader(BREAKFASTTABLE_LOADER_ID, Bundle.EMPTY, this);
        View fragmentLayout = inflater.inflate(R.layout.fragment_with_grid, container, false);
       // View fragmentLayout1 = inflater.inflate(R.layout.drawerlayout, container, false);
/////////////////////////////
        //mPlanetTitles = new String[]{"Breakfast", "Lunch", "Dinner"};
        //mDrawerLayout = (DrawerLayout) fragmentLayout1.findViewById(R.id.drawer_layout);
        //mDrawerList = (ListView) fragmentLayout1.findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        //mDrawerList.setAdapter(new ArrayAdapter<String>(this.getActivity(),
          //      android.R.layout.simple_list_item_1, mPlanetTitles));
////////////////////////////
       // ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(fragmentLayout.getContext(), android.R.layout.simple_list_item_1, text1, adapterData);
        //foodGrid.setAdapter(adapter);
        System.out.println("tu som");
       foodGrid = (GridView) fragmentLayout.findViewById(R.id.foodGridView);
        foodGridSQL = (GridView) fragmentLayout.findViewById(R.id.foodGridViewSQL);
       foodGridSQL.setAdapter(initializeAdapter());
       foodGrid.setAdapter(new ImageAdapter(fragmentLayout.getContext(), 1));
       // foodGrid.setAdapter(new ImageAdapter(fragmentLayout.getContext()));
        //ImageAdapter imageAdapter = new ImageAdapter(fragmentLayout.getContext());
        //foodGrid.setAdapter(imageAdapter);
foodGridSQL.setOnItemClickListener(this
        //new AdapterView.OnItemClickListener() {
    //@Override
    //public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
      //  DialogForAmount dialogForAmount = new DialogForAmount();
        //dialogForAmount.show(getFragmentManager(), FRAGMENT_TAG_SOURCE);

    //}
//}
);
        return fragmentLayout;
    }
     private ListAdapter initializeAdapter() {
         String[] from= {Provider.BreakfastTable.NULL };
         //System.out.println(Arrays.toString(from));
         int[] to = {android.R.id.text1};
         //this.adapter = new SimpleCursorAdapter(this.getActivity(), android.R.layout.simple_list_item_2, Defaults.NO_CURSOR, from, to, Defaults.NO_FLAGS);
         this.adapter = new SimpleCursorAdapter(this.getActivity(), R.layout.customstyle, Defaults.NO_CURSOR, from, to, Defaults.NO_FLAGS);
         System.out.println("inicializujem");
         return this.adapter;
     }

     @Override
     public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
         CursorLoader loader = new CursorLoader(this.getActivity());

         loader.setUri(BreakfastTableContentProvider.CONTENT_URI);
         System.out.println("uri: "+BreakfastTableContentProvider.CONTENT_URI);
         return loader;
     }

     @Override
     public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
         System.out.println("finished");
         this.adapter.swapCursor(cursor);
     }

     @Override
     public void onLoaderReset(Loader<Cursor> cursorLoader) {
         this.adapter.swapCursor(Defaults.NO_CURSOR);
     }

     @Override
     public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
         System.out.println("position "+position);
         Cursor selectedBreakfastTableCursor = (Cursor) parent.getItemAtPosition(position);
         if (selectedBreakfastTableCursor==null) {
             System.out.println("je to null");
         }
         int carbohydratesColumnIndex = selectedBreakfastTableCursor.getColumnIndex(Provider.BreakfastTable.CARBOHYDRATES);
         Double carbohydrates = selectedBreakfastTableCursor.getDouble(carbohydratesColumnIndex);
         int mealColumnIndex = selectedBreakfastTableCursor.getColumnIndex(Provider.BreakfastTable.MEAL);
         String meal = selectedBreakfastTableCursor.getString(mealColumnIndex);
         System.out.println("carbohydrates in 100 g"+carbohydrates);
         DialogForAmount dialogForAmount = new DialogForAmount();
         dialogForAmount.setCarbohydrates(carbohydrates, meal, "breakfast");
         dialogForAmount.show(getFragmentManager(), FRAGMENT_TAG_SOURCE);
     }
 }
