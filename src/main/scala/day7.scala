

object day7 extends App {

  val data = Util.getDataInLines("day7")
  val innerRegex = "\\d \\w+ \\w+".r

  val rules: Map[String, List[String]] = data.map(line => {
    val name = line.split(" ").take(2).mkString(" ")
    name -> innerRegex.findAllIn(line).map(_.split(" ").tail.mkString(" ")).toList
  }).toMap

  val allColors = data.map(_.split(" ").take(2).mkString(" "))

  def allRecursiveChildren(name: String, map: Map[String, List[String]]): List[String] = {
    List(name) ++ map(name).flatMap(allRecursiveChildren(_, map))
  }

  val count = allColors.count(str => {
    val str1 = allRecursiveChildren(str, rules).distinct
    str1.head != "shiny gold" && str1.contains("shiny gold")
  })

  println(count)

}

