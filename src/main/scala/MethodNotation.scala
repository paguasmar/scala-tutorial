import scala.language.postfixOps

object MethodNotation extends  App {
  class Person(val name: String, favoriteMovie: String) {
    def likes(movie: String): Boolean = movie == favoriteMovie

    def unary_!() : Person = new Person(s"- ${this.name}", this.favoriteMovie)

    def +(txt: String): Person = new Person(this.name + txt, this.favoriteMovie)

    def learns(txt:String) = s"$this.name learns $txt"

    def learnsScala() = this learns "Scala"

    def apply(num: Int): String = ""+num
  }

  val mary = new Person("Marry", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception")

  println((!mary).name)
  println(1.unary_-)

  println((mary + "Pedro").name)
  println(mary learnsScala)
  println(mary(2))
}
