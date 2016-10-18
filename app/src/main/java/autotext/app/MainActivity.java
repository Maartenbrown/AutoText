package autotext.app;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements AutoReply.OnFragmentInteractionListener,
        Menu.OnFragmentInteractionListener, Login.OnFragmentInteractionListener {

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
        Menu menu = Menu.newInstance();
        fragmentManager.beginTransaction().replace(R.id.fragment_layout, menu).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
