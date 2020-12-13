object day13 extends App {

  private val data: List[String] = Util.getDataInLines("day13")
  private val earliestTimeOfDeparture: Long = data.head.toLong

  private val intervals: Array[Long] = data.last.split(",")
    .filter(_ != "x")
    .map(_.toLong)

  // create a map from the interval/id to the lowest multiple of the interval greater than the earliest time of departure
  println(s"Earliest time of departure is $earliestTimeOfDeparture")
  val map = intervals.map(i => i -> ((earliestTimeOfDeparture / i + 1) * i - earliestTimeOfDeparture)).toMap
  val earliestBus = map.minBy(_._2)
  println(s"The earliest bus has the id ${earliestBus._1} and I need to wait ${earliestBus._2} minutes for it")
  println(earliestBus._1 * earliestBus._2)

}
