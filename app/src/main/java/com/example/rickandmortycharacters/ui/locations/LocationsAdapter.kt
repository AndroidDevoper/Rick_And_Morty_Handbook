package com.example.rickandmortycharacters.ui.locations

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.rickandmortycharacters.databinding.ItemLocationBinding
import com.example.rickandmortycharacters.model.location.Location
import com.example.rickandmortycharacters.model.location.PageLocation

class LocationsAdapter(private val inflater: LayoutInflater,
                       private val callback: CallBack) : BaseAdapter() {

    interface CallBack {
        fun onNextPage(num: Int)
        fun onLocationSelect(location: Location)
    }

    private var listPages: ArrayList<PageLocation> = ArrayList()
    private var listLocations: ArrayList<Location> = ArrayList()


    fun addPage(data: PageLocationsData) {
        listPages = data.listPages
        listLocations = data.listLocations
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return listLocations.size
    }

    override fun getItem(p0: Int): Any {
        return listLocations[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val binding = ItemLocationBinding.inflate(inflater, p2, false)
        val location = listLocations[p0]
        binding.itemLocationIsName.text = location.name
        binding.itemLocationIsType.text = location.type
        binding.itemLocationIsDimension.text = location.dimension
        binding.root.setOnClickListener {
            callback.onLocationSelect(location)
        }
        return binding.root
    }

    override fun getItemViewType(position: Int): Int {
        if (position == listLocations.size - 4 && listPages.size < listPages[0].info.pages)
            callback.onNextPage(listPages.size + 1)
        return super.getItemViewType(position)
    }
}