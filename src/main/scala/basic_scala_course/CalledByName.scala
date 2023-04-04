package basic_scala_course

object CalledByValue extends App {
  def calledByValue(t: Long): Unit = {
    println("by value:" + t)
    println("by value:" + t)
  }

  // Extremely useful in lazy strems
  def calledByName(t: => Long): Unit = {
    println("by name:" + t)
    println("by name:" + t)
  }

  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())

  /*
  by value:8288886021208
  by value:8288886021208
  by name:8288920560000
  by name:8288920583458
   */

  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int): Unit = println(123)

  //printFirst(34, infinite()) // all good
  printFirst(infinite(), 34) // error
}
