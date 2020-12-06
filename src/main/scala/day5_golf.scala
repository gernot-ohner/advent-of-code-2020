object day5_golf extends App {
  val seatIds = Util.getDataInLines("day5").map(x => Integer.parseInt(x.replace("B", "1").replace("R", "1").replace("L", "0").replace("F", "0"), 2))
  val diff = (seatIds.min to seatIds.max).diff(seatIds).head

  println(s"Maximum seatId: ${seatIds.max}")
  println(s"My Seat ID: $diff")
}
