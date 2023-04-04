package advanced_scala_course

object PartialFunctions extends App {


   val giveDesc: PartialFunction[Int, String] = {
     case 1 => "Um"
     case 2 => "Dois"
     case 3 => "Tres"
   }

  println(giveDesc.lift(11))

  val pfChain: PartialFunction[Int, String] = giveDesc.orElse[Int, String] {
    case 42 => "Quarenta e dois"
  }

  case class Square(val h: Int, val w: Int)

  val widths: List[Int] = List(Square(1,2), Square(1,3), Square(1,4)).map {
    case Square(_, w) => w
  }

  println(widths)

  val test = new PartialFunction[Int, String] {
    override def isDefinedAt(x: Int): Boolean = List(1, 2, 3).contains(x)

    override def apply(v1: Int): String = v1 match {
      case 1 => "Um"
      case 2 => "Dois"
      case 3 => "Tres"
    }
  }


  println(test(1))


    val chatbot: PartialFunction[String, String] = {
      case "hi" => "Hello!"
      case "whats ur name?" => "I am chatbot! :)"
    }
  scala.io.Source.stdin.getLines().foreach(line => println(chatbot(line)))

}
