package com.nwpu.wsner.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nwpu.wsner.R;
import com.nwpu.wsner.constants.Url;
import com.nwpu.wsner.data.Fragments;
import com.nwpu.wsner.ui.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.InjectView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    SharedPreferences sharedPreferences;

    Button loginButton,signinButton;
    EditText userNameEditText,pwdEditText;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("test","onCreate");

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("test","onCREateView");
        View view=inflater.inflate(R.layout.fragment_login, container, false);

        init();
        signinButton = (Button) view.findViewById(R.id.signinButton);
        loginButton = (Button) view.findViewById(R.id.loginButton);
        pwdEditText = (EditText) view.findViewById(R.id.pwdEditText);
        userNameEditText = (EditText) view.findViewById(R.id.userNameEditText);
        Log.e("test","afterinit");


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("test","sigin onclick");
                if (userNameEditText.getText().equals(null)||pwdEditText.getText().equals(null)){
                    Toast.makeText(getActivity(),"请输入用户名和密码",Toast.LENGTH_LONG).show();
                }else {
                    sharedPreferences= getActivity().getPreferences(Context.MODE_WORLD_WRITEABLE);
                    SharedPreferences.Editor editor =sharedPreferences.edit();
                    editor.putString("name", String.valueOf(userNameEditText.getText()));
                    editor.putString("pwd", String.valueOf(pwdEditText.getText()));

                    AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
                    RequestParams requestParams = new RequestParams();
                    String URL= Url.LOGIN_URL+"username="+userNameEditText.getText()+"&password="+pwdEditText.getText();
                    Log.e("url",URL);
                    asyncHttpClient.post(URL,requestParams,new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int i, Header[] headers, byte[] bytes) {
                            try {
                                JSONObject jsonObject= new JSONObject(new String(bytes));
                                String s =jsonObject.getString("result");
                                Log.e("s",s);
                                if (s.equals("1")){
                                    Toast.makeText(getActivity(),"登录成功",Toast.LENGTH_LONG).show();

                                }else{
                                    Toast.makeText(getActivity(),"用户名或密码错误",Toast.LENGTH_LONG).show();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }

                        @Override
                        public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                            Toast.makeText(getActivity(),"请重试",Toast.LENGTH_LONG).show();

                        }
                    });
                }
            }
        });
        // Inflate the layout for this fragment
        return view;


    }
    public void init(){

    }
//


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @OnClick(R.id.signinButton)
//    public void signinButtononclick(){
//
//    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
