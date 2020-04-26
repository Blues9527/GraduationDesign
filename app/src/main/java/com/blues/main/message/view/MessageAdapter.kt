package com.blues.main.message.view

import com.blues.main.message.model.MessageEntity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.blues.myapplication.R
import kotlinx.android.synthetic.main.item_chat_rv.view.*

class MessageAdapter : BaseQuickAdapter<MessageEntity, BaseViewHolder>(R.layout.item_chat_rv) {

    override fun convert(helper: BaseViewHolder, item: MessageEntity) {
        helper.itemView.run {
            ivAvatar.setImageResource(item.avatarResId)
            tvUsername.text = item.username
            tvMessage.text = item.message
            tvDate.text = item.date
        }
    }
}