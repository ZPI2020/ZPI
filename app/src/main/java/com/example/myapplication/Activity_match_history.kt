package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_match_history.*
import kotlinx.android.synthetic.main.activity_match_history.view.*


class Activity_match_history : AppCompatActivity(),AdapterMatchHistory.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_history)
        val matches_rv =  matches_recycler as RecyclerView
        val h1=History()

        val matches=ArrayList<History>()
        matches.add(h1)
        matches.add(h1)
        matches.add(h1)

        val adapter =  AdapterMatchHistory(matches)
        adapter.setOnClickListener(this)
        matches_rv.adapter = adapter
        matches_rv.layoutManager= LinearLayoutManager(this)
    }

    override fun onItemClick(index: Int) {

        val gameBoardArray = arrayOf(
            arrayOf(0,0,0,0,0,0,1,1,0,1,1,2),
            arrayOf(0,2,2,1,0,0,0,0,0,1,1,0),
            arrayOf(2,2,2,2,0,1,0,1,0,1,1,1),
            arrayOf(0,0,0,0,0,1,0,0,0,0,1,1),
            arrayOf(1,0,2,0,1,2,2,0,0,1,1,1),
            arrayOf(2,2,2,0,1,1,0,0,0,1,1,2),
            arrayOf(0,0,0,1,1,1,0,0,0,1,1,2),
            arrayOf(0,0,1,0,1,1,0,0,0,1,1,2),
            arrayOf(0,0,1,0,1,1,0,0,0,1,1,2),
            arrayOf(0,0,1,0,1,1,0,0,0,1,1,2),
            arrayOf(0,0,1,0,1,1,0,0,0,1,1,2),
            arrayOf(0,0,1,0,1,1,0,0,0,1,1,2)
        )
        val popup = Activity_match_history_popup()


        val intent = Intent(this, popup::class.java)
        intent.putExtra("board",gameBoardArray)

        startActivity(intent)
    }
}
