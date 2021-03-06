import scala.annotation.tailrec
import scala.io.StdIn._
import scala.math.BigDecimal.int2bigDecimal
import scala.math.BigInt.int2bigInt

object Main {
  //----------------------------------------------------//
  def isSnakeCase(string: String) : Boolean = {
    !string.contains(" ") &&
      !string.contains("__") &&
      !string.matches(".*()[A-Z0-9].*") &&
      !string.matches("^_.*") &&
      !string.matches(".*_$")
  }

  //----------------------------------------------------//
  def fibs1(num: Int) : Long = {
    @tailrec def go(prev : Int, cur : Int, pos: Int, acc: Long) : Long = {
      if (pos > num) acc
      else go(cur, prev + cur, pos + 1, acc + cur)
    }

    if (num == 1) 1
    else if (num == 2) 1
    else go(0, 1, 3, 1)
  }

  //----------------------------------------------------//
  @tailrec def fibs(num: BigInt, cur: BigInt = 1, f1: BigInt = 0, f2: BigInt = 1) : BigInt = {
    if (num == cur) f2
    else fibs(num, cur + 1, f2, f1 + f2)
  }

  //----------------------------------------------------//
  def sendGift(currentAmount: Int, gift: => Int) = {
    if (currentAmount >= 500)
      currentAmount + gift
    else
      currentAmount
  }

  val getGift = () => {
    println("The gift is received")
    100//readLine.toInt
  }

  val accountAmounts = List(100, 200, 500, 300, 700)

  //----------------------------------------------------//
  val mul3 = 3*(_: Double)
  val pow2 = (x: Double) => x*x
  val result = (pow2.andThen[Double] _)(mul3)(5)

  //----------------------------------------------------//
  object LessonData{
    def searchInArray(cond: Int => Boolean, array: List[Int]): List[Int] = {
      array.filter(cond)
    }
  }
  val searchOdd: List[Int] => List[Int] = array => LessonData.searchInArray(_ % 2 != 0, array)

  //----------------------------------------------------//
  def middle[A](data: Iterable[A]) : A = {
    data.splitAt(data.size / 2)._2.head
  }


  //----------------------------------------------------//
  case class Pet(name: String, says: String)
  val pet = Pet("Rex", "0")

  val saysMeow = (says : String) => says == "meow" || says == "nya"
  val saysRobot = (says : String) => says.contains("0") || says.contains("1")

  val kind = pet match {
    case pet if pet.name == "Rex" && !saysMeow(pet.says) && !saysRobot(pet.says) => "dog"
    case pet if pet.name != "Rex" && saysMeow(pet.says) && !saysRobot(pet.says) => "cat"
    case pet if pet.name != "Rex" && saysRobot(pet.says)  => "robot"
    case _ => "unknown"
  }

  val robotRe = "(0|1)+".r
  val kind2 = pet match {
    case Pet("Rex", _) => "dog"
    case Pet(_, "meow"|"nya") => "cat"
    case Pet(_, robotRe(_)) => "robot"
    case Pet(_, _) => "unknown"
  }

  //----------------------------------------------------//
  def commitValidator(input: List[String]) = {
    val r_name = "([a-zA-Z]+)".r
    val r_emailDomain = "\\w+@\\w+\\.\\w+".r

    val result = input match {
      case List(r_name(name), _*) => name
      case List(name@r_emailDomain(em), _*) => s"$name $em"
      case _ => "invalid"
    }
    result
  }

  def test(result: String, correctResult: String) = {
    if (result != correctResult)
      println("Failed: [" + result + "]. Should be [" + correctResult + "]")
      else
      println("PASSED")
  }

  def main(args: Array[String]) : Unit = {


    test(commitValidator(List("")), "invalid")
    test(commitValidator(List("oleg", "oleg@email.com", "7bdaf0a1be3", "a8af118b1a2", "28d74b7a3fe")), "oleg email.com")
    test(commitValidator(List("oleg oleg@email.com", "7bdaf0a1be3", "a8af118b1a2", "28d74b7a3fe")), "oleg email.com")
  }
}
