package oop

object Enums extends App {

  object Main extends Enumeration
  {
    type Main = Value

    // Assigning values
    val first = Value("Thriller")
    val second = Value("Horror")
    val third = Value("Comedy")
    val fourth = Value("Romance")
  }
  println(s"Main Movie Genres = ${Main.values}")
}