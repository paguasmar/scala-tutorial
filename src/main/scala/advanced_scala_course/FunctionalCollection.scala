package advanced_scala_course

object FunctionalCollection extends App {


  case class EmptySet[A]() extends MySet[A] {

    override def apply(v1: A): Boolean = contains(v1)

    def contains(elem: A): Boolean = false

    def +(elem: A): MySet[A] = ConsSet(elem, EmptySet[A])

    def ++(anotherSet: MySet[A]): MySet[A] = anotherSet

    def map[B](f: A => B): MySet[B] = EmptySet[B]

    def flatMap[B](f: A => MySet[B]): MySet[B] = EmptySet[B]

    def filter(predicate: A => Boolean): MySet[A] = EmptySet[A]

    def foreach(f: A => Unit): Unit = ()
  }

  case class ConsSet[A](val head: A, val tail: MySet[A]) extends MySet[A] {
    override def apply(v1: A): Boolean = contains(v1)
    override def contains(elem: A): Boolean = head == elem || tail.contains(elem)
    override def +(elem: A): MySet[A] = {
      if(this contains elem) this
      else ConsSet(elem, this)
    }
    override def ++(anotherSet: MySet[A]): MySet[A] =
      tail ++ anotherSet + head

    // [2,3] ++ [4,5,6] + 1
    // ([3] ++ [4,5,6] + 2) + 1
    // ( ([] ++ [4,5,6] + 3) + 2) + 1
    // ( ( [4,5,6] + 3) + 2) + 1
    // ( [4,5,6,3] + 2) + 1
    // [4,5,6,3,2] + 1
    // [4,5,6,3,2,1]
    // (EmptySet ++ anotherSet) + head
    override def map[B](f: A => B): MySet[B] = tail.map(f) + f(head)

    override def flatMap[B](f: A => MySet[B]): MySet[B] = tail.flatMap(f) ++ f(head)

    override def filter(predicate: A => Boolean): MySet[A] = {
      if (predicate(head)) ConsSet(head, tail.filter(predicate))
      else tail.filter(predicate)
    }

    override def foreach(f: A => Unit): Unit = {
      f(head)
      tail.foreach(f)
    }
  }

  object MySet {
    def apply[A](values: A*): MySet[A] = {
      if(values.isEmpty) EmptySet[A]
      else MySet.apply(values.tail : _*) + values(0)
    }
  }

  val my_set = EmptySet[Int] + 1 + 2 + 3
  val my_set2 = EmptySet[Int] + 4 + 5 + 6 + 3

  val my_set_3 = my_set ++ my_set2
  my_set_3.foreach(println)
  println("-------")
  val my_set4 = MySet(1,2,3,4)
  my_set4.foreach(println)


  def curriedAdder(x: Int)(y: Int) = x + y
  //val add4 = curriedAdder(4)
  val add4: Int => Int = curriedAdder(4)

  val add5 = curriedAdder(5) _

  def sayName(s: String): String = s"Hi $s"

  def curriedFormatter(s: String)(number: Double): String = s.format(number)
  val numbers = List(Math.PI, Math.E, 1, 9.8, 1.3e-12)
  val simpleFormatter = curriedFormatter("%4.2f") _
  val seriousFormatter = curriedFormatter("%8.6f") _
  val preciseFormatter = curriedFormatter("%14.12f") _

  val num = () => 1
  val formatter = (x: Double) => s"Simple format: ${simpleFormatter(x)}\nSerious formatter: ${seriousFormatter(x)}\nPrecise formatter: ${preciseFormatter(x)}\n----"
  numbers.map(formatter).foreach(println)


  def byName(n: => Int) = n + 1
  def byFunction(f: () => Int) = f() + 1
  def method: Int = 42
  def parenMethod(): Int = 42

  /*
  calling byName and byFunction
  - int
  - method
  - parenMethod
  - lambda
  - PAF
   */


}
