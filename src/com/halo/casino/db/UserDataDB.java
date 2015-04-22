package com.halo.casino.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserDataDB {
	private final String TAG = UserDataDB.this.getClass().getSimpleName();

	private static UserDataDB mUserDB;
	private SQLiteDatabase sqliteDB;
	private DBhelper dbHelper;

	private UserDataDB() {
	};

	public static UserDataDB getInstance() {
		if (mUserDB == null) {
			mUserDB = new UserDataDB();
		}
		return mUserDB;
	}

	public class DBhelper extends SQLiteOpenHelper {
		public DBhelper(Context context, String name, CursorFactory factory,
				int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(HaloQueryString.CREATE_HALO_USER_TABLE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS "
					+ HaloDBConstants.HALO_USER_TABLE);
			onCreate(db);
		}
	}

	public UserDataDB open(Context ctx) {
		if (sqliteDB == null || dbHelper == null) {
			dbHelper = new DBhelper(ctx, HaloDBConstants.HALO_DATABASE_NAME,
					null, HaloDBConstants.DATABASE_VERSION);
			sqliteDB = dbHelper.getWritableDatabase();
		}
		return this;
	}

	public long insert(String tableName, ContentValues cv) {
		Log.d(TAG, " table: " + tableName + " insert value");
		return sqliteDB.insert(tableName, null, cv);
	}

	/*public Cursor getSavedCards() {
		return sqliteDB.rawQuery(HaloQueryString.GET_SAVED_CARDS, null);
	}

	public Cursor getSavedCardById(String saveDraftDB) {
		return sqliteDB.rawQuery(HaloQueryString.GET_SAVED_CARD_BY_ID, new String[]{saveDraftDB});	
	}*/
	
	public void close() {
		if (sqliteDB != null) {
			sqliteDB.close();
			sqliteDB = null;
			dbHelper = null;
		}
	}
}
