package advanced_scala_course

object PimpMyLib extends App {

  implicit class RichInt(value: Int) {
    def isEven: Boolean = value % 2 == 0
    def double: Int = value + value
    def *[T](list: List[T]): List[T] = (0 to value).flatMap(_ => list).toList
  }


  implicit class RichString(text: String) {
    def encrypt: String = text.map(c => (c + 2).toChar)
  }

  implicit def stringToInt(text: String): Int = Integer.parseInt(text)

  println("John".encrypt)
  println(3*List("ola", "adeus"))
  println("10" / 2)
}
