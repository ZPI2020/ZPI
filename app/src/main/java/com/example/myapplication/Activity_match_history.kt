package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_match_history.*
import kotlinx.android.synthetic.main.activity_match_history.view.*


class Activity_match_history : AppCompatActivity(),AdapterMatchHistory.OnClickListener {

    var matches=ArrayList<GameLog>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_history)
        val matches_rv =  matches_recycler as RecyclerView
        no_match_text.visibility= View.GONE
        match_details.visibility= View.GONE

        val loader=GamesHistory()
        try{
            matches=loader.load(this)
        }
        catch(e:Exception){

            no_match_text.visibility= View.VISIBLE
        }


        val arr:Array<IntArray> = arrayOf(
            intArrayOf(0,0,0,0,0,0,0,0),
            intArrayOf(0,0,0,2,0,0,0,0),
            intArrayOf(0,0,0,2,0,0,0,0),
            intArrayOf(0,0,0,2,1,0,0,0),
            intArrayOf(0,0,0,2,0,0,0,0),
            intArrayOf(0,0,0,2,0,0,0,0),
            intArrayOf(0,0,0,0,0,0,0,0),
            intArrayOf(0,0,0,0,0,0,0,0)
        )
        matches.add(GameLog("today","hard","you",arr))
        val adapter =  AdapterMatchHistory(matches)

        adapter.setOnClickListener(this)
        matches_rv.adapter = adapter
        matches_rv.layoutManager= LinearLayoutManager(this)
    }
    override fun onResume() {
        super.onResume()
        match_details.visibility= View.GONE

    }

    override fun onItemClick(index: Int) {
        match_details.visibility= View.VISIBLE
        match_details.tv_date.text=matches[index].date
        match_details.tv_mode.text=matches[index].diffLevel
        match_details.tv_winner.text=matches[index].winner
        val gameBoardArray = matches[index].board
        val popup = Activity_match_history_popup()


        val intent = Intent(this, popup::class.java)
        intent.putExtra("board",gameBoardArray)

        startActivity(intent)
    }
}
