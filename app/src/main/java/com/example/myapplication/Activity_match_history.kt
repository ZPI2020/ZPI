package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_match_history.*


class Activity_match_history : AppCompatActivity(),AdapterMatchHistory.OnClickListener {

    var matches=ArrayList<GameLog>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_history)
        val matches_rv =  matches_recycler as RecyclerView
        no_match_text.visibility= View.GONE
        val loader=GamesHistory()
        try{
            matches=loader.load(this)
        }
        catch(e:Exception){

            no_match_text.visibility= View.VISIBLE
        }



        val adapter =  AdapterMatchHistory(matches)

        adapter.setOnClickListener(this)
        matches_rv.adapter = adapter
        matches_rv.layoutManager= LinearLayoutManager(this)
    }

    override fun onItemClick(index: Int) {

        val gameBoardArray = matches[index].board
        val popup = Activity_match_history_popup()


        val intent = Intent(this, popup::class.java)
        intent.putExtra("board",gameBoardArray)

        startActivity(intent)
    }
}
