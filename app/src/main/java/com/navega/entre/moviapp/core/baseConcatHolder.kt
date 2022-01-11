package com.navega.entre.moviapp.core

import android.view.View
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

abstract class baseConcatHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(adapter: T)
}