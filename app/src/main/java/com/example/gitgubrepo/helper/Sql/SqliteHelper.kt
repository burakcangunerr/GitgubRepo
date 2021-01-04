package com.example.gitgubrepo.helper.Sql

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SqliteHelper(mContext: Context) : SQLiteOpenHelper(mContext, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "repos.db"
    }

        override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(SqliteTable.DB_REPOS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + SqliteTable.DB_REPOS);
        onCreate(db);
    }

    fun insertData(table: String, values: ContentValues) : Boolean {
        var db : SQLiteDatabase = this.writableDatabase
        var result : Long = db.insert(table, null, values)
        if(result == -1L){
            Log.d(TAG, "failed to save data!")
            return false
        }else{
            Log.d(TAG, "save data successful")
            return true
        }

    }

    fun deleteReposData(id: String) : Boolean {
        var db : SQLiteDatabase = this.writableDatabase
        return db.delete(SqliteTable.TABLE_REPOS, SqliteTable.COL_REPOS_ID + " =? ", arrayOf(id)) > 0

    }

    fun getAllRepos(): ArrayList<String>? {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM " + SqliteTable.TABLE_REPOS, null)
        val array : ArrayList<String> = arrayListOf<String>()
        if(cursor!!.moveToFirst()){
            cursor!!.moveToFirst()
            array.add((cursor.getString(cursor.getColumnIndex(SqliteTable.COL_REPOS_ID))))
            while (cursor.moveToNext()) {
                array.add((cursor.getString(cursor.getColumnIndex(SqliteTable.COL_REPOS_ID))))
            }
            cursor.close()
        }
        db.close()
        return array
    }

}