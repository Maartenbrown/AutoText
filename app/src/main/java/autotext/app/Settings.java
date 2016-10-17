package autotext.app;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Settings.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Settings#newInstance} factory method to
 * create an instance of this fragment.
 */

public class Settings extends Fragment implements OnClickListener {

    private OnFragmentInteractionListener sListener;



    private Button facebookButton;
    private Button twitterButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    setContentView(R.layout.settings);

    final CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox_id);
    if (checkBox.isChecked()) {
        checkBox.setChecked(false);
    }
}
