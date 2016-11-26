package autotext.app;

import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.text.format.Time;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import static java.util.concurrent.TimeUnit.*;
import static autotext.app.AutoReplyDBHelper.*;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity implements AutoReply.OnFragmentInteractionListener,
        MenuFragment.OnFragmentInteractionListener, Login.OnFragmentInteractionListener,
        HistoryFragment.OnFragmentInteractionListener, MessagesFragment.OnFragmentInteractionListener,
        ContactsFragment.OnFragmentInteractionListener, ConversationFragment.OnFragmentInteractionListener,
        ComposeMessage.OnFragmentInteractionListener, Settings.OnFragmentInteractionListener,
        MapFragment.OnFragmentInteractionListener {

    final FragmentManager fragmentManager = getSupportFragmentManager();
    private final String TAG = "MAIN";
    protected DatabaseManager data;
    protected long uID;
    private final ScheduledExecutorService schedule = Executors.newScheduledThreadPool(1);
    private static long DAY = 24*60*60*1000L;
    private ScheduledFuture<?> checkHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Activity Created");
        setContentView(R.layout.activity_main);
        data = new DatabaseManager(this.getApplicationContext());
        goToLogin();
        View bar =  findViewById(R.id.my_toolbar);
        bar.setVisibility(View.GONE);
    }

    public void goToAutoReply() {
        AutoReply autoReplyFragment = AutoReply.newInstance();
        fragmentManager.beginTransaction().replace(R.id.main_fragment, autoReplyFragment).addToBackStack("autoreply").commit();
    }

    public void goToLogin() {
        View bar =  findViewById(R.id.my_toolbar);//hide toolbar
        bar.setVisibility(View.GONE);
        uID = -1; //set user id to -1;

        Login loginFragment = Login.newInstance();
        fragmentManager.beginTransaction().replace(R.id.main_fragment, loginFragment).commit();
    }

    public void goToMenu() {
        MenuFragment menu = MenuFragment.newInstance();
        fragmentManager.beginTransaction().replace(R.id.main_fragment, menu).commit();
    }
    public void goToContacts() {
        ContactsFragment contactsFragment = ContactsFragment.newInstance();
        fragmentManager.beginTransaction().replace(R.id.main_fragment, contactsFragment).addToBackStack("contactsFragment").commit();
    }
    public void goToMessageHistory() {
        HistoryFragment historyFragment = HistoryFragment.newInstance();
        fragmentManager.beginTransaction().replace(R.id.main_fragment, historyFragment).addToBackStack("historyFragment").commit();
    }
    public void goToMessages() {
        MessagesFragment messagesFragment = MessagesFragment.newInstance();
        fragmentManager.beginTransaction().replace(R.id.main_fragment, messagesFragment).addToBackStack("messagesFragment").commit();
    }

    public void goToSettings() {
        Settings settings = Settings.newInstance();
        fragmentManager.beginTransaction().replace(R.id.main_fragment, settings).addToBackStack("settings").commit();
    }

    public void goToComposeMessage() {
        ComposeMessage composeMessage = ComposeMessage.newInstance();
        fragmentManager.beginTransaction().replace(R.id.main_fragment, composeMessage).addToBackStack("composeMessage").commit();
    }

    public void goToMap() {
        MapFragment map = MapFragment.newInstance();
        fragmentManager.beginTransaction().replace(R.id.main_fragment, map).addToBackStack("map").commit();
    }

    public void logOut(){
        checkHandler.cancel(true);
        goToLogin();
        //disable menu bar buttons here.
    }
    public long checkLogin(String user, String pass){
        return data.checkUser(user, pass);
    }
    public long newUser(String user, String pass){

            return data.addUser(user, pass);

    }
    public void onLogin(long userID){
        uID = userID;
        Button go = (Button) findViewById(R.id.auto_reply_button);//attach all the toolbar buttons
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to set fragment here
                goToAutoReply();
            }
        });
        Button messages = (Button) findViewById(R.id.messages_button);
        messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMessages();
            }

        });
        Button menu = (Button) findViewById(R.id.menu_button);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMenu();
            }

        });
        Button contacts = (Button) findViewById(R.id.contacts_button);
        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToContacts();            }

        });
        View bar =  findViewById(R.id.my_toolbar);//show the toolbar
        bar.setVisibility(View.VISIBLE);

        goToMenu();
        checkMessageRepeat();
    }

    public void sendMessage(String message, String phoneNumber) {


        SmsManager smsManager = SmsManager.getDefault();
        PendingIntent sentP;
        sentP = PendingIntent.getBroadcast(this, 0, new Intent("sent"),0);
        smsManager.sendTextMessage("1"+phoneNumber, null, message, sentP, null);
    }
    public void checkMessageRepeat(){
        final Runnable check = new Runnable(){
            public void run(){
                //check messages goes here
                //get saved messages
                Log.d("Check","started checking");
                Calendar cal = Calendar.getInstance();
                long current = cal.getTimeInMillis();
                Log.d("checking","current time: "+current);
                cal.set(Calendar.DAY_OF_WEEK, 1);
                cal.set(Calendar.HOUR, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                long calOffset = cal.getTimeInMillis();//get start of current week time.
               // Log.d("Check","before prog messages sql");
                Cursor cursor = data.getActiveProgMessages(); //gets only on messages
                String[] names = cursor.getColumnNames();//gets names of columns
                System.out.println(cursor.getCount());
               // Log.d("check","after println");
                while (cursor.moveToNext()) {//move to next row
                   // Log.d("Check", "first loop");
                    boolean send = false;//assume not sendable unless it is in the correct time, determined soon.
                    //check Time and date
                    //get days
                    int i = 0;
                    for (int j = 0; j < names.length; j++) {
                     //   Log.d("Check", "j loop 1");
                        if (names[j].equals(T9C4)) {//4 is days
                            i = j;
                        }
                    }
                    //get days string
                    String days = cursor.getString(i); //days are the column right before repeat
                    long st = cursor.getLong(i - 2);//start and end time re the 2 before days
                    long end = cursor.getLong(i - 1);
                    //check every day
                    for (long k = 1; k < 8; k++) {
                    //    Log.d("Check", "k loop 1");
                        if (!send && days.indexOf(k + "") >= 0) {
                            //Log.d("checking","start + offset: "+((k-1)*DAY+st+calOffset));
                            //Log.d("checking","start + offset: "+((k-1)*DAY+end+calOffset));
                            if (((k-1) * DAY + st + calOffset < current) && ((k-1) * DAY + end + calOffset > current)) {
                                send = true;/* Message is to be sent during this time*/
                                Log.d("Check", "Correct time on message.");
                            }
                        }
                    }

                    //check wifi settings
                    i = 0;
                    for (int j = 0; j < names.length; j++) {
                       // Log.d("Check", "j loop 2");
                        if (names[j].equals(T6C1)) {
                            i = j;
                        }
                    }
                   // Log.d("Check", "outside j loop 2 i="+i);
                    //TODO check real wifi connections
                    String wiName = cursor.getString(i);
                  //  Log.d("Check", "after cursor.getString wiName = "+wiName);
                    if (!(wiName.equals("null"))) {//check if real value
                        //actually check if correct wifi is connected
                        send = false;
                        Log.d("checking", "wifi not connecting not sending");
                    }
                   // Log.d("check","after wifi name check");
                    //check gps settings
                    i = 0;
                    for (int j = 0; j < names.length; j++) {
                       // Log.d("Check", "j loop 3");
                        if (names[j].equals(T7C3)) {//get radius
                            i = j;
                        }
                    }
                    //TODO check against real gps location
                    if (!(cursor.getDouble(i) == 0)) {//if radius is not 0
                        //check against current location
                        send = false;
                        Log.d("checking", "radius not 0, not sending");
                    }
                    //determine which column is messageText
                    i = 0;
                    for (int j = 0; j < names.length; j++) {
                       // Log.d("Check", "j loop 4");
                        if (names[j].equals(T9C1)) {
                            i = j;
                        }
                    }
                    String mtext = cursor.getString(i);
                    //determine which column is phonenumber
                    i = 0;
                    for (int j = 0; j < names.length; j++) {
                       // Log.d("Check", "j loop 5");
                        if (names[j].equals(T9C9)) {
                            i = j;
                        }
                    }
                    long number = cursor.getLong(i);
                    //call send message with them
                    if (send) {
                        Log.d("Sending", "Sending a message");
                        sendMessage(mtext, number + "");                    }
                }
                Log.d("in checker","checked messages");
            }

        };
        checkHandler = schedule.scheduleWithFixedDelay(check, 30,300, SECONDS);
    }

    public long addGPSCondition(double longi, double lat, double radius, int leaveEnter){
        return data.addGPSCondition(longi, lat, radius, leaveEnter);
    }
    public long addWiFiCondition(String name, int leaveEnter){
        return data.addWiFiCondition(name, leaveEnter);
    }
    public long addProgMessage(String text, long start, long end, String days, int repeat, int onOff, long condition, long user, long pnum){
        return data.addProgMessage(text, start, end, days, repeat, onOff, condition, user, pnum);
    }
    public long addMessageCond(long wifiCond, long gpsCond){
        return data.addMessageCond(wifiCond, gpsCond);
    }
    public long getUserID(){
        return uID;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
