package basic_scala_course

object WhatsAFunction extends App {

 val concatenate: (String, String) => String = new Function2[String,String, String] {
   override def apply(a: String, b:String): String = a+b
 }

  println(concatenate("Pedr", "o"))

  val functionInt: Int => Function1[Int, Int] = new Function1[Int, Function1[Int, Int]] {
    override def apply(elem: Int): Function1[Int, Int] = new Function1[Int, Int] {
        override def apply(elem2: Int): Int = elem + elem2
    }
  }

  println(functionInt(3)(4))

  val multiplyBy2: Int => Int = x => x*2

  println(multiplyBy2(8))

  val addNumbers: (Int, Int) => Int = (x, y) => x*y
  println(addNumbers(2,3))

  val giveMePI: () => Float = () => 3.14f

  val addNumbers3: (Int, Int, Int) => Int = (x,y,z) => {
    var acc: Int = x+y
    acc += z
    acc
  }

  println(addNumbers3(1,2,3))

  println(giveMePI)
  println(giveMePI())

  val addNumb: (Int,Int) => Int = _ + _
  println(addNumb(2,3))

  val functionInt2: Int => (Int => Int) = x => (y => x + y)
  println(functionInt2(2)(3))

  val nums: List[Int] = List(1,2,3,4)
  println(nums.filter(_ % 2 == 0))

  def nTimes(f: Int => Int, n:Int, x: Int): Int = {
    if(n <= 0) x
    else nTimes(f, n-1, f(x))
  }

  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) = {
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n - 1)(f(x))
  }

  val plus1: Int => Int = _ + 1
  val plus10: Int => Int = nTimesBetter(plus1, 10)

  println(nTimes(plus1, 3, 0))

  println(plus10(10))

  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))
}

abstract class MyFunction[A, B] {
  def apply(elem: A): B
}

abstract class MyFunction2[A, B, C] {
  def apply(elem1: A, elem2: B): C
}