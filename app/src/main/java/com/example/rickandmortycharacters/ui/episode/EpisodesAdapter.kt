package com.example.rickandmortycharacters.ui.episode

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.rickandmortycharacters.databinding.ItemEpisodeBinding
import com.example.rickandmortycharacters.model.episode.Episode
import com.example.rickandmortycharacters.model.episode.PageEpisode

class EpisodesAdapter(private val inflater: LayoutInflater,
                      private val callback: CallBack): BaseAdapter() {

    interface CallBack {
        fun onNextPage(num: Int)
        fun onEpisodeSelect(episode: Episode)
    }

    private var listPages: ArrayList<PageEpisode> = ArrayList()
    private var listEpisode: ArrayList<Episode> = ArrayList()


    fun addPage(data: PageEpisodesData) {
        listPages = data.listPages
        listEpisode = data.listEpisode
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return listEpisode.size
    }

    override fun getItem(p0: Int): Any {
        return listEpisode[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val binding = ItemEpisodeBinding.inflate(inflater, p2, false)
        val episode = listEpisode[p0]
        binding.itemEpisodeTvName.text = episode.name
        binding.itemEpisodeTvNum.text = episode.episode
        binding.itemEpisodeTvDate.text = episode.air_date
        binding.root.setOnClickListener {
            callback.onEpisodeSelect(episode)
        }
        return binding.root
    }

    override fun getItemViewType(position: Int): Int {
        if (position == listEpisode.size - 4 && listPages.size < listPages[0].info.pages)
            callback.onNextPage(listPages.size + 1)
        return super.getItemViewType(position)
    }
}