package com.psm.ecurapp;

import android.app.Application;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.psm.ecurapp.inject.EcurModule;


public class EcurApplication extends Application {

	private Injector injector;

    @Override
    public void onCreate() {
        super.onCreate();
        injector = Guice.createInjector(new EcurModule(this));

    }

    public Injector getInjector() {
        return injector;
    }
	
}
