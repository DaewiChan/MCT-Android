package com.example.mct.presentation.adapter.viewholder

import android.content.Context
import android.view.View
import android.widget.TextView
import com.example.mct.R
import com.example.mct.base.BaseViewHolder
import com.example.mct.data.model.TransactionHistoryData


class TransactionHistoryListsVH(
    context: Context,
    itemView: View
) : BaseViewHolder<TransactionHistoryData>(itemView) {

    var context: Context = context

    var tvSendDate = itemView.findViewById<TextView>(R.id.tvSendDate)
    var tvAmount = itemView.findViewById<TextView>(R.id.tvAmount)
    var tvAccount = itemView.findViewById<TextView>(R.id.tvAccount)
    var tvStatus = itemView.findViewById<TextView>(R.id.tvStatus)

    override fun setData(data: TransactionHistoryData) {
        mData = data

        if(data.date !=null){
            tvSendDate.text = data.date
        }else{
            tvSendDate?.text="___"
        }

        if(data.amount != null){
            tvAmount.text = data.amount
        }else{
            tvAmount?.text="___"
        }

        if(data.account !=null){
            tvAccount.text = data.account
        }else{
            tvAccount?.text="___"
        }

        if(data.status !=null){
            tvStatus.text = data.status
        }else{
            tvStatus?.text="___"
        }
    }

    override fun onClick(v: View) {
    }
}