package basic_scala_course

object FltaMap extends App {

  val list = List(1, 2, 3)

  println(list.headOption.getOrElse(-1))

  val  toPair: Int => List[List[Int]] = x => List(List(x), List(x+1))

  println(list.map(toPair))
  println(list.flatMap(toPair))

  // print all combinations between two lists
  val numbers = List(1, 2, 3, 4)
  val chars = List('a', 'b', 'c', 'd')

  println(numbers.flatMap(n => chars.map(c => "" + c + n)))

  val coins = List('1', '2', '3', '4')
  val toys = List('q', 'w', 'p', 'w')
  val symbols = List('#', '%', '?', '=')

  println(coins.flatMap(c => toys.flatMap(t => symbols.map(s => "" + c + t + s))))

  val forCombinations = for {
    c <- coins
    t <- toys
    s <- symbols
  } yield "" + c + t + s

  println(forCombinations)
}
