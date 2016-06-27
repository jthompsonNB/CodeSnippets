package com.minesweeper.grid

/**
  * Created by James Thompson on 27-Jun-16.
  */
trait GridSquare {

  def action(visibleGrid: Array[Array[GridSquare]], invisibleGrid: Array[Array[GridSquare]], x: Int, y: Int) : Array[Array[GridSquare]]

  def select(action: (Array[Array[GridSquare]], Array[Array[GridSquare]], Int, Int) => Array[Array[GridSquare]]): Array[Array[GridSquare]] = {
    action(_,_,_,_)
  }

  def sweepGrid(visibleGrid: Array[Array[GridSquare]], invisibleGrid: Array[Array[GridSquare]], sweepRow:(Array[GridSquare], Array[GridSquare]) => Array[GridSquare]) : Array[Array[GridSquare]] ={
    if(invisibleGrid.isEmpty){
      visibleGrid
    } else {
      visibleGrid.update(0, sweepRow(visibleGrid.head, invisibleGrid.head))
      visibleGrid.head +: sweepGrid(visibleGrid.tail, invisibleGrid.tail, sweepRow)
    }
  }

  def checkSurrounding(visibleGrid: Array[Array[GridSquare]], invisibleGrid: Array[Array[GridSquare]], x: Int, y: Int, action: (Array[Array[GridSquare]], Array[Array[GridSquare]], Int, Int) => Array[Array[GridSquare]]) : Array[Array[GridSquare]] = {
    if (x != 0) {
      action(visibleGrid, invisibleGrid, x - 1, y)
      if (y != 0) {
        action(visibleGrid, invisibleGrid, x - 1, y - 1)
      } if (y < visibleGrid.lift(x).get.length) {
        action(visibleGrid, invisibleGrid, x - 1, y + 1)
      }
    }
    if (x < visibleGrid.length) {
      action(visibleGrid, invisibleGrid, x + 1, y)
      if (y != 0) {
        action(visibleGrid, invisibleGrid, x + 1, y - 1)
      } if (y < visibleGrid.lift(x).get.length) {
        action(visibleGrid, invisibleGrid, x + 1, y + 1)
      }
    }
    if (y != 0) {
      action(visibleGrid, invisibleGrid, x, y - 1)
    } if (y < visibleGrid.lift(x).get.length) {
      action(visibleGrid, invisibleGrid, x, y + 1)
    }
    visibleGrid
  }
}