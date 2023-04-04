package basic_scala_course

object Generics extends App {
  class MyList[A] {

  }

  class MyMap[Key, Map]

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  object MyList

  class Animal
  class Cat extends Animal

  class Dog extends Animal

  // yes List[Cat] extends List[Animal]
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animaLList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add (newDog)
}
