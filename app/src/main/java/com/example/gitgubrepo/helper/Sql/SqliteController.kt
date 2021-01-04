package com.example.gitgubrepo.helper.Sql

import android.content.ContentValues
import android.content.Context


@SuppressWarnings("WeakerAccess")
class SqliteController(mContext: Context) {
    private val sqliteHelper: SqliteHelper
    var context: Context

    fun saveReposData(reposId: String): Boolean {
        val values = ContentValues()
        values.put(SqliteTable.COL_REPOS_ID, reposId)
        return sqliteHelper.insertData(SqliteTable.TABLE_REPOS, values)
    }


    fun deleteReposData(reposId: String): Boolean {
        return sqliteHelper.deleteReposData(reposId)
    }

    fun getAllRepos(): ArrayList<String>? {
        return sqliteHelper.getAllRepos()
    }

    init {
        context = mContext
        sqliteHelper = SqliteHelper(context)
    }
}