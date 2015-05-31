package sk.upjs.ics.android.sweetcontroller;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Lenka on 2. 5. 2015.
 */
//http://developer.android.com/guide/topics/ui/dialogs.html
public class DialogForAmount extends DialogFragment {
    public static final String FRAGMENT_WITH_GRID = "SourceFragment";
    private double carbohydrates;
    private String meal;
    private String identification;

    public interface NoticeDialogListener {
       // public void onDialogPositiveClick(DialogFragment dialog);
       public void onDialogPositiveClick(double amount, String meal);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    NoticeDialogListener mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (NoticeDialogListener) activity;//.getFragmentManager().findFragmentByTag(FRAGMENT_WITH_GRID);
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_for_amount, null);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // builder.setView(view);
                        EditText edit = (EditText) view.findViewById(R.id.amount);
                        String text = edit.getText().toString();
                        if(text.equals("")) {
                           return;
                        }
                        int amount= Integer.parseInt(text) ;
                        System.out.println("amount: " + text);
                        //mListener.onDialogPositiveClick(DialogForAmount.this);
                        String state="";
                        if(identification.equals("breakfast")) {
                            state=(String)SetInsulinActivity2.breakfastSettingsTextView.getText();
                        }
                        if(identification.equals("lunch")) {
                            state=(String)SetInsulinActivity2.lunchSettingsTextView.getText();
                        }
                        if(identification.equals("dinner")) {
                            state=(String)SetInsulinActivity2.dinnerSettingsTextView.getText();
                        }
                        // String breakfastState = (String)SetInsulinActivity2.breakfastSettingsTextView.getText();
                       String amountOfInsulinPerOneCUString=state.substring(state.length()-1);
                        int amountOfInsulinPerOneCU = Integer.parseInt(amountOfInsulinPerOneCUString);
                        System.out.println("amount of insulin "+amountOfInsulinPerOneCU);

                        double amountOfSJ=amount/carbohydrates;//carbohydrates je v skutocnosti mnozstvo potraviny v gramoch, kt. obsahuje  SJ, amount je mnozstvo potraviny v gramoch, kt. chcem zjest
                        mListener.onDialogPositiveClick(amountOfSJ*amountOfInsulinPerOneCU, meal);//SJ je to iste co inzulinove jednotky priblizne (iny pomer sa bude dat nastavit v set inzulin)
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DialogForAmount.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    public void setCarbohydrates(double carbohydrates, String meal, String id) {
        this.carbohydrates = carbohydrates;
        this.meal=meal;
        this.identification=id;
    }
}
