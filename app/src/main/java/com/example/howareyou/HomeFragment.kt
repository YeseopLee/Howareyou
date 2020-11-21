package com.example.howareyou

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.howareyou.Model.LoadPostDTO
import com.example.howareyou.Model.LoadPostItem
import com.example.howareyou.network.RetrofitClient
import com.example.howareyou.network.ServiceApi
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class HomeFragment : Fragment() {

    private var service: ServiceApi? = null
    val postingDTOlist: ArrayList<LoadPostItem> = arrayListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // retrofit 연결
        service = RetrofitClient.client!!.create(ServiceApi::class.java)

        //fragment view에 담는다
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_home, container, false )

        loadPosting()

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadPosting()
    }


    private fun loadPosting() {
        service?.getAllPost()?.enqueue(object : Callback<LoadPostDTO?> {
            override fun onResponse(
                call: Call<LoadPostDTO?>?,
                response: Response<LoadPostDTO?>

            ) {
                if(response.isSuccessful)
                {
                    val result: LoadPostDTO = response.body()!!
                    val postSize: Int = result.size-1

                    if(result.size != 0)
                    {
                        for (i in 0..postSize){

                            postingDTOlist?.add(LoadPostItem(result[i].id,result[i].title,result[i].content,result[i].author,result[i].code,result[i].comments,result[i].likeds,result[i].viewed,result[i].createdAt
                                ,result[i].header,result[i].user_id,result[i].is_delected))

                        }

                        // 어댑터 연결
                        attachAdapter()

                        showProgress(false)
                        //mAdapter?.notifyDataSetChanged()
                    }else{
                        //TODO
                    }

                }else {
                    // 실패시 resopnse.errorbody를 객체화
                    val gson = Gson()
                    val adapter: TypeAdapter<LoadPostDTO> = gson.getAdapter<LoadPostDTO>(
                        LoadPostDTO::class.java
                    )
                    try {
                        if (response.errorBody() != null) {
                            showProgress(false)
                            val result : LoadPostDTO = adapter.fromJson(response.errorBody()!!.string())

                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }

            }

            override fun onFailure(call: Call<LoadPostDTO?>?, t: Throwable) {
                Log.e("onFailure", t.message!!)
            }
        })

    }

    private fun attachAdapter(){
        //어댑터 연결
        home_recyclerview.adapter = HomeAdapter(activity!!,postingDTOlist)
        val lm = LinearLayoutManager(activity)
        home_recyclerview.layoutManager = lm
        home_recyclerview.setHasFixedSize(true)

        // 리사이클러뷰 역순 출력
        lm.reverseLayout = true
        lm.stackFromEnd = true
    }

    private fun showProgress(show: Boolean) {
        home_progressbar.visibility = (if (show) View.VISIBLE else View.GONE)
    }


}

