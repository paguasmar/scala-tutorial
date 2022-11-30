object ValuesVariableTypes extends App {
  //val x:Int = 42
  //println(s"Olá${x}")

  def aRecursive(a:String, b:Int): String = {
    if(b==1) a
    else a + aRecursive(a,b-1)
  }
  println(aRecursive("Pedro", 3))


  def fibonnaci(n:Long): Long = {
    if(n == 0) 1L
    else if(n == 1) 1L
    else fibonnaci(n-2) + fibonnaci(n-1)
  }
  println(fibonnaci(4))

  var fib:Map[Int, Long] = Map(0 -> 1L, 1 -> 1L)

  def fibonnaciMemory(n: Int): Long = {
    if (fib.contains(n)) fib(n)
    else fibonnaciMemory(n - 2) + fibonnaciMemory(n - 1)
  }

  println(fibonnaci(45))

}
