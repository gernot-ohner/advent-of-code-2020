import scala.util.matching.Regex

object day16 extends App {

  val data: Array[String] = Util.getData("day16").split("\n\n")
  val rules: Array[Rule] = data(0).split("\n").map(Rule(_))

  val masterRule: Rule = rules.reduce((r1, r2) => r1 or r2)
  val myTicket: Array[Int] = data(1).split("\n").last.split(",").map(_.toInt)
  val otherTickets: Array[Array[Int]] = data(2).split("\n").tail.map(_.split(",").map(_.toInt))

  val ticketScanningErrorRate: Int = otherTickets.map(_.filter(i => !masterRule.contains(i)).sum).sum
  println(s"The ticket scanning error rate is $ticketScanningErrorRate")

}

class Rule(val name: String, val range1: Seq[Int], val range2: Seq[Int]) {
  def contains(i: Int): Boolean = {
    range1.contains(i) || range2.contains(i)
  }

  override def toString: String = {
    s"$name: $range1 or $range2"
  }

  def or(that: Rule): Rule = {
    new Rule(s"${this.name} or ${that.name}",
      range1.concat(that.range1), range2.concat(that.range2)
    )
  }
}

object Rule {
  val regex: Regex = "(?<name>[\\w ]+): (?<range1start>\\d+)-(?<range1end>\\d+) or (?<range2start>\\d+)-(?<range2end>\\d+)".r
  def apply(s: String): Rule = {
    val regex(name, range1start, range1end, range2start, range2end) = s
    new Rule(name, range1start.toInt to range1end.toInt, range2start.toInt to range2end.toInt)
  }
}