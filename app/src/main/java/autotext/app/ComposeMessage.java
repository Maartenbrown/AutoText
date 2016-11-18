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
    private Long phoneNumber;
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
    private int leaveEnterW=0;//wifi
    private int leaveEnterG=0;//gps

    public long wifiCond;
    public long gpsCond;
    public long messageCond;
    public int onOff;
    private TimePicker timePicker;
    private long number= 61466859960L;

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
    /*
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
    */
    private void saveMessage() {
        //initialize calendar to current week, at minimum time. ie sunday at midnight
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, 1);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND,0);
        long timebase = cal.getTimeInMillis();
        message= editMessage.getText().toString();
        phoneNumber = Long.parseLong(editPhoneNumber.getText().toString());
        onOff = 0;
        if(message.isEmpty()) {
            editMessage.setError("Message text is required!");
            return;
        }
        if(phoneNumber<=0) {
            editPhoneNumber.setError("Phone number is required!");
            return;
        } else if (phoneNumber<=999999999) {
            editPhoneNumber.setError("Phone number must be 10 digits!");
            return;
        }

        //set base wifi/gps conditions
        wifiCond = AutoReplyDBHelper.baseWIFI;
        gpsCond = AutoReplyDBHelper.baseGPS;
        //Get time of day
        cal.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
        cal.set(Calendar.MINUTE, timePicker.getCurrentMinute());
        long start = cal.getTimeInMillis()-timebase;//get time during week
        long end = start +(60*1000*5L);//start +5 minutes in miliseconds

        //Check for repeating
        if (repeatCheck.isChecked()) {
            repeat = 0;
            Log.d(TAG, "Message will repeat weekly");
        } else {
            repeat = 1;
        }
        //create days string
        String days ="";
        if (mondayCheck.isChecked()) {
            days+="2";
        }
        if (tuesdayCheck.isChecked()) {
            days+="3";
        }
        if (wednesdayCheck.isChecked()) {
            days+="4";
        }
        if (thursdayCheck.isChecked()) {
            days+="5";
        }
        if (fridayCheck.isChecked()) {
            days+="6";
        }
        if (saturdayCheck.isChecked()) {
            days+="7";
        }
        if (sundayCheck.isChecked()) {
            days+="0";
        }
        //set up wifi information
        String wifiName = editWifiName.getText().toString();
        if(wifiName != null && wifiName.length()>0){

            if (wifiConnectCheck.isChecked()) {
                leaveEnterW = 0;
                wifiCond = composeListener.addWiFiCondition(wifiName, leaveEnterW);
            }
             else if (wifiDisconnectCheck.isChecked()) {
                 leaveEnterW = 1;
                wifiCond = composeListener.addWiFiCondition(wifiName, leaveEnterW);
            }
        }
        //set up gps information
        //TODO: Add GPS coordinates from Google Maps
        long latitude = 0;
        long longitude = 0;
        long radius = 0;

        if (gpsEnterCheck.isChecked()&& radius>0) {
            leaveEnterG = 0;
            gpsCond = composeListener.addGPSCondition(latitude, longitude, radius, leaveEnterG);
        } else if (gpsLeaveCheck.isChecked()) {
            leaveEnterG= 1;
            gpsCond = composeListener.addGPSCondition(latitude, longitude, radius, leaveEnterG);
        }
        //make message
        //first set up message condition, gps and wifi conditions already exist
        messageCond = composeListener.addMessageCond(wifiCond,gpsCond);
        //then create message
        composeListener.addProgMessage(message,start, end, days, repeat, onOff, messageCond, composeListener.getUserID(), number);
        Log.d("Message","Created new message");


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
        long addProgMessage(String text, long start, long end, String days, int repeat, int onOff, long condition, long user, long pnum);
        long addMessageCond(long wifiCond, long gpsCond);
        long getUserID();
//        String getPhoneNumber();
//        String getMessage();

    }
}
