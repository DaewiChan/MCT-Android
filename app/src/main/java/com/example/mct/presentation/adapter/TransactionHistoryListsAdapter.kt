package com.example.mct.presentation.adapter

import android.content.Context
import android.view.ViewGroup
import com.example.mct.R
import com.example.mct.base.BaseRecyclerAdapter
import com.example.mct.data.model.TransactionHistoryData
import com.example.mct.presentation.adapter.viewholder.TransactionHistoryListsVH


class TransactionHistoryListsAdapter(
    context: Context
) : BaseRecyclerAdapter<TransactionHistoryListsVH, TransactionHistoryData>(context) {
    var context: Context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHistoryListsVH {
        val newsItemView =
            mLayoutInflator.inflate(R.layout.item_transaction_history_lists, parent, false)
        return TransactionHistoryListsVH(context,newsItemView)
    }
}