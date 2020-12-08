object day8 extends App {

  val data = Util.getDataInLines("day8").map(x => {
    val instruction = x.split(" ").head
    val argument = x.split(" ").last.toInt
    new Instruction(instruction, argument)
  })

  @scala.annotation.tailrec
  def compute(data: List[Instruction], i: Int, acc: Int, alreadyVisitedInstructions: Set[Int]): Int = {
    if (alreadyVisitedInstructions.contains(i)) {
      println("loop detected!")
      return acc
    }
    val instruction = data(i)
    if (instruction.instruction == "nop") {
      compute(data, i + 1, acc, alreadyVisitedInstructions.incl(i))
    } else if (instruction.instruction == "jmp") {
      compute(data, i + instruction.argument, acc, alreadyVisitedInstructions.incl(i))
    } else if (instruction.instruction == "acc") {
      compute(data, i + 1, acc + instruction.argument, alreadyVisitedInstructions.incl(i))
    } else {
      0
    }
  }

  val result = compute(data, 0, 0, Set.empty)
  println(result)

}

class Instruction(val instruction: String, val argument: Int) {
  override def toString: String = s"instruction $instruction, argument: $argument"
}
