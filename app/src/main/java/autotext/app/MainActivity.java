package autotext.app;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements AutoReply.OnFragmentInteractionListener{

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
    }

    private void goToAutoReply() {
        AutoReply autoReplyFragment = new AutoReply();
        fragmentManager.beginTransaction().replace(R.id.fragment_layout, autoReplyFragment).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
