package com.example.gitgubrepo.helper.Sql


@SuppressWarnings("WeakerAccess")
class SqliteTable {

    companion object {
        const val TABLE_REPOS : String = "table_user"
        const val COL_REPOS_ID : String = "col_repos_id"
        const val DB_REPOS : String = "CREATE TABLE " + TABLE_REPOS + "(" + COL_REPOS_ID + " INTEGER PRIMARY KEY " + ")"

    }

}