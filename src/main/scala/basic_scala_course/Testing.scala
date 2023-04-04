package basic_scala_course

object Testing extends App {
  def listTuples(list1: List[String], list2: List[Int], list3: List[String]): List[(String, Int, String)] = {
    for {
      x <- list1
      y <- list2
      z <- list3
    } yield (x, y, z)
  }

  // Example usage:
  val list1 = List("red", "blue", "yellow")
  val list2 = List(1, 2, 3)
  val list3 = List("$", "&", "|")
  val tuples = listTuples(list1, list2, list3)

  //println(tuples)

  // function to compute the average of two ints
  def average(a: Int, b: Int): Double = (a + b) / 2.0

  //println(average(2,5))

  //  Option[Seq[String]]

  //create case class employee containing email as optional string
  case class Employee(email: Option[String]) {
    def apply(email: Option[String]): Employee = new Employee(email)
  }

  val employees_contacts: Some[Seq[Employee]] = Some(Seq(
    Employee(None), Employee(None),
    Employee(Some("p"))))

  val res = employees_contacts.flatMap(_.flatMap(_.email).headOption)

  //println(res)

  def parseStars(stars: Option[Long]): Option[Long] = {
    stars match {
        case Some(1L) | Some(20L) => Some(1L)
        case Some(2L) | Some(40L) => Some(2L)
        case Some(3L) | Some(60L) => Some(3L)
        case Some(4L) | Some(80L) => Some(4L)
        case Some(5L) | Some(100L) => Some(5L)
        case _ => None
      }
  }
  println(parseStars(Some(4L)))
   val x: Int = 1

}
