package com.minesweeper.grid

/**
  * Created by James Thompson on 27-Jun-16.
  */
class Bomb extends GridSquare {
  override def action(visibleGrid: Array[Array[GridSquare]], invisibleGrid: Array[Array[GridSquare]], x: Int, y: Int): Array[Array[GridSquare]] ={
    sweepGrid(visibleGrid, invisibleGrid, sweepRowBomb(_,_))
    //as in java
    /*for(int i = 0; i < invisibleGrid.length; i++) {
      for(int j = 0; j < invisibleGrid.get(i).length; j++) {
        if (invisibleGrid[i][j].equals(Bomb) && !visibleGrid[i][j].equals(Flag)) {
          visibleGrid[i][j] == new Bomb;
        }
      }
    }*/
  }

  def sweepRowBomb(visibleRow: Array[GridSquare], invisibleRow: Array[GridSquare]) : Array[GridSquare] ={
    if(invisibleRow.isEmpty) {
      visibleRow
    } else {
      if (invisibleRow.head.isInstanceOf[Bomb] && visibleRow.head.isInstanceOf[Flag]) {
        visibleRow.update(0, new Bomb)
        visibleRow.head +: sweepRowBomb(visibleRow.tail, invisibleRow.tail)
      } else {
        sweepRowBomb(visibleRow.tail, invisibleRow.tail)
      }
    }
  }
}