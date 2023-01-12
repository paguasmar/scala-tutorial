package oop

object AnonymousClasses extends App {

  abstract class Animal {
    def eat: String
  }

  trait Box {
    def size: Float
  }

  val cat = new Animal with Box {
    def eat() = "fish"

    def size() = 2.3f
  }

  println(cat.eat())

  println(cat.size())
}
