package oop

object Inheritance extends App {

  class Animal {
    private val age = 0
    val creatureType = "wild"
    protected def eat = println("nomnomnom")
  }

  class Cat extends Animal {

    // def getAge = age // does not work
    def crunch = {
      eat
      println("crunch crunch")
    }

    override def eat: Unit =
      println("au au au")
  }

  class Person(name: String, age: Int)
  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)
}
