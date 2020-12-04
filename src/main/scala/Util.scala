import scala.io.{BufferedSource, Source}

object Util {

  def getDataInLines(day: String): List[String] = {
    val filepath = s"inputs/$day.txt"
    val file: BufferedSource = Source.fromResource(filepath)
    val data = file.getLines().toList
    file.close
    data
  }

  def getData(day: String): String = {
    val filepath = s"inputs/$day.txt"
    val file: BufferedSource = Source.fromResource(filepath)
    val data = file.mkString
    file.close
    data
  }

}
