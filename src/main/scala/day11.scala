object day11 extends App {

  val availableSeat = "L"
  val occupiedSeat = "#"
  val data: Array[Array[String]] = Util.getDataInLines("day11").map(_.split("")).toArray
  val seatMap = data.map(_.map(s => s == availableSeat))

  def surroundingSeats(x: Int, y: Int) = {
    val allSurroundingPositions = List(
      (x + 1, y + 1), (x + 1, y), (x + 1, y - 1),
      (x, y + 1), (x, y - 1),
      (x - 1, y + 1), (x - 1, y), (x - 1, y - 1)
    )
    allSurroundingPositions.filter(position =>
      data.indices.contains(position._1)
        && data.head.indices.contains(position._2)
        && seatMap(position._1)(position._2))
  }

  def computeOccupancy(data: Array[Array[String]], x: Int, y: Int) = {
    val occupiedSeatsNearby = surroundingSeats(x, y).map(position => data(position._1)(position._2)).count(_ == occupiedSeat)
    val seat = data(x)(y)
    if (seat == availableSeat && occupiedSeatsNearby == 0) {
      occupiedSeat
    } else if (seat == occupiedSeat && occupiedSeatsNearby >= 4) {
      availableSeat
    } else {
      seat
    }
  }

  def deepEquals (a1: Array[Array[String]], a2: Array[Array[String]]) = {
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
    (for {
      x <- state
      y <- x
    } yield y == occupiedSeat).count(_ == true)
  }

  private val finalState: Array[Array[String]] = update(data)
  private val occupiedSeatsInFinalState: Int = countOccupied(finalState)
  println(s"In the final state $occupiedSeatsInFinalState are occupied")

}
