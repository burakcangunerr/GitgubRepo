package com.example.gitgubrepo.helper.global

import com.example.gitgubrepo.model.ReposModel

class Global
{
    companion object{
        var  ReposModelArray : List<ReposModel> = ArrayList()

        fun clear()
        {
            ReposModelArray = ArrayList()
        }
    }
}