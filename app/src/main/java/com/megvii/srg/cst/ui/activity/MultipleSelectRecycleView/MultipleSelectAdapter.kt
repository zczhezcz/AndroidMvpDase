package com.megvii.srg.cst.ui.activity.MultipleSelectRecycleView

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import com.megvii.srg.cst.R
import com.megvii.srg.cst.entity.ResponseLockSscc
import kotlinx.android.synthetic.main.item_lock_sscc.view.*
import org.apache.commons.collections4.CollectionUtils


class MultipleSelectAdapter(private var mContext: Context, private var mCtvCheckAll: CheckedTextView,
                            var lockSsccList: List<ResponseLockSscc.LockSscc>) :
        RecyclerView.Adapter<MultipleSelectAdapter.TaskViewHolder>() {

    var onItemClick: (lockSscc: ResponseLockSscc.LockSscc) -> Unit = {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(mContext).inflate(
                R.layout.item_lock_sscc, parent, false
        )

        mCtvCheckAll.setOnClickListener {view->
            val ctvCheckAll = view as CheckedTextView
            ctvCheckAll.isChecked = !ctvCheckAll.isChecked
            setCheckList(ctvCheckAll.isChecked)
            notifyDataSetChanged()
        }

        return TaskViewHolder(itemView)
    }

    private fun setCheckList(isCheck: Boolean) {
        for (item in lockSsccList) {
            item.isCheck = isCheck
        }
    }

    override fun getItemCount(): Int {
        return lockSsccList.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {

        val lockSscc = lockSsccList[position]
        holder.tv_sku_name.isChecked = lockSscc.isCheck
        holder.tv_sku_name.text = lockSscc.skuName
        holder.tv_id_num.text = (position+1).toString()

        holder.tv_sku_name.setOnClickListener { view ->
            val ctvCheck = view as CheckedTextView

            ctvCheck.isChecked = !ctvCheck.isChecked
            lockSsccList[position].isCheck = ctvCheck.isChecked
            setCheckAllStatus()

            onItemClick.invoke(lockSscc)
        }
    }

    inner class TaskViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var tv_sku_name = item.ctv_sku_name
        var tv_id_num = item.tv_id_num
    }

    private fun setCheckAllStatus() {
        if (CollectionUtils.isEmpty(lockSsccList)) {
            return
        }
        mCtvCheckAll.isChecked = (getCheckedCount() == lockSsccList.size)
    }

    private fun getCheckedCount(): Int {
        var result = 0
        for (item in lockSsccList) {
            if (item.isCheck) {
                ++result
            }
        }
        return result
    }

    fun getSelectOrder(): MutableList<ResponseLockSscc.LockSscc> {
        var result = mutableListOf<ResponseLockSscc.LockSscc>()
        for (item in lockSsccList) {
            if (item.isCheck) {
                result.add(item)
            }
        }
        return result
    }
}