package com.example.gitgubrepo.activities

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gitgubrepo.R
import com.example.gitgubrepo.helper.Sql.SqliteController
import com.example.gitgubrepo.helper.global.Global
import com.example.gitgubrepo.model.ReposModel
import kotlinx.android.synthetic.main.activity_repo.*
import kotlinx.android.synthetic.main.toolbar.*

class RepoActivity : AppCompatActivity(){

    private var selectedReposModel: ReposModel? = null
    private lateinit var ownerTextView : TextView
    private lateinit var starTextView : TextView
    private lateinit var openIssuesTextView : TextView
    private lateinit var imageView: ImageView

    private lateinit var backImageView : ImageView
    private lateinit var favImageView : ImageView
    private lateinit var toolbarTextView : TextView

    private lateinit var sqliteController : SqliteController

    private var selectedItem : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)

        selectedReposModel = intent.getSerializableExtra("selectedRepo") as? ReposModel
        selectedItem = intent.getIntExtra("selectedItem", -1)

        initUI()
        init()
    }

    private fun initUI()
    {
        ownerTextView = text_view_owner
        starTextView = text_view_star
        openIssuesTextView = text_view_open_issues
        imageView = image_view
        backImageView = left_icon
        favImageView = image_view_fav
        sqliteController = SqliteController(this)
        toolbarTextView = text_view_toolbar

    }

    private fun init()
    {
        backImageView.visibility = View.VISIBLE
        backImageView.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }
        favImageView.visibility = View.VISIBLE
        if(selectedReposModel!!.fav){
            favImageView.setImageResource(R.drawable.ic_baseline_star_24_black)
        }else{
            favImageView.setImageResource(R.drawable.ic_baseline_star_24_white)
        }

        favImageView.setOnClickListener {
            if(selectedReposModel!!.fav){
                if(sqliteController.deleteReposData(selectedReposModel!!.id.toString())){
                    favImageView.setImageResource(R.drawable.ic_baseline_star_24_white)
                    if(selectedItem != -1) {
                        Global.ReposModelArray[selectedItem].fav = false
                        selectedReposModel!!.fav = false
                    }
                }
            }else{
                if(sqliteController.saveReposData(selectedReposModel!!.id.toString())){
                    favImageView.setImageResource(R.drawable.ic_baseline_star_24_black)
                    if(selectedItem != -1) {
                        Global.ReposModelArray[selectedItem].fav = true
                        selectedReposModel!!.fav = true
                    }

                }
            }
        }
        toolbarTextView.text = selectedReposModel!!.name
        ownerTextView.text = selectedReposModel!!.owner.login
        starTextView.text = "Stars: " + selectedReposModel!!.stargazersCount.toString()
        openIssuesTextView.text = "Open Issues: " + selectedReposModel!!.openIssues.toString()
        if(selectedReposModel!!.owner.avatarUrl != ""){
            DownloadImageByURL(imageView).execute(selectedReposModel!!.owner.avatarUrl)
        }

    }

    @SuppressLint("StaticFieldLeak")
    @Suppress("DEPRECATION")
    private inner class DownloadImageByURL(var imageView: ImageView) : AsyncTask<String, Void, Bitmap?>() {
        init {
            Toast.makeText(applicationContext, "Please wait, it may take a few minute...", Toast.LENGTH_SHORT).show()
        }
        override fun doInBackground(vararg urls: String): Bitmap? {
            val imageURL = urls[0]
            var image: Bitmap? = null
            try {
                val `in` = java.net.URL(imageURL).openStream()
                image = BitmapFactory.decodeStream(`in`)
            }
            catch (e: Exception) {
                Log.e("Error Message", e.message.toString())
                e.printStackTrace()
            }
            return image
        }
        override fun onPostExecute(result: Bitmap?) {
            imageView.setImageBitmap(result)
        }
    }

}