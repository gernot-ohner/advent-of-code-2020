object day5 extends App {

  val data: List[String] = Util.getDataInLines("day5")
  val seatIds = data.map(_.splitAt(7)).map(x => {
      val row = findSeat(0, 127, x._1)
      val col = findSeat(0, 7, x._2)
      toSeatId(row, col)
    })

  def toSeatId(row: Int, column: Int): Int = row * 8 + column

  @scala.annotation.tailrec
  def findSeat(lower: Int, upper: Int, s: String): Int = {
    if (s.isEmpty) return upper
    val middle = (upper + lower) / 2
    if (s.head == 'L' || s.head == 'F') findSeat(lower, middle, s.tail)
    else findSeat(middle, upper, s.tail)
  }

  println(s"Maximum Seat ID: ${seatIds.max}")
  val diff = (seatIds.min to seatIds.max).diff(seatIds).head
  println(s"My Seat ID: $diff")
}
