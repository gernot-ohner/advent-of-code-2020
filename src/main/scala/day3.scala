object day3 extends App {

  val data: List[String] = Util.getDataInLines("day3")
  val slopes = List((1, 1), (1, 3), (1, 5), (1, 7), (2, 1))

  val width = data.head.length
  val height = data.length

  def countTreesForSlope(data: List[String], slope: (Int, Int)): Long = {
    val numberOfSteps = height / slope._1
    val steps = (0 until numberOfSteps).map(i => (slope._1 * i, (slope._2 * i) % width))
    steps.count(step => data(step._1)(step._2) == '#')
  }

  println(slopes.map(slope => countTreesForSlope(data, slope)).product)
}
