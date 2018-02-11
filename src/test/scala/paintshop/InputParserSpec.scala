package paintshop
import org.scalatest.{FlatSpec, Matchers}


class InputParserSpec extends FlatSpec with Matchers {

  "input parser" should "read customers list from individual case" in {
    val expectedNumColours : Int = 5
    val expectedNumCustomers : Int = 3
    val expectedCustomers : Seq[Seq[CustomerRequirement]] = List(List(Matte(1)), List(Glossy(1),Glossy(2)), List(Glossy(5)))

    val singleCaseInputLines =
      """|5
        |3
        |1 1 1
        |2 1 0 2 0
        |1 5 0""".stripMargin.split("\n").toList
    val (actualNumColours, actualNumCustomers, actualCustomerRequirements) = InputParser.parse(singleCaseInputLines)
    actualNumColours should equal(expectedNumColours)
    actualNumCustomers should equal(expectedNumCustomers)
    actualCustomerRequirements should contain theSameElementsAs(expectedCustomers)

  }

}
