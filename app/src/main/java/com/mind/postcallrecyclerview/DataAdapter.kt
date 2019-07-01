package com.mind.postcallrecyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_data_display.view.*
import java.util.*

class DataAdapter : RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    val userDataList = ArrayList<UserSalDetailsResponce>()
    private val TAG = MainActivity::class.java.simpleName

    fun addDataList(userSalDetailsResponce: UserSalDetailsResponce) {
        userDataList.add(userSalDetailsResponce)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.row_data_display, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.bindItem(userDataList[i])
    }

    override fun getItemCount(): Int {
        return userDataList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(dataBean: UserSalDetailsResponce) {

            itemView.tvUserID.text = dataBean.id
            Log.i(TAG, "tvUserID : " + dataBean.id)
            itemView.tvSalary.text = dataBean.salary
            itemView.tvAge.text = dataBean.age
            itemView.tvUserName.text = dataBean.name
        }
    }
}