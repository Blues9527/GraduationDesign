package com.blues.main.dynamic.view

import com.blues.main.dynamic.model.Dynamic
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.blues.myapplication.R
import kotlinx.android.synthetic.main.item_iteraction_rv.view.*

class DynamicAdapter : BaseQuickAdapter<Dynamic, BaseViewHolder>(R.layout.item_iteraction_rv) {
    override fun convert(helper: BaseViewHolder, item: Dynamic) {
        helper.itemView.run {
            ivIcon.setImageResource(item.iconResId)
            tvUsername.text = item.username
            tvContent.text = item.content
            ivCover.setImageResource(item.coverResId)
            tvDate.text = item.date
            tvSource.text = item.source
        }
    }
}