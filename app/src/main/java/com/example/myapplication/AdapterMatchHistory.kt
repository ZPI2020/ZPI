package com.example.myapplication

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterMatchHistory(private var match_list :ArrayList<History>):
    RecyclerView.Adapter<AdapterMatchHistory.ViewHolder>() {

    private var onClickListener: OnClickListener? = null


    interface OnClickListener
    {
        fun onItemClick(index: Int)
    }


    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        val item = view.findViewById<LinearLayout>(R.id.match_list_item)
        var tv_winner = view.findViewById<TextView>(R.id.tv_winner)
        //val typeface = Typeface.createFromAsset(assets, "fonts/yourfont.ttf")

        val tv_date = view.findViewById<TextView>(R.id.tv_date)
        val tv_mode = view.findViewById<TextView>(R.id.tv_mode)


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
        holder.tv_winner.text = match_list[position].winner
        holder.tv_date.text = match_list[position].date.toString()
        holder.tv_mode.text = getMode(match_list[position].mode)
        holder.bindOnClickListener(onClickListener,position)


    }
    override fun getItemCount(): Int {
        return match_list.size
    }
    fun getMode(mode: Int): String{
        if(mode==0){
            return ""
        }
        else if(mode==1){
            return "EASY"
        }
        else if(mode==2){
            return "MEDIUM"
        }
        else if(mode==3){
            return "HARD"
        }
        return ""
    }





    fun setOnClickListener(onClickListener: OnClickListener)
    {
        this.onClickListener = onClickListener
    }

}