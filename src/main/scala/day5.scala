object day5 extends App {

  val data: List[String] = Util.getDataInLines("day5")
  val seatIds = data.map(_.splitAt(7)).map(x => findSeat(0, 127, x._1) * 8 + findSeat(0, 7, x._2))

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
