package advanced_scala_course

import javax.swing.text.html.HTML

object TypeClass extends App {

  trait HTMLWritable {
    def toHTML: String
  }

  case class User(username: String, age: Int) extends HTMLWritable{
    override def toHTML: String = username
  }

  val rep: HTMLWritable = User("pedro", 21)

  object HTMLSerializerPM {
    def serializeToHTML(value: Any) = value match {
      case User(n, _) => n
      case _ => "noting"
    }
  }

  // type class
  trait Equal[T] {
    def apply(obj1: T, obj2: T): Boolean
  }
  object Equal {
    def apply[T](obj1: T, obj2: T)(implicit equalizer: Equal[T]): Boolean =
      equalizer.apply(obj1, obj2)
  }

  // type class instance
  implicit object NameEquality extends Equal[User] {
    override def apply(obj1: User, obj2: User): Boolean = obj1.username.equals(obj2.username)
  }

  // type class instance
  object FullEquality extends Equal[User] {
    override def apply(obj1: User, obj2: User): Boolean =
      obj1.username.equals(obj2.username) && obj1.age == obj2.age
  }

  println(Equal.apply(User("pedro", 23), User("pedro", 22)))
}
