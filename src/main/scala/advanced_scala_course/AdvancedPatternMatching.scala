package advanced_scala_course

object AdvancedPatternMatching extends App {

  val numbner = List(1)

  println(numbner match {
    case head :: Nil => println(s"the only element is $head.")
    case _ =>
  })

  class Person(val name: String, val age: Int)

  object Person {
    def unapply(person: Person): Option[(String, Int)] = Some((person.name, person.age))
  }

  println(new Person("Bob", 23) match {
    case Person(n, a) => s"Hi I am $n and I am $a years old!"
  })

  object singleDigit {
    def unapply(num: Int): Boolean = num > -10 && num < 10
  }

  object even {
    def unapply(num: Int): Boolean = num % 2 == 0
  }
  println(44 match {
    case singleDigit() => "X"
    case even() => "2"
  })

  case class And[A, B](a: A, b: B)
  val either = And(1, "one")
  println(either match {
    case num And string => s"$num is $string"
  })

  abstract class MyList[+A] {
    def head: A = ???
    def tail: MyList[A] = ???
  }

  case object Empty extends MyList[Nothing]
  case class Cons[+A](override val head: A, override val tail: MyList[A]) extends MyList[A]

  object MyList {
    def unapplySeq[A](list: MyList[A]): Option[Seq[A]] =
      if(list == Empty) Some(Seq.empty)
      else unapplySeq(list.tail).map(list.head +: _)
  }

  val myList: MyList[Int] = Cons(1, Cons(2, Empty))
  println(myList match {
    case MyList(1,2, _*) => "1, 2"
    case _ => "Nada"
  })


}
