package autotext.app;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements AutoReply.OnFragmentInteractionListener,
        HistoryFragment.OnFragmentInteractionListener, MessagesFragment.OnFragmentInteractionListener,
        ContactsFragment.OnFragmentInteractionListener, ConversationFragment.OnFragmentInteractionListener{

    final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // We can use this to test our fragments
        Button go = (Button) findViewById(R.id.go_button);
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
    }

    private void goToAutoReply() {
        AutoReply autoReplyFragment = AutoReply.newInstance();
        fragmentManager.beginTransaction().replace(R.id.main_fragment, autoReplyFragment).addToBackStack("autoreply").commit();
    }
    private void goToHistory(){
        HistoryFragment mes = HistoryFragment.newInstance();
        fragmentManager.beginTransaction().replace(R.id.main_fragment, mes).addToBackStack("historylist").commit();
    }
    private void goToMessages(){
        MessagesFragment mes = MessagesFragment.newInstance();
        fragmentManager.beginTransaction().replace(R.id.main_fragment, mes).addToBackStack("messagelist").commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
