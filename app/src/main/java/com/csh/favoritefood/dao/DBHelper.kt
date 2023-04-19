package com.csh.favoritefood.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.csh.favoritefood.dto.Review

private const val TAG = "MemoDBHelper_싸피"
private const val DB_NAME = "RVDB"
private const val TABLE = "RVTABLE"
private const val ID = "id"
private const val TITLE = "title"
private const val RATING = "rating"
private const val MENU = "menu"
private const val CONTENT = "content"
private const val IMG = "img"
private const val LATITUDE = "latitude"
private const val LONGITUDE = "longitude"
lateinit var sqlDB: SQLiteDatabase
class DBHelper(context: Context): SQLiteOpenHelper(context, DB_NAME, null, 1) {

    private lateinit var db:SQLiteDatabase

    override fun onCreate(db: SQLiteDatabase) {
        // 테이블 생성 쿼리
        val query: String =
            "CREATE TABLE if not exists $TABLE ( id integer primary key autoincrement" +
                    ", $TITLE text" +
                    ", $RATING Long" +
                    ", $MENU text" +
                    ", $CONTENT text" +
                    ", $IMG text" +
                    ", $LATITUDE Long" +
                    ", $LONGITUDE Long);"

        db.execSQL(query)
    }
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {}
    override fun onOpen(db: SQLiteDatabase) {
        super.onOpen(db)
        sqlDB = db
        Log.d(TAG, "onOpen...")
    }

}