package advanced_scala_course

import sun.nio.cs.Surrogate.Generator

abstract class MyStream[+A] {
  def isEmpty: Boolean
  def head: A
  def tail: MyStream[A]

  def #::[B >: A](element: B): MyStream[B]
  def ++[B >: A](anotherStream: => MyStream[B]): MyStream[B]

  def foreach(f: A => Unit): Unit
  def map[B](f: A => B): MyStream[B]
  def flatMap[B](f: A => MyStream[B]): MyStream[B]
  def filter(predicate: A => Boolean): MyStream[A]

  def take(n: Int): MyStream[A]

  def takeAsList(n: Int): List[A]

}

object EmptyStream extends MyStream[Nothing] {
  override def isEmpty: Boolean = true

  override def head: Nothing = throw new NoSuchElementException()

  override def tail: MyStream[Nothing] = throw new NoSuchElementException()

  override def #::[B >: Nothing](element: B): MyStream[B] = new Cons(element, this)

  override def ++[B >: Nothing](anotherStream: => MyStream[B]): MyStream[B] = anotherStream

  override def foreach(f: Nothing => Unit): Unit = ()

  override def map[B](f: Nothing => B): MyStream[B] = this

  override def flatMap[B](f: Nothing => MyStream[B]): MyStream[B] = this

  override def filter(predicate: Nothing => Boolean): MyStream[Nothing] = this

  override def take(n: Int): MyStream[Nothing] = this

  override def takeAsList(n: Int): List[Nothing] = Nil
}

class Cons[+A](hd: A, t1 : => MyStream[A]) extends MyStream[A] {
  override def isEmpty: Boolean = false

  override val head: A = hd

  override lazy val tail: MyStream[A] = t1

  override def #::[B >: A](element: B): MyStream[B] = new Cons(element, this)

  override def ++[B >: A](anotherStream: => MyStream[B]): MyStream[B] = new Cons(head, tail ++ anotherStream)

  override def foreach(f: A => Unit): Unit = {
    f(head)
    tail.foreach(f)
  }

  override def map[B](f: A => B): MyStream[B] = new Cons(f(head), tail.map(f))

  override def flatMap[B](f: A => MyStream[B]): MyStream[B] = f(head) ++ tail.flatMap(f)

  // flatmap [1,2,...] -> [[1,2], [2,3], ...] ->
  // [1,2] ++
  override def filter(predicate: A => Boolean): MyStream[A] =
    if(predicate(head)) new Cons(head, tail.filter(predicate))
    else tail.filter(predicate)

  override def take(n: Int): MyStream[A] = {
    if(n <= 0) EmptyStream
    else if(n == 1) new Cons(head, EmptyStream)
    else new Cons(head, tail.take(n-1))
  }

  override def takeAsList(n: Int): List[A] =
    if (n <= 0) Nil
    else if (n == 1) List(head)
    else List(head) ++ tail.takeAsList(n - 1)
}

object MyStream {
  def from[A](start: A)(generator: A => A): MyStream[A] =
    new Cons(start, MyStream.from(generator(start))(generator))

  def fibonnaci(): MyStream[Int] = new Cons(0, new Cons(1, fibonnaciAux(0, 1)))
  private def fibonnaciAux(i1: Int, i2: Int): MyStream[Int] = new Cons(i1 + i2, fibonnaciAux(i2, i1 + i2))

  def primes(): MyStream[Int] = new Cons(2, primeAux(3, Seq(2)))

  private def primeAux(i: Int, primes: Seq[Int]): MyStream[Int] = {
    if(primes.exists(i % _ == 0))
      primeAux(i + 1, primes)
    else
      new Cons(i, primeAux(i + 1, primes :+ i))
  }

}