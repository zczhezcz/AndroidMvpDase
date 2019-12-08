package com.megvii.srg.cst.ui.menu.baseMenu

import android.graphics.Canvas
import android.graphics.Paint
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.megvii.srg.cst.R

class GirdMenuDecoration : RecyclerView.ItemDecoration() {



    /**
     * 复写onDraw方法，从而达到在每隔条目的被绘制之前（或之后），让他先帮我们画上去几根线吧
     *
     * @param c
     * @param parent
     * @param state
     */
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State?) {
        //先初始化一个Paint来简单指定一下Canvas的颜色，就黑的吧！
        val paint = Paint()
        paint.color = parent.context.resources.getColor(R.color.background_gray)
        paint.strokeWidth = 1f
        //获得RecyclerView中总条目数量
        val childCount = parent.adapter.itemCount
        val spanCount = (parent.layoutManager as GridLayoutManager).spanCount

        //遍历一下
        for (i in 0 until childCount) {
            //获得子View，也就是一个条目的View，准备给他画上边框
            val childView = parent.getChildAt(i)
            if (childView != null) {
                //先获得子View的长宽，以及在屏幕上的位置，方便我们得到边框的具体坐标
                val x = childView.x
                val y = childView.y
                val width = childView.width
                val height = childView.height

                //根据这些点画条目的四周的线 上 左 右 下
                c.drawLine(x, y, x + width, y, paint)
                //c.drawLine(x, y, x, y + height, paint);
                if ((i + 1) % spanCount != 0) {
                    c.drawLine(x + width, y, x + width, y + height, paint)
                }
                c.drawLine(x, y + height, x + width, y + height, paint)
            }
        }
        super.onDraw(c, parent, state)
    }
}