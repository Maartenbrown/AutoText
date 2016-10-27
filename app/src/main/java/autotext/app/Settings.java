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
import android.widget.CheckBox;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Settings.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Settings#newInstance} factory method to
 * create an instance of this fragment.
 */

public class Settings extends Fragment implements OnClickListener {

    private Settings.OnFragmentInteractionListener settingsListener;

    private Button facebookButton;
    private Button twitterButton;

    public Settings() {
        // Required empty public constructor
    }

    public static Settings newInstance() { return new Settings(); }
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.settings_screen;
        //    final CheckBox facebookCheckBox = (CheckBox) findViewById(R.id.checkbox_id);
//    if (facebookCheckBox.isChecked()) {
//        facebookCheckBox.setChecked(false);
//    }
//
//    final CheckBox twitterCheckBox = (CheckBox) findViewById(R.id.checkbox_id);
//    if (twitterCheckBox.isChecked()) {
//        twitterCheckBox.setChecked(false);
//    }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.settings_screen, container, false);
        final CheckBox gpsCheckBox = (CheckBox) view.findViewById(R.id.gps_checkbox_id);
        if (gpsCheckBox.isChecked()) {
            gpsCheckBox.setChecked(false);
        }

        final CheckBox wifiCheckBox = (CheckBox) view.findViewById(R.id.wifi_checkbox_id);
        if (wifiCheckBox.isChecked()) {
            wifiCheckBox.setChecked(false);
        }

        final CheckBox cellCheckBox = (CheckBox) view.findViewById(R.id.cell_checkbox_id);
        if (cellCheckBox.isChecked()) {
            cellCheckBox.setChecked(false);
        }

        facebookButton = (Button) view.findViewById(R.id.settings_screen_facebook_button);
        twitterButton = (Button) view.findViewById(R.id.settings_screen_twitter_button);
        facebookButton.setOnClickListener(this);
        twitterButton.setOnClickListener(this);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Settings.OnFragmentInteractionListener) {
            settingsListener = (Settings.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        settingsListener = null;
    }

    @Override
    public void onClick(View view) {


        //TODO: Navigation to another view
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
