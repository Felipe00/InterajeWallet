package br.com.interaje.mypersonalwallet.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import br.com.interaje.mypersonalwallet.databases.Database;

/**
 * Created by rayquaza on 25/07/16.
 */
public class BalanceDAOImpl implements BalanceDAO {
    @Override
    public Double getBalance(Database database) {
        Cursor cursor = null;
        Double balanceValue = 0D;

        database.open();

        try {
            cursor = database.get().rawQuery("select * from balance;", null);
            cursor.moveToFirst();
        } catch (Exception e) {
            e.getCause();
        }

        if (cursor != null && !cursor.isClosed()) {
            while (cursor.moveToNext()) {
                balanceValue = cursor.getDouble(cursor.getColumnIndex("value"));
            }
            cursor.close();
        }

        database.close();
        return balanceValue;
    }

    @Override
    public void intertBalance(Double value, Database database) {
        ContentValues cv = new ContentValues();
        database.open();
        try {
            if (value != null) {
                cv.put("value", value);
                database.get().insert("balance", null, cv);
            }
        } catch (Exception e) {
            e.getCause();
        } finally {
            database.close();
        }
    }
}
