package com.innowaves.asyncsample.BusinessLogic;

import android.app.Activity;

import com.innowaves.asyncsample.MainActivity;
import com.innowaves.asyncsample.utils.ConfigurableOps;
import com.innowaves.asyncsample.utils.ConnectionDetector;
import com.innowaves.asyncsample.utils.NetworkCall;
import com.innowaves.asyncsample.utils.ProjectUtils;
import com.innowaves.asyncsample.utils.Response;
import com.loopj.android.http.RequestParams;

import java.lang.ref.WeakReference;

/**
 * Created by GEORGE on 10/1/2015.
 * email: georgethms10@gmail.com
 * Business Logic class for MainActivity
 * This class binds all the business logic associated with MainActivity
 */
public class MainActivityBL implements ConfigurableOps, Response {

    private final int PERSON_RESPONSE_CODE = 100;

    MainActivity main;
    ConnectionDetector con;
    private WeakReference<MainActivity> mActivity;
    private Response resp;

    @Override
    public void onConfiguration(Activity activity) {

        main = (MainActivity) activity;
        resp = this;
        con=new ConnectionDetector(main);
        mActivity = new WeakReference<>((MainActivity) activity);
    }

    @Override
    public void onSuccess(String response, int code) {
        switch (code) {
            case PERSON_RESPONSE_CODE:
                //parse with gson and display result
                mActivity.get().setResponsetoTextView(response);
                break;
            default:
                break;
        }

    }

    @Override
    public void onFail(String response, int code) {
        switch (code) {
            case PERSON_RESPONSE_CODE:
                //parse with gson and display result
                mActivity.get().showMessage("Request failed");
                break;
            default:
                break;
        }
    }

    //NetworkCall
    public void callServer() {

        if (con.isConnectingToInternet())
        {
            new NetworkCall(ProjectUtils.SERVER + ProjectUtils.PERSON_LINK, resp, PERSON_RESPONSE_CODE, main);

            RequestParams params=new RequestParams();
            params.put("key1","value1");
            params.put("key2","value2");
            params.put("key3","value3");

            /** For making a POST api call we should use a RequestParam object and pass the params to the NetworkCall call
             * FOR EXAMPLE
             *  new NetworkCall("URL",resp,PERSON_RESPONSE_CODE,params,context);
             *  Where
             *  URL: will be you URL head
             *  resp: is the Interface object for callback
             *  PERSON_RESPONSE_CODE: is the code for identifying the API callback
             *  params: is the key-values pair for a post api call
             *  context: is the context of your activity
             */

        }
        else
        {
            mActivity.get().showMessage("NO INTERNET");
        }

    }

}
