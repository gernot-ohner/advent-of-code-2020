object day4 extends App {

  val byr = ".*byr:(?<year>\\d{4}) .*".r.findFirstMatchIn(_: String).exists(1920 to 2002 contains _.group("year").toInt)
  val iyr = ".*iyr:(?<year>\\d{4}) .*".r.findFirstMatchIn(_: String).exists(2010 to 2020 contains _.group("year").toInt)
  val eyr = ".*eyr:(?<year>\\d{4}) .*".r.findFirstMatchIn(_: String).exists(2020 to 2030 contains _.group("year").toInt)
  val hgt = ".*hgt:(?<height>\\d{2,3})(?<unit>\\w{2}) .*".r.findFirstMatchIn(_: String).exists(x => {
    val unit = x.group("unit")
    val height = x.group("height").toInt
    (unit == "in" && (59 to 76 contains height)) || (unit == "cm" && (150 to 193 contains height))
  })
  val hcl = ".*hcl:#[0-9a-f]{6} .*".r.matches(_: String)
  val ecl = ".*ecl:(amb|blu|brn|gry|grn|hzl|oth) .*".r.matches(_: String)
  val pid = ".*pid:\\d{9} .*".r.matches(_: String)

  val requirements = List(byr, iyr, eyr, hgt, hcl, ecl, pid)
  val data: String = Util.getData("day4")
  val passports = data.split("\n\n").map(_.replace("\n", " ").appended(' '))
  val numberOfValidPassports = passports.count(passport => requirements.forall(_(passport)))

  println(s"Number of valid passports: $numberOfValidPassports")
}
