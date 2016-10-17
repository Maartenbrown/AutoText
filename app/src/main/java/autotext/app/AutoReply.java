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
import android.widget.RadioButton;
import android.widget.RadioGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AutoReply.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AutoReply#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AutoReply extends Fragment implements OnClickListener {

    private OnFragmentInteractionListener mListener;

    private EditText editText;
    private EditText editDuration;
    private RadioButton minutesRadioButton;
    private RadioButton hoursRadioButton;
    private Button saveButton;

    public AutoReply() {
        // Required empty public constructor
    }

    public static AutoReply newInstance() {
        return new AutoReply();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_auto_reply, container, false);

        editText = (EditText) view.findViewById(R.id.auto_reply_text);
        editDuration = (EditText) view.findViewById(R.id.auto_reply_duration);
        minutesRadioButton = (RadioButton) view.findViewById(R.id.auto_reply_minutes);
        hoursRadioButton = (RadioButton) view.findViewById(R.id.auto_reply_hours);
        saveButton = (Button) view.findViewById(R.id.auto_reply_save_button);

        minutesRadioButton.setChecked(true);
        saveButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {

        String text = editText.getText().toString();
        String durationText = editDuration.getText().toString();
        boolean useMinutes = minutesRadioButton.isChecked();
        boolean useHours = hoursRadioButton.isChecked();

        if(text.isEmpty()) {
            editText.setError("Message is required");
            return;
        }

        if(durationText.isEmpty()) {
            editDuration.setError("Duration is required");
        }

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
