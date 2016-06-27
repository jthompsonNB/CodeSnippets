package com.minesweeper.grid

/**
  * Created by James Thompson on 27-Jun-16.
  * It's so... Flappy!
  */
class Flag extends GridSquare{
  override def action(visibleGrid: Array[Array[GridSquare]], invisibleGrid: Array[Array[GridSquare]], x: Int, y: Int): Array[Array[GridSquare]] ={
    var updatedRow:Array[GridSquare] = visibleGrid.lift(x).get
    updatedRow.update(y, new Flag)
    visibleGrid.update(x, updatedRow)
    visibleGrid
  }
}