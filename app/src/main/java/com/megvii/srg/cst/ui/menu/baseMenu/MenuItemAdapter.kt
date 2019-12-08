package com.megvii.srg.cst.ui.menu.baseMenu

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.megvii.srg.cst.R
import kotlinx.android.synthetic.main.item_main_grid.view.*

class MenuItemAdapter(internal var mContext: Context, internal var mMenuList: List<MenuItem>?// 传过来的数据
) : RecyclerView.Adapter<MenuItemAdapter.MenuViewHolder>() {
    private val IMAGE_WIDTH = 100
    private val IMAGE_HIGHT = 100

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.item_main_grid, parent,false)
        return MenuViewHolder(view)
    }

    override fun getItemCount(): Int {
       return mMenuList!!.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val top = mContext.resources.getDrawable(mMenuList!![position].iconId)

        top.setBounds(0, 0, IMAGE_HIGHT, IMAGE_WIDTH)

        holder.icon!!.setCompoundDrawables(null, top, null, null)
        holder.icon!!.text = (position + 1).toString() + "-" + mMenuList!![position].title

        holder.cls = mMenuList!![position].activity
        holder.parameter = mMenuList!![position].parameter

        holder.icon!!.setOnClickListener {
            if (holder.cls != null) {
                val `in` = Intent(mContext, holder.cls)
                val parameter = holder.parameter
                if (parameter?.isNotEmpty() == true) {
                    for (key in parameter!!.keys) {
                        val o = parameter[key]
                        if (o is String) {
                            `in`.putExtra(key, o)
                        } else if (o is Int) {
                            `in`.putExtra(key, o)
                        }
                    }
                }
                mContext.startActivity(`in`)
            }
        }

    }

    class MenuViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var icon: Button? = itemView.iv_icon
        var cls: Class<*>? = null
        var parameter: Map<String, *>? = null
    }

}
