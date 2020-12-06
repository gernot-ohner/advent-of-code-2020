object day6 extends App {

  val groups = Util.getData("day6").split("\n\n")
  val anyTrueAnswers = groups.map(_.replace("\n", "")).map(_.distinct.length).sum
  val allTrueAnswers = groups.map(_.split("\n").map(_.distinct).reduce(_.intersect(_)).length).sum

  println(s"Sum of number of questions for which anyone in a groups answered yes: $anyTrueAnswers")
  println(s"Sum of number of questions for which everyone in a groups answered yes: $allTrueAnswers")

}
