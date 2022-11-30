import scala.util.Random
import scala.collection.immutable._

object Sequences extends App {
  // Seg
  val aSequence: Seq[Int] = Seq(1, 2, 3, 4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))

  val aRange: Seq[Int] = 1 to 10
  println(aRange)

  println(List(1,2,3) :+ 42)

  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  val numberList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector
  val numbersArray = (1 to maxCapacity).toArray

  //println(getWriteTime(numberList))
  //println(getWriteTime(numbersVector))
  //println(getWriteTime(numbersArray))

  val names = List("Bob", "James","Angela", "Mary", "Daniel", "Jim")

  val mapNames = names.groupBy(name => name.charAt(0))

  val phonebook = Map(("Jim", 555), "JIM" -> 789).withDefaultValue(-1)

  println(phonebook.map(pair => pair._1.toLowerCase() -> pair._2))

  val socialNetwork: SocialNetwork = SocialNetwork()
  socialNetwork.add("Pedro")
  socialNetwork.addFriend("Pedro", "David")
  socialNetwork.addFriend("David", "Antonio")
  socialNetwork.addFriend("Pedro", "Tomas")
  socialNetwork.addFriend("Beatriz", "Nora")
  println("Is social connected " + socialNetwork.isSocialConnected("Pedro", "Nora"))
  println(socialNetwork.personWithMostFriends())
  println(socialNetwork.getNetwork())
  //socialNetwork.remove("Pedro")
  socialNetwork.removeFriend("Pedro", "David")
  println(socialNetwork.getNetwork())
  println(socialNetwork.personWithMostFriends())
  println(socialNetwork.amountOfPeopleNoFriends())
}

case class SocialNetwork() {
  private var network: Map[String, Seq[String]] = Map()

  def add(person: String) = this.network = this.network + (person -> Seq.empty)
  def remove(person:String) = this.network = this.network - person
  def getNetwork() = this.network
  def addFriend(person:String, friend: String) = {
    if(!this.network.contains(person)) this.add(person)
    if(!this.network.contains(friend)) this.add(friend)
    this.network = this.network + (person -> (this.network(person) :+ friend))
    this.network = this.network + (friend -> (this.network(friend) :+ person))
  }

  def removeFriend(person:String, friend: String) = {
    this.network = this.network + (person -> (this.network(person) diff Seq(friend)))
    this.network = this.network + (friend -> (this.network(friend) diff Seq(person)))
  }

  def numberOfFriends(person: String): Int = this.network(person).length

  def peopleNumberOfFriends(): Seq[(String, Int)] = this.network.map(pair => pair._1 -> pair._2.length).toList

  def personWithMostFriends(): String = {
    val personNumFriendsOrdered = this.peopleNumberOfFriends().sorted((pair1: (String, Int), pair2: (String, Int)) => pair1._2 - pair2._2).reverse
    personNumFriendsOrdered(0)._1
  }

  def amountOfPeopleNoFriends(): Int = {
    val personNumFriendsOrdered = this.peopleNumberOfFriends().filter((pair: (String, Int)) => pair._2 == 0)
    personNumFriendsOrdered.map(_._1).length
  }

  import java.util

  //prints BFS traversal from a given source s
  def isSocialConnected(s: String, d: String): Boolean = {
    var temp = null
    // Mark all the vertices as not visited(By default set
    // as false)
    var visited: Map[String, Boolean] = Map().withDefaultValue(false)
    // Create a queue for BFS
    var queue: Queue[String] = Queue()
    // Mark the current node as visited and enqueue it
    visited = visited + (s -> true)
    queue = queue :+ s

    var sTemp = s
    // 'i' will be used to get all adjacent vertices of a vertex
    var i: Iterator[String] = Iterator()
    while(queue.nonEmpty) { // Dequeue a vertex from queue and print it
      val pairTemp = queue.dequeue
      sTemp = pairTemp._1
      queue = pairTemp._2

      var n: String = ""
      i = this.network(sTemp).toIterator
      // Get all adjacent vertices of the dequeued vertex s
      // If a adjacent has not been visited, then mark it
      // visited and enqueue it
      while (i.hasNext) {
        n = i.next
        // If this adjacent node is the destination node,
        // then return true
        if (n == d) return true
        // Else, continue to do BFS
        if (!visited.contains(n)) {
          visited += (n -> true)
          queue = queue.enqueue(n)
        }
      }
    }
    // If BFS is complete without visited d
    false
  }

 }
