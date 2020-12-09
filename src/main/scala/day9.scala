import day9.data

object day9 extends App {

  val data: List[Long] = Util.getDataInLines("day9").map(_.toLong)

  def twoSum(data: Seq[Long], target: Long) = {
    data.find(i => data.contains(target - i)).toRight(target)
  }

  val preambleLength = 25
  val invalidNumber = data.zipWithIndex
    .drop(preambleLength)
    .map(i => twoSum(data.slice(i._2 - preambleLength, i._2), i._1))
    .filter(_.isLeft).head.left.get

  println(s"Invalid number: $invalidNumber")

  (2 to data.size).find(i => {
    val value: Option[List[Long]] = data.sliding(i).find( is => is.sum == invalidNumber)
    if (value.isDefined) {
      val contiguousNumbers = value.get
      println(s"Encryption weakness: ${contiguousNumbers.min + contiguousNumbers.max}")
      true
    } else {
      false
    }
  })




}
