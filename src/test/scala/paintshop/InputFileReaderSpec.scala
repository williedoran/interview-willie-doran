package paintshop

import org.scalatest.{FlatSpec, Matchers}

class InputFileReaderSpec extends FlatSpec with Matchers {

  "file reader" should "read a single case from file" in {
    val fileName = "/single-test.txt"
    val expectedNumCases = 1
    val expectedSingleCaseInputLines =
      """|5
         |3
         |1 1 1
         |2 1 0 2 0
         |1 5 0""".stripMargin.split("\n").toList

    val (acutalNumberCases, inputCases) = InputFileReader.readCases(fileName)
    acutalNumberCases should equal(expectedNumCases)
    inputCases should contain theSameElementsInOrderAs List(expectedSingleCaseInputLines)

  }

  "file reader" should "read a multiple case from file" in {
    val fileName = "/multi-test.txt"
    val expectedNumCases = 2
    val expectedCase1 =
      """|5
         |3
         |1 1 1
         |2 1 0 2 0
         |1 5 0""".stripMargin.split("\n").toList
    val expectedCase2 =
      """|1
         |2
         |1 1 0
         |1 1 1""".stripMargin.split("\n").toList

    val (acutalNumberCases, inputCases) = InputFileReader.readCases(fileName)
    acutalNumberCases should equal(expectedNumCases)
    inputCases should contain theSameElementsInOrderAs List(expectedCase1, expectedCase2)

  }
  "file reader" should "throw exception in unexpected cases" in {
    val fileName = "/unexpected-case-difference-test.txt"

    an[IllegalArgumentException] shouldBe thrownBy {
      InputFileReader.readCases(fileName)
    }

  }

}
