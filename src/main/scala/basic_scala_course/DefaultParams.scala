package basic_scala_course

object DefaultParams extends App {

  def testFunc(test:Int, myname: String ="Pedro"):Unit = println(myname)

  testFunc(4)
}
