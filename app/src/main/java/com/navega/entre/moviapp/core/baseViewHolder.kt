package com.navega.entre.moviapp.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class baseViewHolder <T>(itemView:View):RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: T)

}