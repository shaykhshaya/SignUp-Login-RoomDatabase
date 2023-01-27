package com.shaya.loginsignup.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shaya.loginsignup.data.friend.FriendData
import com.shaya.loginsignup.databinding.HomeScreenItemBinding
import com.shaya.loginsignup.utils.Utils.loadImageViaFile
import java.io.File

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {


    private var FriendDatas = mutableListOf<FriendData>()

    class HomeViewHolder(private val binding: HomeScreenItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(friendData: FriendData) {
            binding.apply {
                firstName.text = friendData.firstName
                lastName.text = friendData.lastName
                if (friendData.filePath.isNotEmpty()){
                   binding.imgHome.loadImageViaFile(File(friendData.filePath))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val layout = HomeScreenItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HomeViewHolder(layout)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val currentItem = FriendDatas[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return FriendDatas.size
    }

    fun setAuthors(authors: List<FriendData>) {
        this.FriendDatas = authors as MutableList<FriendData>
        notifyDataSetChanged()
    }
}