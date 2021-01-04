package com.example.gitgubrepo.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.gitgubrepo.R
import com.example.gitgubrepo.model.ReposModel
import kotlinx.android.synthetic.main.repos_item.view.*


class Adapter(context: Context, list: List<ReposModel>, val callbackAdapter: AdapterCallBack?) : RecyclerView.Adapter<Adapter.MyViewHolder>() {
    private val context: Context
    private var list: List<ReposModel> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.repos_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.reposName.setText(list[position].name)
        holder.cardView.setOnClickListener {
            callbackAdapter!!.onMethodCallback(position)
        }
        if(list[position].fav){
            holder.favImageView.visibility = View.VISIBLE
        } else{
            holder.favImageView.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun update(newlist: List<ReposModel>){
        list = newlist
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var reposName: TextView
        var cardView: CardView
        var favImageView : ImageView

        init {
            cardView = itemView.card_view
            reposName = itemView.text_view
            favImageView = itemView.image_view_fav
        }
    }

    init {
        this.context = context
        this.list = list
    }
}