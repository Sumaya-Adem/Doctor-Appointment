package com.example.doctorappointment.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

import com.bumptech.glide.request.RequestOptions
import com.example.doctorappointment.Model.DoctorsModel
import com.example.doctorappointment.databinding.ViewholderListBinding
import android.content.Context
import android.content.Intent
import com.example.doctorappointment.Activity.DetailsActivity


class DoctorsAdapter(private val items:MutableList<DoctorsModel>)
    : RecyclerView.Adapter<DoctorsAdapter.Viewholder>() {
//    private var context: Context? = null

    class Viewholder(val binding: ViewholderListBinding)
        :RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorsAdapter.Viewholder {

//       context =parent.context
        return Viewholder(ViewholderListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false))

    }

    override fun onBindViewHolder(holder: DoctorsAdapter.Viewholder, position: Int) {
        holder.binding.doctorName.text = items[position].Name
        holder.binding.specialistName.text = items[position].Special
        holder.binding.cost.text = items[position].Cost

        Glide.with(holder.itemView.context)
            .load(items[position].Picture)
            .apply(RequestOptions().transform(CenterCrop()))
            .into(holder.binding.doctorImage)

        holder.binding.root.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context,DetailsActivity::class.java)
            intent.putExtra("object",items[position])
            context?.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size
}