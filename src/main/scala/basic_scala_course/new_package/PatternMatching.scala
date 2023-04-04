package basic_scala_course.new_package

object PatternMatching extends App {

  // sealed is used Declaring a class or trait as sealed restricts where we can define its subclasses — we have to define them in the same source file.

  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greeting: String) extends Animal

  val animal: Animal = Dog("Terra Nova")
  animal match {
    case Dog(someBreed) => println(s"Matched a dog of the $someBreed breed")
  }

  sealed abstract class Expr()
  case class Number(a: Int) extends Expr
  case class Sum(a: Expr, b: Expr) extends Expr
  case class Prod(a: Expr, b: Expr) extends Expr
  val expression = Prod(Sum(Number(2), Number(3)), Sum(Number(4), Number(5))) // 2 * 3 + 4 * 5
  val res: Expr => Int = _ match {
    case Number(n) => n
    case Sum(a, b) => res(a) + res(b)
    case Prod(a, b) => res(a) * res(b)
  }

  def show(e: Expr): String = e match {
    case Number(n) => s"$n"
    case Sum(a, b) => s"${show(a)} + ${show(b)}"
    case Prod(a, b) => {
      def maybeShowParentheses(exp: Expr) = exp match {
        case Prod(_, _) => show(exp)
        case Number(_) => show(exp)
        case _ => s"(${show(exp)})"
      }
      s"${maybeShowParentheses(a)} * ${maybeShowParentheses(b)}"
    }
  }

  // create that prints a tree structure with branches takes a Lineage, if Lineage if a Person then return the name, if the Lineage is a Couple with the output of the function applied to the two Lineages
  sealed abstract class Lineage
  case class Person(name: String) extends Lineage
  case class Couple(father: Lineage, mother: Lineage) extends Lineage

  def printTree(lineage: Lineage): String = lineage match {
    case Person(name) => s"$name"
    case Couple(father, mother) => s"${printTree(father)} + ${printTree(mother)}"
  }

  val lineage = Couple(Person("Pedro"), Person("Beatriz"))
  println(printTree(lineage))

  // create a function that takes a Family and prints a binary tree structure of the family
  sealed abstract class Family
  case class FamilyMember(name: String, children: List[Family]) extends Family
  case class FamilyCouple(father: Family, mother: Family) extends Family

  val tuple = (1,2,3)
  val (n1, n2, n3) = tuple
  println(n1)



val family = FamilyCouple(FamilyMember("Pedro", List(FamilyMember("Beatriz", List()))), FamilyMember("Maria", List(FamilyMember("Joao", List()))))
  println(show(expression))

  //

  println(res(expression))
  // Sum(Prod(Number(2), Number(3)), Prod(Number(4), Number(5)))
  // Prod(Number(2), Number(3)) + Prod(Number(4), Number(5))
  // Number(2) * Number(3) + Number(4) * Number(5)
  // 2 * 3 + 4 * 5 = 26

  // 3 - tuple
  val aTuple = (1,2)
  val person = Person("Pedro")
  val test = person match {
    case _: Person => s"Hi, my name is"
    case _ => "I don't know my name"
  }

    println(test)


  val list = List(1,2,3,4,5)
  val head :: tail = list
  println(head)

  val mappedList = list.map {
    case v if v % 2 == 0 => s"$v is even"
    case 1 => "The one"
    case _ => "Odd"
  }

  println(mappedList)

}
