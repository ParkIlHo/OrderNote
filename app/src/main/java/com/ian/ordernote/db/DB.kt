package com.ian.ordernote.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.ian.ordernote.data.CustomerInfo
import com.ian.ordernote.data.OrderInfo
import java.lang.Exception
import com.ian.ordernote.db.DB.DatabaseHelper
import java.nio.file.Files.delete

class DB(context : Context?) {

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
            rtn = mDb?.delete(DBConfig().TB_ORDER, null, null)!!

            mDb?.setTransactionSuccessful()
        } finally {
            mDb?.endTransaction()
        }
        return rtn
    }

    fun setCustomer(customer: CustomerInfo): Long {
        chkDB()

        var rtn = -1L

        mDb?.beginTransaction()

        try {
            var whereClause = "" + DBConfig().CO_MOBILE + "=?"
            var whereArgs = arrayOf(customer.mobile)

            var values = ContentValues()

            values.put(DBConfig().CO_NAME, customer.name)
            values.put(DBConfig().CO_EMAIL, customer.email)
            values.put(DBConfig().CO_TEL, customer.tel)
            values.put(DBConfig().CO_MOBILE, customer.mobile)
            values.put(DBConfig().CO_OTHER, customer.other)

            rtn = mDb?.update(DBConfig().TB_CUSTOMER, values, whereClause, whereArgs)!!.toLong()

            if(rtn < 1) {
                Log.e("DB setCustomer", "insert :::${values.toString()}")
                rtn  = mDb?.insertOrThrow(DBConfig().TB_CUSTOMER, null, values)!!
            }

            mDb?.setTransactionSuccessful()
        } finally {
            mDb?.endTransaction()
        }

        return rtn
    }

    fun getCustomer(): ArrayList<CustomerInfo>? {
        var customerList = ArrayList<CustomerInfo>()

        chkDB()

        var sql = "SELECT * FROM ${DBConfig().TB_CUSTOMER}"

        Log.e("DB", "sql = ${sql}")

        var cursor = mDb?.rawQuery(sql, null)

        if(cursor?.count!! > 0) {
            while (cursor?.moveToNext()) {
                var customer = CustomerInfo()

                customer.name = cursor.getString(cursor.getColumnIndex(DBConfig().CO_NAME))
                customer.mobile = cursor.getString(cursor.getColumnIndex(DBConfig().CO_MOBILE))
                customer.tel = cursor.getString(cursor.getColumnIndex(DBConfig().CO_TEL))
                customer.email = cursor.getString(cursor.getColumnIndex(DBConfig().CO_EMAIL))
                customer.other = cursor.getString(cursor.getColumnIndex(DBConfig().CO_OTHER))

                customerList.add(customer)
            }

            cursor.close()
            return customerList
        } else {
            return ArrayList<CustomerInfo>()
        }
    }

    fun setOrder(order: OrderInfo): Long {
        chkDB()

        var rtn = -1L

        mDb?.beginTransaction()

        var isUpdate = if(order.index > -1) true else false

        try {
            var whereClause = "" + DBConfig().CO_INDEX + "=?"
            var whereArgs = arrayOf(order.index.toString())

            var values = ContentValues()

            if(isUpdate) {
                values.put(DBConfig().CO_INDEX, order.index)
            }

            values.put(DBConfig().CO_NAME, order.name)
            values.put(DBConfig().CO_EMAIL, order.email)
            values.put(DBConfig().CO_TEL, order.tel)
            values.put(DBConfig().CO_MOBILE, order.mobile)
            values.put(DBConfig().CO_PRODUCT_NAME, order.productName)
            values.put(DBConfig().CO_ORDER_DATE, order.orderDate)
            values.put(DBConfig().CO_RELEASE_SCHEDULE, order.releaseSchedule)
            values.put(DBConfig().CO_COAST_PRICE, order.coasePrice)
            values.put(DBConfig().CO_SELLING_PRICE, order.sellingPrice)
            values.put(DBConfig().CO_RELEASE_YN, order.releaseYN)
            values.put(DBConfig().CO_PRODUCT_IMAGE, order.productImage)
            values.put(DBConfig().CO_SHIPPING_ADDRESS, order.shippingAddress)
            values.put(DBConfig().CO_ACCOUNT_NAME, order.accountName)
            values.put(DBConfig().CO_CONTENT, order.content)
            values.put(DBConfig().CO_COLOR, order.color)
            values.put(DBConfig().CO_SIZE, order.size)
            values.put(DBConfig().CO_TRANSFORM, order.transform)
            values.put(DBConfig().CO_PROMISE_DATE, order.promiseDate)
            values.put(DBConfig().CO_OTHER, order.other)


            if(isUpdate) {
                rtn = mDb?.update(DBConfig().TB_ORDER, values, whereClause, whereArgs)!!.toLong()

                if(rtn < 1) {
                    Log.e("DB SetOrder", "insert :::${values.toString()}")
                    rtn  = mDb?.insertOrThrow(DBConfig().TB_ORDER, null, values)!!
                }
            } else {
                rtn  = mDb?.insertOrThrow(DBConfig().TB_ORDER, null, values)!!
            }

            mDb?.setTransactionSuccessful()
        } finally {
            mDb?.endTransaction()
        }

        return rtn
    }


    class DatabaseHelper(context: Context?) : SQLiteOpenHelper(context, DBConfig().DB_NAME, null, DBConfig().DB_VERSION) {

        var mDBContext: Context? = null

        var arr_sql_tabel: List<String> = listOf(DBConfig().CREATE_CUSTOMER, DBConfig().CREATE_ORDER)

        init {
            mDBContext = context
        }
        override fun onCreate(db: SQLiteDatabase?) {
            Log.e("DB onCreate() ::: ", "onCreate : " + arr_sql_tabel[0])
            Log.e("DB onCreate() ::: ", "onCreate : " + arr_sql_tabel[1])
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