package com.example.myapplication

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_match_history_popup.*

class AdapterMatchHistory(private var match_list :ArrayList<GameLog>, val ctx: Context):
    RecyclerView.Adapter<AdapterMatchHistory.ViewHolder>() {

    private var onClickListener: OnClickListener? = null


    interface OnClickListener
    {
        fun onItemClick(index: Int)
    }


    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        val item = view.findViewById<LinearLayout>(R.id.match_list_item)
        var tv_winner = view.findViewById<TextView>(R.id.tv_winner_ad)
        //val typeface = Typeface.createFromAsset(assets, "fonts/yourfont.ttf")

        val tv_date = view.findViewById<TextView>(R.id.tv_date_ad)
        val tv_mode = view.findViewById<TextView>(R.id.tv_mode_ad)


        fun bindOnClickListener(onClickListener: OnClickListener?, index: Int)
        {
            item.setOnClickListener{view -> onClickListener?.onItemClick(index)}

        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val view = inflater.inflate(R.layout.adapter_match_history, parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv_winner.text = match_list[position].winner+" WON"
        holder.tv_date.text = match_list[position].date
        holder.tv_mode.text = match_list[position].diffLevel
        if(match_list[position].winner.equals("AI")){

            val resID = ctx.resources.getIdentifier("lost_match", "drawable", ctx.packageName)
            holder.item.background= ActivityCompat.getDrawable(ctx,resID)
        }
        else{
            val resID = ctx.resources.getIdentifier("button_design", "drawable", ctx.packageName)
            holder.item.background= ActivityCompat.getDrawable(ctx,resID)

        }
        holder.bindOnClickListener(onClickListener,position)


    }
    override fun getItemCount(): Int {
        return match_list.size
    }






    fun setOnClickListener(onClickListener: OnClickListener)
    {
        this.onClickListener = onClickListener
    }

}