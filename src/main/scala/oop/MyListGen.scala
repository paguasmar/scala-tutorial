package oop

abstract class MyListGen[+A] {
  def head(): A
  def tail(): MyListGen[A]
  def isEmpty(): Boolean
  def add[B >: A](elem: B): MyListGen[B]
  def printElements(): String
  override final def toString: String = "[" + printElements() + "]"
}

object EmptyGen extends MyListGen[Nothing] {
  override def head(): Nothing = throw new NoSuchElementException()

  override def tail(): MyListGen[Nothing] = throw new NoSuchElementException()

  override def isEmpty(): Boolean = true

  override def add[B >: Nothing](elem: B): MyListGen[B] = new ConsGen(elem, EmptyGen)

  override def printElements(): String = ""
}

class ConsGen[+A](h: A, t: MyListGen[A]) extends MyListGen[A] {
  override def head(): A = h

  override def tail(): MyListGen[A] = t

  override def isEmpty(): Boolean = false

  override def add[B >: A](elem: B): MyListGen[B] = new ConsGen(elem, this)

  def printElements(): String =
    if(t.isEmpty()) this.h + ""
    else this.h + ", " + this.t.printElements()
}

object ListTest2 extends App {
  val list: MyListGen[Int] = EmptyGen
  println(list)

  val list2: MyListGen[Int] = list.add(2)
  println(list2)

  val list3: MyListGen[Int] = new ConsGen(1, EmptyGen)
  println(list3.head())

  val list4: MyListGen[Int] = new ConsGen(1, new ConsGen(2, new ConsGen(3, EmptyGen)))
  println(list4)
}