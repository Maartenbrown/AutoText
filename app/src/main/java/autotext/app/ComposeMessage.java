package autotext.app;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import android.widget.CheckBox;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import java.util.Calendar;

import static java.util.Calendar.FRIDAY;
import static java.util.Calendar.MONDAY;
import static java.util.Calendar.THURSDAY;
import static java.util.Calendar.TUESDAY;
import static java.util.Calendar.WEDNESDAY;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ComposeMessage.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ComposeMessage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComposeMessage extends Fragment {

    private OnFragmentInteractionListener composeListener;

    private final String TAG = "COMPOSE_MESSAGE";

    private EditText editMessage;
    private EditText editPhoneNumber;

    private Button saveMessageButton;

    private CheckBox mondayCheck;
    private CheckBox tuesdayCheck;
    private CheckBox wednesdayCheck;
    private CheckBox thursdayCheck;
    private CheckBox fridayCheck;

    private TimePicker timePicker;

//    private RadioButton amSelect;
//    private RadioButton pmSelect;

    public ComposeMessage() {
        // Required empty public constructor
    }

    public static ComposeMessage newInstance() {
        return new ComposeMessage();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Fragment created");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_compose_message, container, false);

        editMessage = (EditText) view.findViewById(R.id.compose_message_text);
        //editPhoneNumber = (EditText) view.findViewById(R.id.)
        saveMessageButton = (Button) view.findViewById(R.id.compose_message_save);

        mondayCheck = (CheckBox) view.findViewById(R.id.compose_message_monday);
        tuesdayCheck = (CheckBox) view.findViewById(R.id.compose_message_tuesday);
        wednesdayCheck = (CheckBox) view.findViewById(R.id.compose_message_wednesday);
        thursdayCheck = (CheckBox) view.findViewById(R.id.compose_message_thursday);
        fridayCheck = (CheckBox) view.findViewById(R.id.compose_message_friday);

        timePicker = (TimePicker) view.findViewById(R.id.compose_time_picker);

//        amSelect = (RadioButton) view.findViewById(R.id.compose_message_am);
//        pmSelect = (RadioButton) view.findViewById(R.id.compose_message_pm);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (composeListener != null) {
            composeListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "Fragment attached");
        if (context instanceof OnFragmentInteractionListener) {
            composeListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "Fragment detached");
        composeListener = null;
    }

    public void onClick(View view) {
        String message = editMessage.getText().toString();
        //String phoneNumber = edit
        Calendar calendar = Calendar.getInstance();

        if(message.isEmpty()) {
            editMessage.setError("Message text is required!");
            return;
        }

//        if (amSelect.isChecked()) {
//
//        } else if (pmSelect.isChecked()) {
//
//        } else {
//            editMessage.setError("AM/PM selection is required!");
//            return;
//        }


        calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
        calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());

        if (mondayCheck.isChecked()) {
            calendar.set(Calendar.DAY_OF_WEEK, MONDAY);
        }
        if (tuesdayCheck.isChecked()) {
            calendar.set(Calendar.DAY_OF_WEEK, TUESDAY);
        }
        if (wednesdayCheck.isChecked()) {
            calendar.set(Calendar.DAY_OF_WEEK, WEDNESDAY);
        }
        if (thursdayCheck.isChecked()) {
            calendar.set(Calendar.DAY_OF_WEEK, THURSDAY);
        }
        if (fridayCheck.isChecked()) {
            calendar.set(Calendar.DAY_OF_WEEK, FRIDAY);
        }

        if (!mondayCheck.isChecked() && !tuesdayCheck.isChecked() && !wednesdayCheck.isChecked() &&
                !thursdayCheck.isChecked() && !fridayCheck.isChecked()) {
            editMessage.setError("At least one day must be selected!");
            return;
        }

        composeListener.sendMessage();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
        void sendMessage();
    }
}
