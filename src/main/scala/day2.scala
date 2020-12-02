import scala.util.matching.Regex

object day2 extends App {

  private val passwordRegex = new Regex("(\\d+)-(\\d+) (\\w): (\\w+)", "lower", "upper", "char", "password")
  private val data: List[String] = Util.getData("day2")

  def parsePasswordEntry(string: String): PasswordEntry = {

    val matchData: Regex.Match = passwordRegex.findFirstMatchIn(string).get
    new PasswordEntry(
      matchData.group("lower").toInt,
      matchData.group("upper").toInt,
      matchData.group("char"),
      matchData.group("password")
    )
  }

  private val passwordEntries: List[PasswordEntry] = data.map(parsePasswordEntry)

  def validateOldPassword(passwordEntry: PasswordEntry): Boolean = {
    val count = passwordEntry.password.count(passwordEntry.char == _.toString)
    count >= passwordEntry.lower && count <= passwordEntry.upper
  }

  def validateNewPassword(passwordEntry: PasswordEntry): Boolean = {
    (passwordEntry.password(passwordEntry.lower - 1).toString == passwordEntry.char) ^
      (passwordEntry.password(passwordEntry.upper - 1).toString == passwordEntry.char)
  }


  val oldCount = passwordEntries.map(validateOldPassword).count(_ == true)
  val newCount = passwordEntries.map(validateNewPassword).count(_ == true)
  println(newCount)




}

class PasswordEntry(val lower: Int, val upper: Int, val char: String, val password: String)
