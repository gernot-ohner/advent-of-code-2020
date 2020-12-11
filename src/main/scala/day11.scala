object day11 extends App {

  val availableSeat = "L"
  val occupiedSeat = "#"
  val data: Array[Array[String]] = Util.getDataInLines("day11").map(_.split("")).toArray

  def visibleOccupiedSeats(state: Array[Array[String]], x: Int, y: Int) = {
    val xPlus = (x until data.length).tail
    val xMinus = (x to 0 by -1).tail
    val yPlus = (y until data.head.length).tail
    val yMinus = (y to 0 by -1).tail

    val right = xPlus.map((_, y))
    val left = xMinus.map((_, y))
    val up = yPlus.map((x, _))
    val down = yMinus.map((x, _))
    val rightUp = xPlus.zip(yPlus)
    val rightDown = xPlus.zip(yMinus)
    val leftUp = xMinus.zip(yPlus)
    val leftDown = xMinus.zip(yMinus)

    def findVisibleOccupiedSeat(range: Seq[(Int, Int)]) = {
      range.takeWhile(pos => state(pos._1)(pos._2) != availableSeat)
        .find(pos => state(pos._1)(pos._2) == occupiedSeat)
    }

    List(right, left, up, down, rightUp, rightDown, leftUp, leftDown)
      .map(findVisibleOccupiedSeat)
      .count(_.isDefined)
  }

  def computeOccupancy(state: Array[Array[String]], x: Int, y: Int) = {
    val numOfVisibleOccupiedSeats = visibleOccupiedSeats(state, x, y)
    val seat = state(x)(y)
    if (seat == availableSeat && numOfVisibleOccupiedSeats == 0) {
      occupiedSeat
    } else if (seat == occupiedSeat && numOfVisibleOccupiedSeats >= 5) {
      availableSeat
    } else {
      seat
    }
  }

  def deepEquals(a1: Array[Array[String]], a2: Array[Array[String]]) = {
    val flatten = a2.flatten
    a1.flatten.sameElements(flatten)
  }

  def computeNewState(data: Array[Array[String]]) = {
    data.zipWithIndex.map(x => x._1.zipWithIndex.map(y => computeOccupancy(data, x._2, y._2)))
  }

  @scala.annotation.tailrec
  def update(state: Array[Array[String]]): Array[Array[String]] = {
    val newState = computeNewState(state)
    if (deepEquals(state, newState)) state else update(newState)
  }

  def countOccupied(state: Array[Array[String]]): Int = {
    (for {x <- state; y <- x} yield y == occupiedSeat).count(_ == true)
  }

  private val finalState: Array[Array[String]] = update(data)
  private val occupiedSeatsInFinalState: Int = countOccupied(finalState)
  println(s"In the final state $occupiedSeatsInFinalState seats are occupied")

}
