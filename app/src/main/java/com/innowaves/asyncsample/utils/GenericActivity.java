package com.innowaves.asyncsample.utils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class GenericActivity<Operation  extends ConfigurableOps> extends AppCompatActivity{

	Operation operation_instance;
	
	protected void onCreate(Bundle savedInstanceState,Class<Operation> opsType) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		try {
			initialize(opsType);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Return the initialized OpsType instance for use by the application.
	 */
	public Operation getOps() {
		return operation_instance;
	}

	
	private void initialize(Class<Operation> opsType)
			throws InstantiationException, IllegalAccessException {
		// Create the OpsType object.
		operation_instance = opsType.newInstance();
		operation_instance.onConfiguration(this);
	}


	public void showMessage(String message)
	{
		Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
	}


	public void Finish()
	{
		finish();
	}
}
