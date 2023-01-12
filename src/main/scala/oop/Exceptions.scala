package oop

object Exceptions extends App {
  def errorMethod: Int = throw new NullPointerException()

  try {
    println(errorMethod)
  } catch {
    case e: NullPointerException => println("Sorry!")
    case _: Throwable => println("Unknown")
  } finally {
    println("Runs no matther what!")
  }

  class MyException extends Exception {
  }

  val excpetion = new MyException

  // throw new OutOfMemoryError()
  //throw new StackOverflowError()

  class PedroProgramException extends Exception
  class OverflowException extends PedroProgramException
  class UnderflowException extends PedroProgramException
  object PocketCalculator {
    def add(x:Int, y:Int): Int = {
      val result: Int = x + y
      if(x > 0 && y > 0 && result < 0) throw new OverflowException()
      else if(x < 0 && y < 0 && result > 0) throw new UnderflowException()
      else result
    }
  }
  PocketCalculator.add(Int.MinValue, -10)

  //var acc:String = "Martin Berger (martinberger) said (edited on Jul 13, 2015 8:05:11 PM UTC):\nI'm surprised that the order of named constants does matter for the generated code. The arguments and their target order are known at compile-time, so why does that lead to more code? I guess maybe that's because argument evaluation has side effects and they should not be changed by the reordering? But that amounts to translating\n"
  //val sb = new StringBuilder(acc)
  //while(true) {
  //  sb.append(sb.toString() ++ sb.toString() ++ sb.toString() ++ sb.toString() ++ sb.toString())
  //}
  /*
  def infinite(): Int = {
    1 + infinite()
  }
  infinite()
  */
}
