package com.example.gitgubrepo.core

import android.content.Context
import com.example.gitgubrepo.model.ReposModel

interface GetDataContract {
    interface View {
        fun onGetDataSuccess(message: String?, list: List<ReposModel>)
        fun onGetDataFailure(message: String?)
    }

    interface Presenter {
        fun getDataFromURL(context: Context?, url: String?)
    }

    interface Interactor {
        fun initRetrofitCall(context: Context?, url: String?)
    }

    interface onGetDataListener {
        fun onSuccess(message: String?, list: List<ReposModel>)
        fun onFailure(message: String?)
    }
}