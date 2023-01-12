package oop

abstract class MyList {
  def head(): Int
  def tail(): MyList
  def isEmpty(): Boolean
  def add(elem: Int): MyList

  def printElements(): String

  override final def toString: String = "[" + printElements() + "]"
}

object Empty extends MyList {
  override def head(): Int = throw new NoSuchElementException()

  override def tail(): MyList = throw new NoSuchElementException()

  override def isEmpty(): Boolean = true

  override def add(elem: Int): MyList = new Cons(elem, Empty)

  override def printElements(): String = ""
}

class Cons(h: Int, t: MyList) extends MyList {
  override def head(): Int = h

  override def tail(): MyList = t

  override def isEmpty(): Boolean = false

  override def add(elem: Int): MyList = new Cons(elem, this)

  def printElements(): String =
    if(t.isEmpty()) this.h + ""
    else this.h + ", " + this.t.printElements()
}

object ListTest extends App {
  val list: MyList = Empty
  println(list)
  val list2: MyList = list.add(2)
  println(list2)

  val list3: MyList = new Cons(1, Empty)
  println(list3.head())

  val list4: MyList = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(list4)
}