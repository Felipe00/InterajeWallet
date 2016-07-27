package br.com.interaje.mypersonalwallet.utils;

import android.app.Application;

import br.com.interaje.mypersonalwallet.models.Saldo;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by rayquaza on 27/07/16.
 */
public class MyApplicationContext extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // The RealmConfiguration is created using the builder pattern.
        // The Realm file will be located in Context.getFilesDir() with name "myrealm.realm"
        RealmConfiguration config = new RealmConfiguration.Builder(this)
                .name("myrealm.realm")
                .schemaVersion(1)
                .build();

        Realm.setDefaultConfiguration(config);
    }
}
