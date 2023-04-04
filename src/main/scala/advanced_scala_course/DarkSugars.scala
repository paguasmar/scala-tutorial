package advanced_scala_course

import java.lang.invoke.MutableCallSite
import scala.util.Try

object DarkSugars extends App {

    // #1: methods with single param
    def singleArgMethod(arg: Int): String = s"$arg little ducks..."

    val description = singleArgMethod {
      val num: Int = 32 + 20
      num - 42
    }

  //println(description)


    // apply method from Try
    val aTryInstance = Try { // java's try {...}
      throw new RuntimeException
    }

    List(1, 2, 3).map { x =>
      x + 1
    }


  trait Action2 {
    def func(x:Int): Int
    def func2(s:String):String
  }
  val jump: Action2 = new Action2 {
    override def func2(s:String):String = s + "jump"

    override def func(x: Int): Int = x+2
  }
  //println(jump.func(2))
    // #2: single abstract method
    trait Action {
      def act(x: Int): Int
    }

    val anInstance: Action = (x: Int) => x + 1 // magic
    // (x: Int) => x + 1 === new Action { def act(x: Int): Int = x + 1 }

    val bThread = new Thread(new Runnable {
      override def run(): Unit = println("Im running")
    })
    //bThread.start()

  abstract class AbstractClassEx{
    final def hi(): String = "Hi!"

    def func(f:Int):Int
  }

  val hIntance: AbstractClassEx = (g: Int) => g + 1
  //println(hIntance.func(4))

    // example: Runnables
    val aThread = new Thread(new Runnable {
      override def run(): Unit = println("hello, Scala")
    })

    val aSweeterThread = new Thread(() => println("sweet, Scala"))

    abstract class AnAbstractType {
      def implemented: Int = 23
      def f(a: Int): Unit
    }

    val anAbstractInstance: AnAbstractType = (a: Int) => println("sweet")

    // #3: the :: and #:: methods are special
    val prependedList = 0 :: 1 :: List(3, 4).::(2)
    // 2.::(List(3, 4)) // equivalent
    // List(3, 4).::(2) // equivalent

    // scala spec: last char decides associativity of method
    1 :: 2 :: 3 :: List(4, 5)
    List(4, 5).::(3).::(2).::(1) // equivalent

  println(prependedList)
  // methods that end in : are right associative
    class MyStream[T] {
      def -->:(value: T): MyStream[T] = this // actual implementation here
    }

    val myStream = 1 -->: 2 -->: 3 -->: new MyStream[Int]

    // #4: multi-word method naming
    class TeenGirl(name: String) {
      def `and then said`(gossip: String): Unit = println(s"$name said $gossip")
    }

    val lilly = new TeenGirl("Lilly")
    lilly `and then said` "Scala is so sweet!"

   class Food(name: String) {
     def `eaten by`(person: String): Unit = println(s"$person ate $name")
   }

  println(new Food("apple") `eaten by` "Pedro")

  class Dual[A, B]
  val composite: Int Dual String = ???

  val anArray = Array(1,2,3)

  class Mutable {
    private var num: Int = 0
    def value = num
    def value_=(value: Int): Unit = {
      num = value
    }
  }


}
