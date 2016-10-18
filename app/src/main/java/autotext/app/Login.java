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
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Login.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Login extends Fragment implements OnClickListener {

    private OnFragmentInteractionListener loginListener;

    private EditText editUsername;
    private EditText editPassword;

    private Button loginButton;
    private Button newUserButton;

    public Login() {
        // Required empty public constructor
    }

    public static Login newInstance() {
        return new Login();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.login_screen, container, false);

        editUsername = (EditText) view.findViewById(R.id.login_username);
        editPassword = (EditText) view.findViewById(R.id.login_password);
        loginButton = (Button) view.findViewById(R.id.login_screen_login_button);
        newUserButton = (Button) view.findViewById(R.id.login_screen_new_user_button);
        loginButton.setOnClickListener(this);
        newUserButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            loginListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        loginListener = null;
    }

    @Override
    public void onClick(View view) {

        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();


        if(username.isEmpty()) {
            editUsername.setError("Username is required");
            return;
        }

        if(password.isEmpty()) {
            editPassword.setError("Password is required");
        }

        openMenu();
    }

    private void openMenu()
    {
        //Probably not the best way to do this but it'll do for now
        ((MainActivity)getActivity()).goToMenu();
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
