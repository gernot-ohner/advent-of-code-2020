import scala.io.{BufferedSource, Source}

object Util {

  def getData(day: String): List[String] = {
    val filepath = s"inputs/$day.txt"
    val file: BufferedSource = Source.fromResource(filepath)
    val data = file.getLines().toList
    file.close
    data
  }

}
