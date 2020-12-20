package com.example.howareyou

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.howareyou.Model.*
import com.example.howareyou.Util.App
import com.example.howareyou.Util.EndlessRecyclerViewScrollListener
import com.example.howareyou.network.RetrofitClient
import com.example.howareyou.network.ServiceApi
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_home_all.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import kotlin.collections.ArrayList

class HomeFreeFragment : HomeBaseFragment() {

    // view가 전부 생성된 뒤에 호출
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showProgress(true)
        App.prefs.myCode = App.prefs.codeFree
        postingDTOlist.clear()
        initAdapter()
        loadSelectedPosting()
    }

    override fun onResume() {
        super.onResume()
        Log.e("Free","OnResume")
        App.prefs.myCode = App.prefs.codeFree
        forceTouch()
    }

}

