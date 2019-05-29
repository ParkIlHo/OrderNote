package com.ian.ordernote.db

import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.lang.Exception
import com.ian.ordernote.db.DB.DatabaseHelper
import java.nio.file.Files.delete

class DB(context : Context) {

    var mDbHelper: DatabaseHelper? = null
    var mDb: SQLiteDatabase? = null
    var mContext: Context? = null
    var db: DB? = null

    init {
        this.mContext = context
    }

    fun getInstance(context: Context): DB {
        if(db == null) {
            db = DB(context)
        }
        return db as DB
    }

    @Synchronized
    @Throws(SQLException::class)
    fun open(): DB {
        if (mDbHelper == null) {
            mDbHelper = DatabaseHelper(this.mContext)
            mDb = mDbHelper?.getWritableDatabase()
        }
        return this
    }

    @Throws(SQLException::class)
    fun close(): DB {
        mDb?.close()
        mDbHelper?.close()
        mDbHelper = null

        return this
    }

    private fun chkDB() {
        try {
            if (mDb == null) {
                this.open()
            }
            if (mDb?.isOpen() === false) {
                this.open()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun clearDb(): Int {
        chkDB()

        var rtn = -1
        mDb?.beginTransaction()

        try {
            rtn = mDb?.delete(DBConfig().TB_CUSTOMER, null, null)!!

            mDb?.setTransactionSuccessful()
        } finally {
            mDb?.endTransaction()
        }
        return rtn
    }


    class DatabaseHelper(context: Context?) : SQLiteOpenHelper(context, DBConfig().DB_NAME, null, DBConfig().DB_VERSION) {

        var mDBContext: Context? = null

        var arr_sql_tabel: List<String> = listOf(DBConfig().CREATE_ORDER)

        init {
            mDBContext = context
        }
        override fun onCreate(db: SQLiteDatabase?) {
            Log.e("DB onCreate() ::: ", "onCreate : " + arr_sql_tabel[0])
            execRawQuery(db, arr_sql_tabel)

            Log.d("DB onCreate() ::: ", "db : " + db)
            Log.d("DB onCreate() ::: ", "Creating database version : " + DBConfig().DB_VERSION)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            Log.d("DB onUpgrade", "db : " + db)
            Log.d("DB onUpgrade", "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data")

//            if (oldVersion < 2) {
//                String[] arr_sql = {
//                        DBConfig.CREATE_FEEDBACKINFO + ";",
//                        "PRAGMA user_version = 2;"
//                };
//                execRawQuery(db, arr_sql);
//            }
        }

        fun execRawQuery(db: SQLiteDatabase?, arr_sql: List<String>) {
            if(arr_sql != null) {
                for (sql:String in arr_sql) {
                    try {
                        if(db != null) {
                            db.execSQL(sql)
                        } else {
                            Log.e("DB is null", "sql : " + sql)
                        }
                    } catch (e : Exception) {
                        Log.e("exception", "SQL Error : " + sql)
                        Log.e("exception", "SQL Error : " + e.toString())
                    }
                }
            }
        }
    }
}