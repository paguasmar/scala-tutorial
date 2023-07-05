package advanced_scala_course

object ExercisesMonads extends App {

  // abstract a computation that is only executed when it is needed
  class Lazy[+A](value: A) {
    private lazy val internalValue = value

    def use: A = internalValue
    def flatMap[B](f: (=> A) => Lazy[B]): Lazy[B] = f(internalValue)
  }

  object Lazy {
    def apply[A](value: => A): Lazy[A] = new Lazy(value)
  }

  val lazyInstance = Lazy {
    println("Test")
    42
  }

  val u1 = lazyInstance.flatMap(x => Lazy(x * 10))
  val u2 = lazyInstance.flatMap(x => Lazy(x * 20))

  println(lazyInstance.use)
}
