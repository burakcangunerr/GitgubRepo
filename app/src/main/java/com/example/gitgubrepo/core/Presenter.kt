package com.example.gitgubrepo.core

import android.content.Context
import com.example.gitgubrepo.core.GetDataContract.onGetDataListener
import com.example.gitgubrepo.model.ReposModel

class Presenter(private val mGetDataView: GetDataContract.View) : GetDataContract.Presenter, onGetDataListener {

    private var  mIntractor: Intractor

    init {
        mIntractor = Intractor(this)
    }

    override fun getDataFromURL(context: Context?, username: String?) {
        mIntractor.initRetrofitCall(context, username)
    }

    override fun onSuccess(message: String?, list: List<ReposModel>) {
        mGetDataView.onGetDataSuccess(message, list)
    }

    override fun onFailure(message: String?) {
        mGetDataView.onGetDataFailure(message)
    }

}