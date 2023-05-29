package com.example.musicplayer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.databinding.FoldersListAdapterBinding

class FoldersListAdapter(

    private var foldersListAdapter: ArrayList<Folders>,
    private val options: FoldersOptions,


    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val  binding = FoldersListAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemViewHolder(binding)
    }


    override fun getItemCount(): Int {

        return foldersListAdapter.size
    }

    class ItemViewHolder(val binding: FoldersListAdapterBinding) : RecyclerView.ViewHolder(binding.root) {

    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ItemViewHolder

        holder.binding.foldersName.text=foldersListAdapter[position].name


        holder.binding.root.setOnClickListener()
        {

            options.itemClicked(foldersListAdapter[position],position)
        }

        holder.binding.folderMenuOptions.setOnClickListener(){

            options.menuItemClicked(foldersListAdapter[position],position)
        }

    }


}