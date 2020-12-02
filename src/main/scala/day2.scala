object day2 extends App {

  val data: List[String] = Util.getData("day2")

  val passwordEntryPattern = "(\\d+)-(\\d+) (\\w): (\\w+)".r

  val oldCount = data.map(passwordEntry => {
    val passwordEntryPattern(lower, upper, char, password) = passwordEntry
    val count: Int = password.count(char == _.toString)
    (count >= lower.toInt) && (count <= upper.toInt)
  }).count(_ == true)

  val newCount = data.map(passwordEntry => {
    val passwordEntryPattern(lower, upper, char, password) = passwordEntry
    (password(lower.toInt - 1).toString == char) ^
      (password(upper.toInt - 1).toString == char)
  }).count(_ == true)

  println(s"Correct passwords according to old policy: $oldCount")
  println(s"Correct passwords according to new policy: $newCount")
}
