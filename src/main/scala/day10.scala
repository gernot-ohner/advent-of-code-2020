object day10 extends App {


  private val sorted: List[Int] = Util.getDataInLines("day10_test2").map(_.toInt).sorted.reverse.appended(0)

  sorted.foreach(println(_))


  def buildTree(data: List[Int]) = {
    def loop(i: Int): Tree = {
      val children = validChildValuesFor(i).map(toTree)
      Node(i, children)
    }

    def toTree(i: Int) = {
      if (i == 0) Leaf(i)
      else loop(i)
    }

    def validChildValuesFor(i: Int) = {
      data.filter((i - 3 until i).contains(_))
    }

    loop(sorted.head)
  }

  private val tree: Tree = buildTree(sorted)
  println(tree)
  println(tree.countValidEndpoints())


}

abstract class Tree {
  def countValidEndpoints(): Int = this match {
    case Leaf(value) => if (value == 0) 1 else 0
    case Node(value, children) => children.map(_.countValidEndpoints()).sum
    case _ => 0
  }
}
case class Node(value: Int, var children: List[Tree]) extends Tree {

  def addChild(child: Tree): Unit = {
    children = children.appended(child)
  }

}

case class Leaf(value: Int) extends Tree
