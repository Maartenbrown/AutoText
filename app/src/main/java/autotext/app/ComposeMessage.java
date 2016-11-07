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
import android.widget.TimePicker;
import java.util.Calendar;
import android.app.PendingIntent;
import android.content.Intent;

import static java.util.Calendar.FRIDAY;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONDAY;
import static java.util.Calendar.SATURDAY;
import static java.util.Calendar.SUNDAY;
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

    public EditText editMessage;
    public EditText editPhoneNumber;

    private Button saveMessageButton;

    private CheckBox mondayCheck;
    private CheckBox tuesdayCheck;
    private CheckBox wednesdayCheck;
    private CheckBox thursdayCheck;
    private CheckBox fridayCheck;
    private CheckBox saturdayCheck;
    private CheckBox sundayCheck;
    private CheckBox repeatCheck;

    private CheckBox gpsLeaveCheck;
    private CheckBox gpsEnterCheck;
    private CheckBox cellLeaveCheck;
    private CheckBox cellEnterCheck;
    private CheckBox wifiConnectCheck;
    private CheckBox wifiDisconnectCheck;


    private TimePicker timePicker;

    private boolean isScheduledMessage;
    private boolean isGpsMessage;
    private boolean isWifiMessage;
    private boolean isCellMessage;

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
        editPhoneNumber = (EditText) view.findViewById(R.id.compose_message_phone);
        saveMessageButton = (Button) view.findViewById(R.id.compose_message_save);

        mondayCheck = (CheckBox) view.findViewById(R.id.compose_message_monday);
        tuesdayCheck = (CheckBox) view.findViewById(R.id.compose_message_tuesday);
        wednesdayCheck = (CheckBox) view.findViewById(R.id.compose_message_wednesday);
        thursdayCheck = (CheckBox) view.findViewById(R.id.compose_message_thursday);
        fridayCheck = (CheckBox) view.findViewById(R.id.compose_message_friday);
        saturdayCheck = (CheckBox) view.findViewById(R.id.compose_message_saturday);
        sundayCheck = (CheckBox) view.findViewById(R.id.compose_message_sunday);
        repeatCheck = (CheckBox) view.findViewById(R.id.compose_message_repeat);

        gpsLeaveCheck = (CheckBox) view.findViewById(R.id.compose_message_leave_gps);
        gpsEnterCheck = (CheckBox) view.findViewById(R.id.compose_message_enter_gps);
        cellLeaveCheck = (CheckBox) view.findViewById(R.id.compose_message_leave_cell_tower);
        cellEnterCheck = (CheckBox) view.findViewById(R.id.compose_message_enter_cell_tower);
        wifiConnectCheck = (CheckBox) view.findViewById(R.id.compose_message_wifi_connect);
        wifiDisconnectCheck = (CheckBox) view.findViewById(R.id.compose_message_wifi_disconnect);

        timePicker = (TimePicker) view.findViewById(R.id.compose_time_picker);

        saveMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                saveMessage();
            }
        });

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

    private void onScheduleMessage () {
        gpsLeaveCheck.setEnabled(false);
        gpsEnterCheck.setEnabled(false);
        cellEnterCheck.setEnabled(false);
        cellLeaveCheck.setEnabled(false);
        wifiConnectCheck.setEnabled(false);
        wifiDisconnectCheck.setEnabled(false);

        isScheduledMessage = true;

        isCellMessage = false;
        isGpsMessage = false;
        isWifiMessage = false;
    }

    private void onGpsMessage() {
        mondayCheck.setEnabled(false);
        tuesdayCheck.setEnabled(false);
        wednesdayCheck.setEnabled(false);
        thursdayCheck.setEnabled(false);
        fridayCheck.setEnabled(false);
        saturdayCheck.setEnabled(false);
        sundayCheck.setEnabled(false);
        repeatCheck.setEnabled(false);
        cellEnterCheck.setEnabled(false);
        cellLeaveCheck.setEnabled(false);
        wifiDisconnectCheck.setEnabled(false);
        wifiConnectCheck.setEnabled(false);

        isGpsMessage = true;

        isScheduledMessage = false;
        isCellMessage = false;
        isWifiMessage = false;
    }

    private void onWifiMessage() {
        mondayCheck.setEnabled(false);
        tuesdayCheck.setEnabled(false);
        wednesdayCheck.setEnabled(false);
        thursdayCheck.setEnabled(false);
        fridayCheck.setEnabled(false);
        saturdayCheck.setEnabled(false);
        sundayCheck.setEnabled(false);
        repeatCheck.setEnabled(false);
        gpsEnterCheck.setEnabled(false);
        gpsLeaveCheck.setEnabled(false);
        cellEnterCheck.setEnabled(false);
        cellLeaveCheck.setEnabled(false);

        isWifiMessage = true;

        isScheduledMessage = false;
        isCellMessage = false;
        isGpsMessage = false;

    }

    private void onCellMessage() {
        mondayCheck.setEnabled(false);
        tuesdayCheck.setEnabled(false);
        wednesdayCheck.setEnabled(false);
        thursdayCheck.setEnabled(false);
        fridayCheck.setEnabled(false);
        saturdayCheck.setEnabled(false);
        sundayCheck.setEnabled(false);
        repeatCheck.setEnabled(false);
        gpsEnterCheck.setEnabled(false);
        gpsLeaveCheck.setEnabled(false);
        wifiConnectCheck.setEnabled(false);
        wifiDisconnectCheck.setEnabled(false);

        isCellMessage = true;

        isScheduledMessage = false;
        isGpsMessage = false;
        isWifiMessage = false;
    }

    private void reEnableAll() {
        mondayCheck.setEnabled(true);
        tuesdayCheck.setEnabled(true);
        wednesdayCheck.setEnabled(true);
        thursdayCheck.setEnabled(true);
        fridayCheck.setEnabled(true);
        saturdayCheck.setEnabled(true);
        sundayCheck.setEnabled(true);
        repeatCheck.setEnabled(true);
        cellEnterCheck.setEnabled(true);
        cellLeaveCheck.setEnabled(true);
        wifiConnectCheck.setEnabled(true);
        wifiDisconnectCheck.setEnabled(true);
        gpsLeaveCheck.setEnabled(true);
        gpsEnterCheck.setEnabled(true);

        isCellMessage = false;
        isScheduledMessage = false;
        isGpsMessage = false;
        isWifiMessage = false;
    }

    private void saveMessage() {
        Calendar calendar = Calendar.getInstance();

        if (saveMessageButton.isPressed()) {
            String message = editMessage.getText().toString();
            String phoneNumber = editPhoneNumber.getText().toString();

            if(message.isEmpty()) {
                editMessage.setError("Message text is required!");
                return;
            }
            if(phoneNumber.isEmpty()) {
                editPhoneNumber.setError("Phone number is required!");
                return;
            } else if (phoneNumber.length() != 10) {
                editPhoneNumber.setError("Phone number must be 10 digits!");
                return;
            }

            if (mondayCheck.isChecked() || tuesdayCheck.isChecked() || wednesdayCheck.isChecked() ||
                    thursdayCheck.isChecked() || fridayCheck.isChecked() || repeatCheck.isChecked()
                    || saturdayCheck.isChecked() || sundayCheck.isChecked()) {
                onScheduleMessage();
            }

            if (gpsEnterCheck.isChecked() || gpsLeaveCheck.isChecked()) {
                onGpsMessage();
            }

            if (cellEnterCheck.isChecked() || cellLeaveCheck.isChecked()) {
                onCellMessage();
            }

            if (wifiConnectCheck.isChecked() || wifiDisconnectCheck.isChecked()) {
                onWifiMessage();
            }

            if(isScheduledMessage) {
                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());

                if (mondayCheck.isChecked()) {
                    calendar.set(Calendar.DAY_OF_WEEK, MONDAY);
                    Log.d(TAG, "Message set for " + HOUR_OF_DAY + ":" + MINUTE + " on Monday!");
                }
                if (tuesdayCheck.isChecked()) {
                    calendar.set(Calendar.DAY_OF_WEEK, TUESDAY);
                    Log.d(TAG, "Message set for " + HOUR_OF_DAY + ":" + MINUTE + " on Tuesday!");
                }
                if (wednesdayCheck.isChecked()) {
                    calendar.set(Calendar.DAY_OF_WEEK, WEDNESDAY);
                    Log.d(TAG, "Message set for " + HOUR_OF_DAY + ":" + MINUTE + " on Wednesday!");
                }
                if (thursdayCheck.isChecked()) {
                    calendar.set(Calendar.DAY_OF_WEEK, THURSDAY);
                    Log.d(TAG, "Message set for " + HOUR_OF_DAY + ":" + MINUTE + " on Thursday!");
                }
                if (fridayCheck.isChecked()) {
                    calendar.set(Calendar.DAY_OF_WEEK, FRIDAY);
                    Log.d(TAG, "Message set for " + HOUR_OF_DAY + ":" + MINUTE + " on Friday!");
                }
                if (saturdayCheck.isChecked()) {
                    calendar.set(Calendar.DAY_OF_WEEK, SATURDAY);
                    Log.d(TAG, "Message set for " + HOUR_OF_DAY + ":" + MINUTE + " on Saturday!");
                }
                if (sundayCheck.isChecked()) {
                    calendar.set(Calendar.DAY_OF_WEEK, SUNDAY);
                    Log.d(TAG, "Message set for " + HOUR_OF_DAY + ":" + MINUTE + " on Sunday!");
                }
                if (repeatCheck.isChecked()) {
                    Log.d(TAG, "Message will repeat weekly");
                }

            } else if (isCellMessage) {
                if (cellEnterCheck.isChecked()) {
                    Log.d(TAG, "Message set to send when entering Cell tower radius");
                } else if (cellLeaveCheck.isChecked()) {
                    Log.d(TAG, "Message set to send when leaving Cell tower radius");
                } else {
                    Log.d(TAG, "ERROR in cell settings");
                }

            } else if (isWifiMessage) {
                if (wifiConnectCheck.isChecked()) {
                    Log.d(TAG, "Message set to send when connecting to WiFi network");
                } else if (wifiDisconnectCheck.isChecked()) {
                    Log.d(TAG, "Message set to send when disconnecting from WiFi network");
                } else {
                    Log.d(TAG, "ERROR in WiFi settings");
                }

            } else if (isGpsMessage) {
                if (gpsEnterCheck.isChecked()) {
                    Log.d(TAG, "Message set to send when entering GPS location");
                } else if (gpsLeaveCheck.isChecked()) {
                    Log.d(TAG, "Message set to send when leaving GPS location");
                } else {
                    Log.d(TAG, "ERROR in GPS settings");
                }
            } else {
                Log.d(TAG, "No message option selected");

            }
        }
    }

    private void onClick(View view) {


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

    }
}
