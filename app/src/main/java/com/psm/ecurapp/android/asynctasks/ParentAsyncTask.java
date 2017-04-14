package com.psm.ecurapp.android.asynctasks;

import android.app.Activity;
import android.os.AsyncTask;

import com.psm.ecurapp.utils.InternetUtil;

/**
 * Created by pmaestri on 3/15/2017.
 */

public class ParentAsyncTask<T,U,V> extends AsyncTask<T, U, V> {

    protected Activity activity;
    protected boolean hasInternet;

    protected boolean validateInternet() {
        hasInternet = InternetUtil.isOnline(activity);
        return hasInternet;
    }

    @Override
    protected V doInBackground(T... ts) {
        validateInternet();
        return null;
    }

    @Override
    protected void onPostExecute(V v) {
        if (!hasInternet) {
            //TODO: show toast error
        }
    }



}
