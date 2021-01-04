package com.example.gitgubrepo.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gitgubrepo.R
import com.example.gitgubrepo.adapters.Adapter
import com.example.gitgubrepo.adapters.AdapterCallBack
import com.example.gitgubrepo.core.GetDataContract
import com.example.gitgubrepo.core.Presenter
import com.example.gitgubrepo.helper.Sql.SqliteController
import com.example.gitgubrepo.helper.global.Global
import com.example.gitgubrepo.model.ReposModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity(), GetDataContract.View, AdapterCallBack {

    private var mPresenter: Presenter? = null
    private lateinit var adapter : Adapter
    private lateinit var recyclerView : RecyclerView
    private lateinit var editTextUsername : EditText
    private lateinit var linearLayoutManager : LinearLayoutManager
    private lateinit var listReposModel : List<ReposModel>
    private lateinit var backImageView : ImageView
    private lateinit var favImageView : ImageView
    private lateinit var toolbarTextView : TextView

    private var test : Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
        init()

    }
    private fun initUI()
    {
        recyclerView = recycler_view
        editTextUsername = edit_text_username
        backImageView = left_icon
        favImageView = image_view_fav
        toolbarTextView = text_view_toolbar
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.setHasFixedSize(true)
        mPresenter = Presenter(this)


    }

    private fun init()
    {
        backImageView.visibility = View.GONE
        favImageView.visibility = View.GONE
        toolbarTextView.text = "Home"
        mPresenter!!.getDataFromURL(applicationContext, editTextUsername.text.toString())
    }

    override fun onGetDataSuccess(message: String?, list: List<ReposModel>) {
        listReposModel = list
        adapter = Adapter(applicationContext, listReposModel, this)
        recyclerView.adapter = adapter
    }

    override fun onGetDataFailure(message: String?) {
        //show toast message
    }

    fun submit(view: View) {
        mPresenter!!.getDataFromURL(applicationContext, editTextUsername.text.toString())
        hideKeyboard()
    }

    private fun hideKeyboard() {
        if(currentFocus != null){
            val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }

    }


    override fun onMethodCallback(selected: Int) {
        val intent = Intent(this, RepoActivity::class.java)
        val selectedRepo = listReposModel[selected]
        intent.putExtra("selectedRepo", selectedRepo)
        intent.putExtra("selectedItem", selected)
        startActivityForResult(intent, 123)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            adapter.update(Global.ReposModelArray)
        }

    }
}