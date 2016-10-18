package autotext.app;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.widget.Button;


public class Menu extends AppCompatActivity implements AutoReply.OnFragmentInteractionListener,
        HistoryFragment.OnFragmentInteractionListener, MessagesFragment.OnFragmentInteractionListener,
        ContactsFragment.OnFragmentInteractionListener, ConversationFragment.OnFragmentInteractionListener,
        ComposeMessage.OnFragmentInteractionListener {

    private OnFragmentInteractionListener menuListener;

    final FragmentManager fragmentManager = getSupportFragmentManager();

    public Menu() {
        // Required empty public constructor
    }

    public static Menu newInstance() {
        return new Menu();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_screen);

        Button menuContactsButton = (Button) findViewById(R.id.menu_screen_contacts_button);
        Button menuMessageHistoryButton = (Button) findViewById(R.id.menu_screen_message_history_button);
        Button menuMessagesButton = (Button) findViewById(R.id.menu_screen_messages_button);
        Button menuSettingsButton = (Button) findViewById(R.id.menu_screen_settings_button);
        Button menuAutoreplyButton = (Button) findViewById(R.id.menu_screen_auto_reply_button);
        Button menuLogoutButton = (Button) findViewById(R.id.menu_screen_logout_button);

        menuContactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoContacts();
            }
        });
        menuMessageHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMessageHistory();
            }
        });
        menuMessagesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMessages();
            }
        });
        menuSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoSettings();
            }
        });
        menuAutoreplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoAutoReply();
            }
        });
        menuLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoLogin();
            }
        });
    }

    private void gotoContacts() {
        ContactsFragment contactsFragment = ContactsFragment.newInstance();
        fragmentManager.beginTransaction().replace(R.id.main_fragment, contactsFragment).addToBackStack("contactsFragment").commit();
    }

    private void gotoAutoReply() {
        AutoReply autoReply = AutoReply.newInstance();
        fragmentManager.beginTransaction().replace(R.id.main_fragment, autoReply).addToBackStack("autoReply").commit();
    }

    private void gotoMessageHistory() {
        HistoryFragment historyFragment = HistoryFragment.newInstance();
        fragmentManager.beginTransaction().replace(R.id.main_fragment, historyFragment).addToBackStack("historyFragment").commit();
    }

    private void gotoMessages() {
        MessagesFragment messagesFragment = MessagesFragment.newInstance();
        fragmentManager.beginTransaction().replace(R.id.main_fragment, messagesFragment).addToBackStack("messagesFragment").commit();
    }

    private void gotoSettings() {
        Settings settings = Settings.newInstance();
        fragmentManager.beginTransaction().replace(R.id.main_fragment, settings).addToBackStack("settings").commit();
    }

    private void gotoLogin() {
        Login login = Login.newInstance();
        fragmentManager.beginTransaction().replace(R.id.main_fragment, login).addToBackStack("login").commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
