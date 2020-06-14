package com.example.gomoku

import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlin.math.abs

class GameBoardView : View  {




    constructor(ctx: Context) : super(ctx)
    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)

    lateinit var positionClickedListener : PositionClickedListener
    var gameboardSize = 10
    lateinit var gameBoardArray : Array<Array<Int>>
    val circleRadius = 30f
    var touching: Boolean = false
    var xPositions : Array<Float>? = null
    var yPositions : Array<Float>? = null

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)


        if(::gameBoardArray.isInitialized){
            checkGameBoardSize()
            calculateXYPositions()
            drawSquares(canvas)
            drawBoard(canvas)
        }




    }

    fun checkGameBoardSize(){
        if(::gameBoardArray.isInitialized  && gameBoardArray.size == gameBoardArray[0].size ){
            gameboardSize=gameBoardArray.size
        }
    }
    fun calculateXYPositions(){

        if(::gameBoardArray.isInitialized) {
            var start = 30f
            val correctedWidth = width.toFloat() - 60f
            val addValue = correctedWidth / (gameboardSize - 1).toFloat()
            xPositions=Array(gameboardSize){0f}
            yPositions=Array(gameboardSize){0f}
            xPositions!![0] = start
            yPositions!![0] = start
            for (x in 1 until gameboardSize ) {
                start += addValue
                yPositions!![x] = start
                xPositions!![x] = start
            }
        }

    }
    fun drawSquares(canvas:Canvas?){
        //Kolor wypelnienia planszy
        val paintFill =Paint()
        paintFill.setColor(Color.GRAY)
        paintFill.style=Paint.Style.FILL
        //kolor obramowek planszy
        val paintStroke = Paint()
        paintStroke.setColor(Color.CYAN)
        paintStroke.style=Paint.Style.STROKE
        paintStroke.strokeWidth= 5F

        val squares = ArrayList<RectF>()
        var xstart=30f
        var ystart=30f
        var widthCorrect = width.toFloat()-60f
        val addwidth=widthCorrect/(gameboardSize-1).toFloat()
        var xfinish=addwidth+30F
        var yfinish= addwidth+30F
        val roundAngle = 30f
//        for (x in xPositions){
//            for(y in yPositions){
//                val rect:RectF = RectF(xstart,ystart,xfinish,yfinish)
//                squares.add(rect)
//
//            }
//        }

        for (x in 0 until gameboardSize-1){
            xstart=20F
            xfinish=widthCorrect/(gameboardSize-1).toFloat()+30f
            for(y in 0 until gameboardSize-1){
                val rect:RectF = RectF(xstart,ystart,xfinish,yfinish)
                squares.add(rect)
                xstart = xfinish
                xfinish += addwidth
            }
            ystart = yfinish
            yfinish += addwidth

        }

        for(rect in squares){
            canvas!!.drawRoundRect(rect,roundAngle,roundAngle,paintFill)
            canvas!!.drawRoundRect(rect,roundAngle,roundAngle,paintStroke)
        }
    }
    fun drawBoard(canvas: Canvas?){
        var x=30F
        var y=30F
        var widthCorrect = width.toFloat()-60f
        val addwidth=widthCorrect/(gameboardSize-1).toFloat()

        val paint1 = Paint()
        paint1.setColor(Color.BLACK)
        val paint2 = Paint()
        paint2.setColor(Color.WHITE)
        var positionVal = 0
        for (x in 0 until gameboardSize-1){
            for(y in 0 until gameboardSize-1){
                positionVal=gameBoardArray[x][y]
                if(positionVal==1){
                    canvas!!.drawCircle(xPositions?.get(x)!!, yPositions?.get(y)!!,circleRadius,paint1)
                }
                if(positionVal==2){
                    canvas!!.drawCircle(xPositions?.get(x)!!, yPositions?.get(y)!!,circleRadius,paint2)
                }
            }
        }
//        for( array in gameBoardArray){
//            x=30F
//            for(position in array){
//                if(position == 1){
//
//                    canvas!!.drawCircle(x,y,circleRadius,paint1)
//                }
//                if(position==2){
//                    canvas!!.drawCircle(x,y,circleRadius,paint2)
//                }
//                x+=addwidth
//            }
//            y+=addwidth
//        }
    }

    fun getPositionOF(x:Float,y:Float): Pair<Int,Int>{
        var xpos =0
        var ypos=0
        var absolut = Float.MAX_VALUE
        var value = 0f
        for(xi in 0 until xPositions!!.size){
            value = abs(this!!.xPositions?.get(xi)!!-x )
            if(value<absolut){
                xpos=xi
                absolut = value
            }
        }
        for(xi in 0 until yPositions!!.size){
            value = abs(this!!.yPositions!![xi]-y )
            if(value<absolut){
                ypos=xi
                absolut = value
            }
        }
        Log.d("POSITION","Position work")
        return Pair(xpos,ypos)
    }
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (!isEnabled) {
            return false
        }
        val x = event!!.x
        val y = event!!.y
        var pos = Pair(0,0)
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                pos = getPositionOF(x,y)
                touching = true
                positionClickedListener?.onPositionClicked(pos.first,pos.second)

            }
            MotionEvent.ACTION_MOVE -> {

            }
            MotionEvent.ACTION_UP -> {
                touching = false

                val (finalX1, finalY1) = getPositionOF(x, y)
                if ((finalX1 == pos.first) && (finalY1 == pos.second)) { // if initial touch and final touch is in same rectangle or not
                    Log.d("listener","finale")


                }

            }
            MotionEvent.ACTION_CANCEL -> {
                touching = false
            }

        }
        return true


    }

    interface PositionClickedListener{
        fun onPositionClicked( x: Int, y: Int)
    }



}