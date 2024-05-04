package com.example.appmusic

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import com.squareup.picasso.Picasso

class TrackAdapter(context: Context, val listTrack: ArrayList<Tracks>, val genres: List<String>, val countries: List<String>)
    : ArrayAdapter<Tracks>(context, 0, listTrack) {

    override fun getCount(): Int {
        return super.getCount()
    }

    override fun getItem(position: Int): Tracks? {
        return super.getItem(position)
    }

    override fun getItemId(position: Int): Long {
        return listTrack[position].TrackID.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_track, parent, false)
            holder = ViewHolder()
            holder.imageView = view.findViewById(R.id.img)
            holder.tvtrackID = view.findViewById(R.id.tvtrackID)
            holder.tvtrackName = view.findViewById(R.id.tvtrackName)
            holder.tvtrackArtist = view.findViewById(R.id.tvtrackArtist)
            holder.tvtrackReleaseDay = view.findViewById(R.id.tvtrackReleaseDay)
            holder.spinnerCountry = view.findViewById(R.id.spinnerCountry)
            holder.spinnerGenre = view.findViewById(R.id.spinnerGenre)
            holder.tvtrackView = view.findViewById(R.id.tvtrackView)
            view.tag = holder
        } else {
            view = convertView
            holder = convertView.tag as ViewHolder
        }

        val track = listTrack[position]

        // Hiển thị dữ liệu từ đối tượng Track lên các thành phần View
        holder.tvtrackID.text = track.trackID
        holder.tvtrackName.text = track.trackName
        holder.tvtrackArtist.text = track.trackArtist
        holder.spinnerGenre.text = track.trackGenre
        holder.spinnerCountry.text = track.trackCountry
        holder.tvtrackReleaseDay.text = track.trackReleaseDate
        holder.tvtrackView.text = track.trackView

        // Sử dụng Picasso để tải và hiển thị hình ảnh từ URL
        Picasso.get()
            .load(track.trackImg)
            .into(holder.imageView)

        return view
    }
}
    private class ViewHolder {
        lateinit var imageView: ImageView
        lateinit var tvtrackID: TextView
        lateinit var tvtrackName: TextView
        lateinit var tvtrackArtist: TextView
        lateinit var spinnerGenre: TextView
        lateinit var spinnerCountry: TextView
        lateinit var tvtrackReleaseDay: TextView
        lateinit var tvtrackView: TextView
    }