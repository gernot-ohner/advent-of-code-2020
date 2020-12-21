object day15 extends App {

  val data: Array[Int] = Util.getData("day15").split(",").map(_.toInt)
  val gameEnd = 2020

  @scala.annotation.tailrec
  def recursion(lastTimeSpokenMap: Map[Int, Int], lastNumberSpoken: Int, i: Int, gameEnd: Int): Int = {
    if (i >= gameEnd) return lastNumberSpoken

    val nextNumberSpoken = lastTimeSpokenMap.get(lastNumberSpoken).map(i - 1 - _).getOrElse(0)
    val newMap = lastTimeSpokenMap.updated(lastNumberSpoken, i - 1)
    recursion(newMap, nextNumberSpoken, i + 1, gameEnd)
  }

  val lastNumberSpoken = recursion(data.zipWithIndex.toMap, data.last, data.length, gameEnd)
  println(s"The ${gameEnd}th number spoken is $lastNumberSpoken")
}
