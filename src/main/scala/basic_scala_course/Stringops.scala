package basic_scala_course

object Stringops extends App {
  val str: String = "Hello, Iam learning Scala"

  println(str.charAt(2))
  println(str.substring(7,11))
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))
  println(str.startsWith("Hello"))
  println(str.startsWith("Hello"))

  val numberString: String = "2"
  val number: Int = numberString.toInt
  println('a' +: numberString :+ 'z')
  println(str.take(2))
  var name = "Pedro"
  val age = 23

  val greeting = s"My name is $name and I am $age years old"
  println(greeting)

  val greeting2 = s"My name is $name and I net year I will be ${age+1} years old"
  println(greeting2)

  val speed = 1.2f
  val myth = f"$name%s can eat $speed%2.2f"
  println(myth)

  //println(f"$speed%3d") // error: d is for int but speed is float

  println(raw"Pedro\n Bora")
}
