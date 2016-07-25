package br.com.interaje.mypersonalwallet.databases;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by rayquaza on 25/07/16.
 */
public class Database {
    private SQLiteDatabase sqld;
    private DatabaseHelper databaseHelper;

    public Database(DatabaseHelper manager) {
        this.databaseHelper = manager;
    }

    public void open() {
        if (databaseHelper != null) {
            sqld = databaseHelper.getWritableDatabase();
        }
    }

    public SQLiteDatabase get() {
        if (sqld != null && sqld.isOpen()) {
            return sqld;
        }
        return null;
    }

    public void close() {
        if (databaseHelper != null) {
            databaseHelper.close();
        }
    }
}
