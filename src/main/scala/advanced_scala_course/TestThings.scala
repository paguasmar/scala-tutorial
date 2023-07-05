package advanced_scala_course

import scala.util.matching.Regex

object TestThings extends App {

  val phoneNumber: String = "ext"
  val extensionPrefix: String = """ext|x|ex"""
  val extensionRegex: Regex = s"""(($extensionPrefix)|cell($extensionPrefix)?|home($extensionPrefix)?)""".r
  val list: Array[String] = extensionRegex.split(phoneNumber)
  println(if (list.length == 2)
    (list(0), Some(list(1)))
  else
    (list(0), None))


}
