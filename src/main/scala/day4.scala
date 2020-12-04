import scala.util.matching.Regex

object day4 extends App {

  val byr = (s: String) => validYear(".*byr:(?<year>\\d{4}).*".r.findFirstMatchIn(s), 1920, 2002)
  val iyr = (s: String) => validYear(".*iyr:(?<year>\\d{4}).*".r.findFirstMatchIn(s), 2010, 2020)
  val eyr = (s: String) => validYear(".*eyr:(?<year>\\d{4}).*".r.findFirstMatchIn(s), 2020, 2030)
  val hgt = (s: String) => {
    val maybeMatch = ".*hgt:(?<height>\\d{2,3})(?<unit>\\w{2}) .*".r.findFirstMatchIn(s)
    maybeMatch.isDefined && {
      val unit = maybeMatch.get.group("unit")
      val height = maybeMatch.get.group("height").toInt
      (unit == "in" && height >= 59 && height <= 76) ||
        (unit == "cm" && height >= 150 && height <= 193)
    }
  }
  val hcl = (s: String) => ".*hcl:#[0-9a-f]{6} .*".r.matches(s)
  val ecl = (s: String) => ".*ecl:(amb|blu|brn|gry|grn|hzl|oth) .*".r.matches(s)
  val pid = (s: String) => ".*pid:\\d{9} .*".r.matches(s)

  private def validYear(maybeMatch: Option[Regex.Match], lowerBound: Int, upperBound: Int) = {
    maybeMatch.isDefined && {
      val year = maybeMatch.get.group("year").toInt
      year >= lowerBound && year <= upperBound
    }
  }

  val requirements = List(byr, iyr, eyr, hgt, hcl, ecl, pid)
  val data: List[String] = Util.getData("day4")
  val s = data.mkString("\n").split("\n\n")
    .map(_.replace("\n", " "))
    .map(_.appended(' '))
  val validPassports = s.filter(passport => requirements.forall(_(passport)))

  println(s"Number of valid passports: ${validPassports.length}")
}
