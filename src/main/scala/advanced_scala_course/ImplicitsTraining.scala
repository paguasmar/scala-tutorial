package advanced_scala_course

object ImplicitsTraining extends App {
  case class Person(name: String, age: Int)

  val people = List(
    Person("Steve", 30),
    Person("Amy", 22),
    Person("John", 66)
  )

  implicit val orderingPerson: Ordering[Person] = Ordering.fromLessThan(_.age < _.age)
  println(people.sorted)


  case class Purchase(nUnits: Int, unitPrice: Double)

  val purchases = List(Purchase(10, 10), Purchase(1, 10), Purchase(2, 20))

  object Purchase {
    implicit val totalPriceOrdering: Ordering[Purchase] = Ordering.fromLessThan((a, b) => a.nUnits * a.unitPrice < b.nUnits * b.unitPrice)
  }

  object PurchaseUnitCountOrdering {
    implicit val unitCountOrdering: Ordering[Purchase] = Ordering.fromLessThan((a, b) => a.nUnits < b.nUnits)
  }

  object PurchaseUnitPriceOrdering {
    implicit val unitPriceOrdering: Ordering[Purchase] = Ordering.fromLessThan((a, b) => a.unitPrice < b.unitPrice)
  }

  import PurchaseUnitPriceOrdering.unitPriceOrdering

  println(purchases.sorted)
}
