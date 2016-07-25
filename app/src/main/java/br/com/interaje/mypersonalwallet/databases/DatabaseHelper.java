package br.com.interaje.mypersonalwallet.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rayquaza on 25/07/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static int VERSION_DB = 2;
    public static String DB_NAME = "walletBank.db";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table balance (value real(255)); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Atualiza a versão do banco.
    }
}
