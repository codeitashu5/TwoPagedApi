package com.ashupandey.twopageapi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycle_view_layout.view.*

class CustonAdapter(val list : MutableList<Data>?
): RecyclerView.Adapter<CustonAdapter.CustomViewHolder>() {

   inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.recycle_view_layout,
            parent,
            false)
       return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
          holder.itemView.em.text = "  email: " + list?.get(position)?.email
          holder.itemView.name.text =  "  name: " + list?.get(position)?.first_name + " " + list?.get(position)?.last_name
          holder.itemView.image.setImageResource(R.drawable.ic_launcher_foreground)
          holder.itemView.ids.text = "  id: "+ list?.get(position)?.id.toString()
    }

    override fun getItemCount(): Int {
  if(list==null)
    return 0
        else
            return list.size


}
}