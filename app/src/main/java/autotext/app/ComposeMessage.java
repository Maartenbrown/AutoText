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
    private String message;
    public EditText editPhoneNumber;
    private String phoneNumber;
    public EditText editWifiName;


    private Button saveMessageButton;

    private CheckBox mondayCheck;
    private CheckBox tuesdayCheck;
    private CheckBox wednesdayCheck;
    private CheckBox thursdayCheck;
    private CheckBox fridayCheck;
    private CheckBox saturdayCheck;
    private CheckBox sundayCheck;
    private CheckBox repeatCheck;
    private int repeat;

    private CheckBox gpsLeaveCheck;
    private CheckBox gpsEnterCheck;
    private CheckBox wifiConnectCheck;
    private CheckBox wifiDisconnectCheck;
    private int leaveEnter;

    public long wifiCond;
    public long gpsCond;
    public long messageCond;
    public int onOff;
    private TimePicker timePicker;

    private boolean isScheduledMessage;
    private boolean isGpsMessage;
    private boolean isWifiMessage;


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
        editWifiName = (EditText) view.findViewById(R.id.compose_wifi_name);
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
        wifiConnectCheck.setEnabled(false);
        wifiDisconnectCheck.setEnabled(false);

        isScheduledMessage = true;


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
        wifiDisconnectCheck.setEnabled(false);
        wifiConnectCheck.setEnabled(false);

        isGpsMessage = true;

        isScheduledMessage = false;
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

        isWifiMessage = true;

        isScheduledMessage = false;
        isGpsMessage = false;

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
        wifiConnectCheck.setEnabled(true);
        wifiDisconnectCheck.setEnabled(true);
        gpsLeaveCheck.setEnabled(true);
        gpsEnterCheck.setEnabled(true);


        isScheduledMessage = false;
        isGpsMessage = false;
        isWifiMessage = false;
    }

    private void saveMessage() {
        Calendar calendar = Calendar.getInstance();

        if (saveMessageButton.isPressed()) {
            message= editMessage.getText().toString();
            phoneNumber = editPhoneNumber.getText().toString();
            onOff = 0;

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

            if (wifiConnectCheck.isChecked() || wifiDisconnectCheck.isChecked()) {
                onWifiMessage();
            }

            if(isScheduledMessage) {
                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
                String wifiName = "null";
                wifiCond = composeListener.addWiFiCondition(wifiName, 1);
                gpsCond = composeListener.addGPSCondition(0, 0, 0, 0);
                messageCond = composeListener.addMessageCond((int) wifiCond, (int) gpsCond);
                leaveEnter = 0;

                if (repeatCheck.isChecked()) {
                    repeat = 1;
                    Log.d(TAG, "Message will repeat weekly");
                } else {
                    repeat = 0;
                }

                if (mondayCheck.isChecked()) {
                    calendar.set(Calendar.DAY_OF_WEEK, MONDAY);
                    composeListener.addProgMessage(message, HOUR_OF_DAY, MINUTE, "Monday" , repeat, onOff, (int) messageCond, (int) composeListener.getUserID());
                    Log.d(TAG, "Message set for " + HOUR_OF_DAY + ":" + MINUTE + " on Monday!");
                }
                if (tuesdayCheck.isChecked()) {
                    calendar.set(Calendar.DAY_OF_WEEK, TUESDAY);
                    Long a =composeListener.addProgMessage(message, HOUR_OF_DAY, MINUTE, "Tuesday" , repeat, onOff, (int) messageCond, (int) composeListener.getUserID());
                    Log.d(TAG, "Message set for " + HOUR_OF_DAY + ":" + MINUTE + " on Tuesday!, message number"+a);
                }
                if (wednesdayCheck.isChecked()) {
                    calendar.set(Calendar.DAY_OF_WEEK, WEDNESDAY);
                    composeListener.addProgMessage(message, HOUR_OF_DAY, MINUTE, "Wednesday" , repeat, onOff, (int) messageCond, (int) composeListener.getUserID());
                    Log.d(TAG, "Message set for " + HOUR_OF_DAY + ":" + MINUTE + " on Wednesday!");
                }
                if (thursdayCheck.isChecked()) {
                    calendar.set(Calendar.DAY_OF_WEEK, THURSDAY);
                    composeListener.addProgMessage(message, HOUR_OF_DAY, MINUTE, "Thursday" , repeat, onOff, (int) messageCond, (int) composeListener.getUserID());
                    Log.d(TAG, "Message set for " + HOUR_OF_DAY + ":" + MINUTE + " on Thursday!");
                }
                if (fridayCheck.isChecked()) {
                    calendar.set(Calendar.DAY_OF_WEEK, FRIDAY);
                    composeListener.addProgMessage(message, HOUR_OF_DAY, MINUTE, "Friday" , repeat, onOff, (int) messageCond, (int) composeListener.getUserID());
                    Log.d(TAG, "Message set for " + HOUR_OF_DAY + ":" + MINUTE + " on Friday!");
                }
                if (saturdayCheck.isChecked()) {
                    calendar.set(Calendar.DAY_OF_WEEK, SATURDAY);
                    composeListener.addProgMessage(message, HOUR_OF_DAY, MINUTE, "Saturday" , repeat, onOff, (int) messageCond,(int) composeListener.getUserID());
                    Log.d(TAG, "Message set for " + HOUR_OF_DAY + ":" + MINUTE + " on Saturday!");
                }
                if (sundayCheck.isChecked()) {
                    calendar.set(Calendar.DAY_OF_WEEK, SUNDAY);
                    composeListener.addProgMessage(message, HOUR_OF_DAY, MINUTE, "Sunday" , repeat, onOff, (int) messageCond, (int) composeListener.getUserID());
                    Log.d(TAG, "Message set for " + HOUR_OF_DAY + ":" + MINUTE + " on Sunday!");
                }


            } else if (isWifiMessage) {
                String wifiName = editWifiName.getText().toString();
                gpsCond = composeListener.addGPSCondition(0, 0, 0, 0);
                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
                repeat = 0;
                if (wifiConnectCheck.isChecked()) {
                    leaveEnter = 0;
                    composeListener.addWiFiCondition(wifiName, leaveEnter);
                    messageCond = composeListener.addMessageCond((int) wifiCond, (int) gpsCond);
                    composeListener.addProgMessage(message, HOUR_OF_DAY, MINUTE, "null" , repeat, onOff, (int) messageCond,(int) composeListener.getUserID() );
                    Log.d(TAG, "Message set to send when connecting to WiFi network: " + wifiName);
                } else if (wifiDisconnectCheck.isChecked()) {
                    leaveEnter = 1;
                    composeListener.addWiFiCondition(wifiName, leaveEnter);
                    messageCond = composeListener.addMessageCond((int) wifiCond, (int) gpsCond);
                    composeListener.addProgMessage(message, HOUR_OF_DAY, MINUTE, "null" , repeat, onOff, (int) messageCond,(int) composeListener.getUserID());
                    Log.d(TAG, "Message set to send when disconnecting from WiFi network: " + wifiName);
                } else {
                    Log.d(TAG, "ERROR in WiFi settings");
                }

            } else if (isGpsMessage) {
                //TODO: Add GPS coordinates from Google Maps
                long latitude = 40;
                long longitude = 83;
                long radius = 50;
                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
                String wifiName = "null";
                wifiCond = composeListener.addWiFiCondition(wifiName, 0);
                if (gpsEnterCheck.isChecked()) {
                    leaveEnter = 1;
                    composeListener.addGPSCondition(longitude, latitude, radius, leaveEnter);
                    Log.d(TAG, "Message set to send when entering GPS location");
                } else if (gpsLeaveCheck.isChecked()) {
                    leaveEnter = 1;
                    composeListener.addGPSCondition(longitude, latitude, radius, leaveEnter);
                    Log.d(TAG, "Message set to send when leaving GPS location");
                } else {
                    Log.d(TAG, "ERROR in GPS settings");
                }
            } else {
                Log.d(TAG, "No message option selected");

            }
        }
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
        long addGPSCondition(double longi, double lat, double radius, int leaveEnter);
        long addWiFiCondition(String name, int leaveEnter);
        long addProgMessage(String text, int hour, int minute, String days, int repeat, int onOff, int condition, int user);
        long addMessageCond(int wifiCond, int gpsCond);
        long getUserID();
//        String getPhoneNumber();
//        String getMessage();

    }
}
