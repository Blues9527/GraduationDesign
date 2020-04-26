package com.blues.main.contact.view

import com.blues.main.contact.model.Contacter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.blues.myapplication.R
import kotlinx.android.synthetic.main.item_rv.view.*

class ContacterAdapter : BaseQuickAdapter<Contacter, BaseViewHolder>(R.layout.item_rv) {

    override fun convert(helper: BaseViewHolder, item: Contacter) {
        helper.itemView.run {
            item_iv.setImageResource(item.iconResId)
            item_tv.text = item.contactName
        }
    }
}