package com.example.gitgubrepo.core

import android.content.Context
import android.util.Log
import com.example.gitgubrepo.core.GetDataContract
import com.example.gitgubrepo.helper.RetrofitHelper.RetrofitHelper
import com.example.gitgubrepo.helper.Sql.SqliteController
import com.example.gitgubrepo.helper.global.Global
import com.example.gitgubrepo.model.ReposModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Intractor(private var mOnGetDatalistener: GetDataContract.onGetDataListener) : GetDataContract.Interactor {

    var allRepos: List<ReposModel> = ArrayList()
    var allFav : ArrayList<String> = ArrayList()
    private lateinit var sqliteController : SqliteController

    override fun initRetrofitCall(context: Context?, username: String?) {
        val call = RetrofitHelper.Call?.getRepos(username)

        if(call != null)
        {
            call.enqueue(object : Callback<List<ReposModel>> {
                override fun onResponse(
                        call: Call<List<ReposModel>>,
                        response: Response<List<ReposModel>>
                ) {
                    if(!response.body().isNullOrEmpty()){
                        allRepos = response.body()!!

                        sqliteController = SqliteController(context!!)
                        allFav = sqliteController.getAllRepos()!!
                        if (!allFav.isNullOrEmpty()){
                            for(i in 0..allRepos.size - 1){
                                if(allFav.contains(allRepos[i].id.toString())){
                                    allRepos[i].fav = true
                                }
                            }
                        }

                    }
                    Global.ReposModelArray = allRepos
                    mOnGetDatalistener.onSuccess("List Size: ", allRepos)
                }

                override fun onFailure(call: Call<List<ReposModel>>, t: Throwable) {
                    Log.e("aaaaaaaaa", "1111111111")
                    mOnGetDatalistener.onFailure(t.message.toString())
                }


            })
        }
        else
        {
            Log.e("aaaaaaaaa", "222222222222222222")
            mOnGetDatalistener.onFailure("Error call null")
        }
    }

}