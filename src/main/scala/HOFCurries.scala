object HOFCurries extends App {
  val toCurry: ((Int, Int) => Int) => (Int => Int => Int) = f => (x => f(x, _))
  val fromCurry: (Int => Int => Int) => ((Int, Int) => Int) = g => g(_)(_)

  def toCurry(f: (Int, Int) => Int): (Int => Int => Int) = x => y => f(x, y)
  def fromCurry(f: (Int => Int => Int)): ((Int, Int) => Int) = (x, y) => f(x)(y)
/*
  val sumTwo: (Int, Int) => Int = _ + _
  val sumTwoCurried = toCurry(sumTwo)
  println(sumTwoCurried(1)(2))
  val sumTwoOriginal = fromCurry(sumTwoCurried)
  println(sumTwoOriginal(1,2))

  val compose: (Int => Int, Int => Int) => (Int => Int) = (f,g) => (x => f(g(x)))

  val sum1: Int => Int = _ + 1
  val minus1: Int => Int = _ - 1

  println(compose(sum1, minus1)(0)) */
}
