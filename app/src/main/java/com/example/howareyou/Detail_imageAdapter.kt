package com.example.howareyou

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.howareyou.Model.Comment
import com.example.howareyou.Model.ImageDTO
import com.example.howareyou.Util.App
import com.example.howareyou.Util.OnSingleClickListener
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.item_comment.view.*
import kotlinx.android.synthetic.main.item_imageshow.view.*
import kotlinx.android.synthetic.main.item_imageupload.view.*
import kotlinx.android.synthetic.main.item_recomment.view.*
import kotlin.collections.ArrayList

class Detail_imageAdapter(val context: Context, val uriList : ArrayList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    val TAG : String = "Detail_imageAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var view = LayoutInflater.from(context).inflate(R.layout.item_imageshow,parent,false)
        return ImageViewHolder(view)

    }

    inner class ImageViewHolder(view : View) : RecyclerView.ViewHolder(view)

    override fun getItemCount(): Int {
        return uriList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        var view = holder.itemView
        System.out.println("good"+uriList)
        Glide.with(view).load(uriList[position]).into(view.imageshow_imageview)

    }

}