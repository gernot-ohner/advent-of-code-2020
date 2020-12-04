object day1 extends App {

  val data = Util.getDataInLines("day1").map(_.toInt)

  def two_sum(data: List[Int], target: Int) = {
    for (
      i <- data;
      j <- data
      if i + j == target
    ) yield (i, j)
  }

  def three_sum(data: List[Int], target: Int) = {
      for (
        i <- data;
        j <- data;
        k <- data
        if i + j + k == target
      ) yield (i, j, k)
  }

  private val tuple: (Int, Int) = two_sum(data, 2020).head
  println("Two sum product: " + tuple._1 * tuple._2)

  private val triple = three_sum(data, 2020).head
  println("Three sum product: " + triple._1 * triple._2 * triple._3)

}
