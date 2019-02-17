package com.guillotjeremie.darksky.presentation.features.week

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.guillotjeremie.darksky.R
import com.guillotjeremie.darksky.data.extension.toDayLetterDate
import com.guillotjeremie.darksky.data.model.DetailWeather
import kotlinx.android.synthetic.main.item_small_detail.view.*
import kotlin.math.roundToInt


class WeekAdapter(private val listener: WeekListener) : RecyclerView.Adapter<ViewHolder>() {

    private var items = ArrayList<DetailWeather>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_small_detail, parent, false))

    fun setItems(items: ArrayList<DetailWeather>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(items[position]) {
            holder.itemView.txt_small_day.text = this.time.toDayLetterDate()
            val temp = "${this.temperatureLow.roundToInt()}°/${this.temperatureHigh.roundToInt()}°"
            holder.itemView.txt_small_temperature.text = temp
            holder.itemView.img_small_weather.setImageDrawable(this.getDrawableIcon(holder.itemView.context))

            holder.itemView.setOnClickListener { listener.showDetail(this.summary) }
        }
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

