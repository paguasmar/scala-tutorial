package oop

object OOPbasics extends App {
  /*
  val person = new Person("Pedro", 22)
  println(person.age)
  println(person.x)

  val mary: Person = new Person("Mary", 22)
  val pedro: Person = new Person("Pedro", 22)
  println(mary == pedro)

  val person1: Person = Person(mary, pedro)
  val person2: Person = Person(mary, pedro)
*/

}

class Writer(val name: String, val age: Int) {
  def fullName(): String = name + " " + age

  def ==(other: Writer): Boolean = {
    name == other.name && age == other.age
  }
}

class Novel(val name: String, val year: Int, val author: Writer) {
  def authorAge(): Int = year - author.age
  def isWrittenBy(author: Writer): Boolean = author == this.author
  def copy(newYear: Int): Novel = new Novel(name, newYear, author)
}

class Counter(val num: Int) {
  def inc(): Counter = new Counter(num + 1)
  def dec(): Counter = new Counter(num - 1)
  def inc(n: Int): Counter = new Counter(num + n)
  def dec(n: Int): Counter = new Counter(num - n)
  def print(): Unit = println(num)
}

object Person {
  val N_EYES = 2
  def canFly: Boolean = false
  def apply(mother: Person, father: Person): Person = new Person("Bobbie")
}
class Person(name: String, val age: Int = -1) {
  val x: String = s"$name " * 4

  println(age + 1)

  def greeting(name: String): Unit = println(s"Hi, $name my name is ${this.name} and I am ${this.age} years old")

  // def this(fullName: String, age: Int) = this(fullName.split(" ")(0), age)
}
