package paintshop
import org.scalatest.{FlatSpec, Matchers}


class InputParserSpec extends FlatSpec with Matchers {

  "input parser" should "read customers list from individual case" in {
    val expectedNumColours : Int = 5
    val expectedCustomers : Seq[Customer] = List(
      Customer(matteId = Some(1)),
      Customer(glossIds = List(1,2)),
      Customer(glossIds = List(5))
    )

    val singleCaseInputLines =
      """|5
        |3
        |1 1 1
        |2 1 0 2 0
        |1 5 0""".stripMargin.split("\n").toList
    val (actualNumColours, actualCustomerRequirements) = InputParser.parse(singleCaseInputLines)
    actualNumColours should equal(expectedNumColours)
    actualCustomerRequirements should contain theSameElementsAs(expectedCustomers)

  }

  "parser" should "throw exception on malformed customer input" in {
    an[IllegalArgumentException] shouldBe  thrownBy {
      InputParser.extractCustomerRequirements("3 1 0 2 0")
    }
  }

  "parser" should "correctly parse a customer from customer input" in {
    val expectedCustomer = Customer(matteId = Some(3), glossIds = List(1,2))
    val actualCustomer = InputParser.extractCustomerRequirements("3 1 0 2 0 3 1")
    actualCustomer should equal(expectedCustomer)
  }
}
