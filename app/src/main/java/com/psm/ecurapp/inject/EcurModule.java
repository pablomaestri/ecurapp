package com.psm.ecurapp.inject;

import android.content.Context;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.psm.ecurapp.EcurApplication;

import retrofit2.Retrofit;

public class EcurModule extends AbstractModule {

	private final EcurApplication application;

    public EcurModule(EcurApplication application) {
        this.application = application;
    }

    private Retrofit retrofit;

    @Override
    protected void configure() {

    	//Services
    	//bind(UserService.class).to(UserServiceImpl.class).in(Singleton.class);


        //Helpers
        //bind(DatabaseHelper.class).toInstance(new DatabaseHelper(getContext()));

        //Retrofit
        /*retrofit = new Retrofit.Builder()
                .baseUrl(getContext().getResources().getString(R.string.server_url))
                .addConverterFactory(JacksonConverterFactory.create())
                .build();*/

        //bind(UserRest.class).toInstance(retrofit.create(UserRest.class));

    }

    @Provides
    @Singleton
    protected Context getContext() {
        return application;
    }


}
