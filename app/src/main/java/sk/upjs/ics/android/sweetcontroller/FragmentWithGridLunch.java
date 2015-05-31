package sk.upjs.ics.android.sweetcontroller;


import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentWithGridLunch extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, AdapterView.OnItemClickListener{
    private GridView foodGrid;
    private GridView foodGridSQL;
    private SimpleCursorAdapter adapter;
    public static final String FRAGMENT_TAG_SOURCE = "SourceFragment";
    private static final int LUNCHTABLE_LOADER_ID = 0;

    public FragmentWithGridLunch() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getLoaderManager().initLoader(LUNCHTABLE_LOADER_ID, Bundle.EMPTY, this);
        View fragmentLayout = inflater.inflate(R.layout.fragment_with_grid_lunch, container, false);
        // ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(fragmentLayout.getContext(), android.R.layout.simple_list_item_1, text1, adapterData);
        //foodGrid.setAdapter(adapter);
        System.out.println("tu som");
        foodGrid = (GridView) fragmentLayout.findViewById(R.id.foodGridView);
        foodGrid.setAdapter(new ImageAdapter(fragmentLayout.getContext(), 2));
        foodGridSQL = (GridView) fragmentLayout.findViewById(R.id.foodGridViewSQL);
        foodGridSQL.setAdapter(initializeAdapter());
        // foodGrid.setAdapter(new ImageAdapter(fragmentLayout.getContext()));
        //ImageAdapter imageAdapter = new ImageAdapter(fragmentLayout.getContext());
        //foodGrid.setAdapter(imageAdapter);
//        foodGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                DialogForAmount dialogForAmount = new DialogForAmount();
//                dialogForAmount.show(getFragmentManager(), FRAGMENT_TAG_SOURCE);
//                //Toast.makeText(foodGrid.getContext(), "breakfast", Toast.LENGTH_SHORT)
//                //      .show();
//            }
//        });
        foodGridSQL.setOnItemClickListener(this);
        return fragmentLayout;
    }

    private ListAdapter initializeAdapter() {
        String[] from= {ProviderLunchDinner.LunchTable.NULL };
        //System.out.println(Arrays.toString(from));
        int[] to = {android.R.id.text1};
       // this.adapter = new SimpleCursorAdapter(this.getActivity(), android.R.layout.simple_list_item_2, Defaults.NO_CURSOR, from, to, Defaults.NO_FLAGS);
        this.adapter = new SimpleCursorAdapter(this.getActivity(), R.layout.customstyle, Defaults.NO_CURSOR, from, to, Defaults.NO_FLAGS);
        System.out.println("inicializujem");
        return this.adapter;
    }
    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        CursorLoader loader = new CursorLoader(this.getActivity());

        loader.setUri(LunchDinnerContentProvider.CONTENT_URI_LUNCH);
        //System.out.println("uri: "+BreakfastTableContentProvider.CONTENT_URI);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        this.adapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        this.adapter.swapCursor(Defaults.NO_CURSOR);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        Cursor selectedLunchTableCursor = (Cursor) parent.getItemAtPosition(i);
        if (selectedLunchTableCursor==null) {
            System.out.println("je to null");
        }
        int carbohydratesColumnIndex = selectedLunchTableCursor.getColumnIndex(ProviderLunchDinner.LunchTable.CARBOHYDRATES);
        Double carbohydrates = selectedLunchTableCursor.getDouble(carbohydratesColumnIndex);
        int mealColumnIndex = selectedLunchTableCursor.getColumnIndex(ProviderLunchDinner.LunchTable.MEAL);
        String meal = selectedLunchTableCursor.getString(mealColumnIndex);
        System.out.println("carbohydrates in 100 g"+carbohydrates);
        DialogForAmount dialogForAmount = new DialogForAmount();
        dialogForAmount.setCarbohydrates(carbohydrates, meal, "lunch");
        dialogForAmount.show(getFragmentManager(), FRAGMENT_TAG_SOURCE);
    }
}
