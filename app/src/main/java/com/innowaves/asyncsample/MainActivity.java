package com.innowaves.asyncsample;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.innowaves.asyncsample.BusinessLogic.MainActivityBL;
import com.innowaves.asyncsample.utils.GenericActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends GenericActivity<MainActivityBL> {

    /**
     * Created by GEORGE on 10/1/2015.
     * MainActivity
     * Controller which calls server and handles UI
     */
    @Bind(R.id.text_response)
    TextView textResponse;

    @Bind(R.id.network_call)
    Button networkCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, MainActivityBL.class);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    //Method to set response to TextView
    public void setResponsetoTextView(String response)
    {
        textResponse.setText(response);
    }

    @OnClick(R.id.network_call)
    public void CallNetwork()
    {
        //call server
        getOps().callServer();
    }


}
