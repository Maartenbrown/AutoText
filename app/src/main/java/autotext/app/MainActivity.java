package autotext.app;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements AutoReply.OnFragmentInteractionListener,
        MenuFragment.OnFragmentInteractionListener, Login.OnFragmentInteractionListener,
        HistoryFragment.OnFragmentInteractionListener, MessagesFragment.OnFragmentInteractionListener,
        ContactsFragment.OnFragmentInteractionListener, ConversationFragment.OnFragmentInteractionListener,
        ComposeMessage.OnFragmentInteractionListener {

    final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goToLogin();
    }

    public void goToAutoReply() {
        AutoReply autoReplyFragment = AutoReply.newInstance();
        fragmentManager.beginTransaction().replace(R.id.fragment_layout, autoReplyFragment).commit();
    }

    public void goToLogin() {
        Login loginFragment = Login.newInstance();
        fragmentManager.beginTransaction().replace(R.id.fragment_layout, loginFragment).commit();
    }

    public void goToMenu() {
        MenuFragment menu = MenuFragment.newInstance();
        fragmentManager.beginTransaction().replace(R.id.fragment_layout, menu).commit();
    }
    public void goToContacts() {
        ContactsFragment contactsFragment = ContactsFragment.newInstance();
        fragmentManager.beginTransaction().replace(R.id.fragment_layout, contactsFragment).addToBackStack("contactsFragment").commit();
    }
    public void goToMessageHistory() {
        HistoryFragment historyFragment = HistoryFragment.newInstance();
        fragmentManager.beginTransaction().replace(R.id.fragment_layout, historyFragment).addToBackStack("historyFragment").commit();
    }
    public void goToMessages() {
        MessagesFragment messagesFragment = MessagesFragment.newInstance();
        fragmentManager.beginTransaction().replace(R.id.fragment_layout, messagesFragment).addToBackStack("messagesFragment").commit();
    }

    public void goToSettings() {
        Settings settings = Settings.newInstance();
        fragmentManager.beginTransaction().replace(R.id.fragment_layout, settings).addToBackStack("settings").commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
