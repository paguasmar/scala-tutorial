package basic_scala_course

import java.util.Random

object TestOption extends App {
  val config: Map[String, String] = Map(
      "host" ->"176.45.36.1",
      "port" ->"80"
  )

  class Connection {
    def connect = "Connected" // connect to some server
  }

  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] =
    if (random.nextBoolean()) Some(new Connection)
    else None
  }
    // try to establish a connection, if so - print the connect method
  val host: Option[String] = config.get("host")
  val port: Option[String] = config.get("port")

  var con2: Option[Connection] = host.flatMap(h => port.flatMap(p => Connection(h, p)))
  con2.foreach(c => println(c.connect))

  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
      map(connection => connection.connect))
    .foreach(println)
}
