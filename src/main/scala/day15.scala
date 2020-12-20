object day15 extends App {

  val data: Array[Long] = Util.getData("day15").split(",").map(_.toLong)
  val gameEnd = 30000000

  val lastTimeSpokenMap = scala.collection.mutable.Map.empty[Long, Long]
  data.zipWithIndex.foreach((l: (Long, Int)) => lastTimeSpokenMap.addOne(l._1, l._2 + 1))

  var lastNumberSpoken: Long = data.last
  (data.length + 1 to gameEnd).foreach(i => {
    val nextNumberSpoken: Long = lastTimeSpokenMap.get(lastNumberSpoken).map(i - 1 - _).getOrElse(0L)
    lastTimeSpokenMap.put(lastNumberSpoken, i - 1)
    lastNumberSpoken = nextNumberSpoken
  })

  println(s"The ${gameEnd}th number spoken is $lastNumberSpoken")
}
