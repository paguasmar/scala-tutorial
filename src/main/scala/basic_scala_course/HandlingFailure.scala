package basic_scala_course

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {
  // create success and tailure
  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("SUPER FAILURE"))
  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException("NO STRING FOR YOU BUSTER")
  // Try objects via the apply method
  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)


  val hostname = "localhost"
  val port = "8080"

  def renderHTML(page: String) = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>. . . </html>"
      else throw new RuntimeException("Connection interrupted")
    }
  }
  object Httpservice {
      val random = new Random(System.nanoTime())

      def getConnection(host: String, port: String): Connection =
      if (random.nextBoolean) new Connection
      else throw new RuntimeException("Someone else took the port")
  }

  // if I get the html page from the connection, print it to the cosnole i.e. call renderHTML

  Try(Httpservice
    .getConnection(hostname, port))
    .flatMap(c => Try(c.get(hostname))
      .map(renderHTML)
    )

}
