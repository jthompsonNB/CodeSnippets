package com.minesweeper.grid

/**
  * Created by James Thompson on 27-Jun-16.
  */
case class UncoveredSquare(adjacentMines: Int) extends GridSquare{
  override def action(visibleGrid: Array[Array[GridSquare]], invisibleGrid: Array[Array[GridSquare]], x: Int, y: Int): Array[Array[GridSquare]] = {
    if(invisibleGrid(x)(y).equals(new UncoveredSquare(0))) {
      uncoverBlanks(visibleGrid, invisibleGrid, x, y)
    } else {
      visibleGrid(x)(y) = invisibleGrid(x)(y)
      visibleGrid
    }
  }

  def uncoverBlanks(visibleGrid: Array[Array[GridSquare]], invisibleGrid: Array[Array[GridSquare]], x: Int, y: Int): Array[Array[GridSquare]] = {
    if (visibleGrid(x)(y).isInstanceOf[CoveredSquare]) {
      visibleGrid(x)(y) = invisibleGrid(x)(y)
      if (invisibleGrid(x)(y).equals(new UncoveredSquare(0))) {
        checkSurrounding(visibleGrid, invisibleGrid, x, y, uncoverBlanks(_,_,_,_))
      }
    }
    visibleGrid
  }
}