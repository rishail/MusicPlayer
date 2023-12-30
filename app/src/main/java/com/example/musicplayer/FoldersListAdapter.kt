package com.example.musicplayer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.databinding.FoldersListAdapterBinding

class FoldersListAdapter(

    private var foldersModelListAdapter: ArrayList<FoldersModel>,
    private val options: FoldersCallBack,


    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val  binding = FoldersListAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemViewHolder(binding)
    }


    override fun getItemCount(): Int {

        return foldersModelListAdapter.size
    }

    class ItemViewHolder(val binding: FoldersListAdapterBinding) : RecyclerView.ViewHolder(binding.root) {

    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ItemViewHolder

        holder.binding.foldersName.text=foldersModelListAdapter[position].name


        holder.binding.root.setOnClickListener()
        {

            options.itemClicked(foldersModelListAdapter[position],position)
        }

        holder.binding.folderMenuOptions.setOnClickListener(){

            options.menuItemClicked(foldersModelListAdapter[position],position)
        }

    }


}