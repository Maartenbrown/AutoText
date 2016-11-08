package autotext.app;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements AutoReply.OnFragmentInteractionListener,
        MenuFragment.OnFragmentInteractionListener, Login.OnFragmentInteractionListener,
        HistoryFragment.OnFragmentInteractionListener, MessagesFragment.OnFragmentInteractionListener,
        ContactsFragment.OnFragmentInteractionListener, ConversationFragment.OnFragmentInteractionListener,
        Settings.OnFragmentInteractionListener {

    final FragmentManager fragmentManager = getSupportFragmentManager();
    private final String TAG = "MAIN";
    protected DatabaseManager data;
    protected long uID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Activity Created");
        setContentView(R.layout.activity_main);
        data = new DatabaseManager(this.getApplicationContext());
        goToLogin();
    }

    public void goToAutoReply() {
        AutoReply autoReplyFragment = AutoReply.newInstance();
        fragmentManager.beginTransaction().replace(R.id.main_fragment, autoReplyFragment).addToBackStack("autoreply").commit();
    }

    public void goToLogin() {
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
    public void logOut(){
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
        Button go = (Button) findViewById(R.id.auto_reply_button);
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
                goToMessages();
            }

        });

        goToMenu();
    }

    public void sendMessage() {
        String message = "test";
        String phoneNumber = "5555555555";
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber, null, message, null, null);
    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
