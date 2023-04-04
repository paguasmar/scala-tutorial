package basic_scala_course

import scala.annotation.tailrec

object Recursion extends App{
  def nameAge(name:String, age:Int):String = {
    s"$name $age"
  }
  //println(nameAge("Pedro", 22))

  def isPrime(prime_num:Int): Boolean = {
     def isPrimeAux(n:Int):Boolean = {
       if (n <= 1) true
       else prime_num % n != 0 && isPrimeAux(n - 1)
     }
    isPrimeAux(prime_num/2)
  }

  //println(isPrime(1))

  def factorial(n: Int): Long = {
    if(n <= 1) 1L
    else {
      val result = n * factorial(n-1)

      result
    }
  }

  def anotherFactorial(n:Int): BigInt = {
    @tailrec
    def factHelper(x: Int, accumulator: BigInt): BigInt = {
      if(x <= 1) accumulator
      else factHelper(x-1, x * accumulator)
    }

    factHelper(n, 1)
  }

  //println(factorial(20))


  def concatString(n:Int, s:String): String = {
    def concatStringRec(accumulator: String, t:Int): String = {
      if(t <= 0) accumulator
      else concatStringRec(accumulator + s, t - 1)
    }
    concatStringRec("", n)
  }

  //println(concatString(3, "Pedro"))

  def isPrimeIt(n:Int): Boolean = {
    @tailrec
      def isPrimeAux(accumulator: Boolean, t: Int): Boolean = {
          if(t <= 1) true && accumulator
          else isPrimeAux(n % t != 0 && accumulator, t - 1)
      }
    isPrimeAux(true, n/2)
  }

  //println(isPrimeIt(10))

  def fibonnaciIt(n: Int): BigInt = {
      def fib(t: Int, prev: BigInt, prevPrev: BigInt): BigInt = {
        if (t >= n) prev
        else fib(t + 1, prev + prevPrev, prev)
      }
    fib(1,1,1)
  }

  println(fibonnaciIt(4))
}
