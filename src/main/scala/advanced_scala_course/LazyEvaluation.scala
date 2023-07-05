package advanced_scala_course

object LazyEvaluation extends App {

  lazy val x: Int = throw new RuntimeException()

  lazy val xHello: Int = {
    println("Hello")
    42
  }

  println(xHello)
  println(xHello)

  println(xHello == 0 && x == 0)

  def sideEffectCondition: Boolean = {
    println("Boo")
    true
  }
  println( if (false && sideEffectCondition) "yes" else "no" )

  def byNameMethod(n: => Int): Int = {
    n + n + n + n +n +n +n +n +n +1
  }

  /*
  def retrieveValue: Int = {
    println("wiating")
    Thread.sleep(1000)
    42
  }
   println(byNameMethod(retrieveValue))
  */



  def lessThan30(i: Int): Boolean = {
    println(s"$i is less than 30?")
    i < 30
  }
  def greaterThan20(i: Int): Boolean = {
    println(s"$i is greater than 20?")
    i > 20
  }

  val numbers = List(1,25,40,5,23,40)
  val lt30 = numbers.filter(lessThan30)
  val gt20 = lt30.filter(greaterThan20)
  println("--------------------------------")
  val lt30lazy = numbers.withFilter(lessThan30)
  val gt20lazy = lt30lazy.withFilter(greaterThan20)
  gt20lazy.foreach(println)

  lazy val costlyOperation: Int = {Thread.sleep(10000); 42}
  def init(param: Int = costlyOperation) = param

  val numbers2 = for {
    a <- List(1,2,3) if a % 2 == 0
  } yield a + 1

  println(numbers2)

  // stream head is always avaliable but the tail is lazy
  val to100: Stream[Int] = Stream.iterate(21)(_ + 1)
  // to100.foreach(println)
  val lt31 = to100.filter(lessThan30)
  print(lt31(4))

  val complaint_url = "https://www.bbb.org:443"
  println(complaint_url.replace("https://www.bbb.org:443","https://www.bbb.org"))
}
