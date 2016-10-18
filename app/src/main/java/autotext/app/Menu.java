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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Menu.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Menu#newInstance} factory method to
 * create an instance of this fragment.
 */

public class Menu extends Fragment implements OnClickListener {

    private Menu.OnFragmentInteractionListener menuListener;

    private Button menuContactsButton;
    private Button menuMessageHistoryButton;
    private Button menuMessagesButton;
    private Button menuSettingsButton;
    private Button menuAutoreplyButton;
    private Button menuLogoutButton;

    public Menu() {
        // Required empty public constructor
    }

    public static Menu newInstance() {
        return new Menu();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.main_menu_screen, container, false);


        menuContactsButton = (Button) view.findViewById(R.id.menu_screen_contacts_button);
        menuMessageHistoryButton = (Button) view.findViewById(R.id.menu_screen_message_history_button);
        menuMessagesButton = (Button) view.findViewById(R.id.menu_screen_messages_button);
        menuSettingsButton = (Button) view.findViewById(R.id.menu_screen_settings_button);
        menuAutoreplyButton = (Button) view.findViewById(R.id.menu_screen_auto_reply_button);
        menuLogoutButton = (Button) view.findViewById(R.id.menu_screen_logout_button);

        menuContactsButton.setOnClickListener(this);
        menuMessageHistoryButton.setOnClickListener(this);
        menuMessagesButton.setOnClickListener(this);
        menuSettingsButton.setOnClickListener(this);
        menuAutoreplyButton.setOnClickListener(this);
        menuLogoutButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Menu.OnFragmentInteractionListener) {
            menuListener = (Menu.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        menuListener = null;
    }

    @Override
    public void onClick(View view) {
        goToAutoReply();
        //TODO: Navigation to another view
    }

    private void goToAutoReply() {

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
