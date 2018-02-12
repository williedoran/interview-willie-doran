package paintshop

import org.scalatest.{FlatSpec, Matchers}

class RequirementCheckerSpec extends FlatSpec with Matchers {

  "requirement checker" should "return true for all these case"  in {
    val customerRequirements : Seq[Customer] = List(
      Customer(matteId = Some(1)),
      Customer(glossIds = List(1,2)),
      Customer(glossIds = List(5))
    )
    val expectedResults = List(true, true, true)
    val batch = Batch(matteIds = Set(1), 5)
    val results = customerRequirements.map(req =>  RequirementChecker.checkIndividualCustomerRequirements(batch, req))
    results should contain theSameElementsAs(expectedResults)
  }

  "requirement checker" should "return false as paint cant be both matte and gloss and no others can satisfy customer 2"  in {
    val customerRequirements : Seq[Customer] = List(
      Customer(matteId = Some(1)),
      Customer(glossIds = List(1))
    )
    val expectedResults = List(true, false)
    val batch = Batch(matteIds = Set(1), 1)
    val results = customerRequirements.map(req =>  RequirementChecker.checkIndividualCustomerRequirements(batch, req))
    results should contain theSameElementsAs(expectedResults)
  }

  "requirement checker" should "return true for mixed gloss and matte requirements"  in {
    val customerRequirements : Seq[Customer] = List(
      Customer(matteId = Some(1), glossIds = List()),
      Customer(matteId = Some(2), glossIds = List(5,6)),
      Customer(matteId = Some(4)),
      Customer(glossIds = List(2, 3))
    )
    val expectedResults = List(true, true,true, true)
    val batch = Batch(matteIds = Set(1,2,4), 6)
    val results = customerRequirements.map(req =>  RequirementChecker.checkIndividualCustomerRequirements(batch, req))
    results should contain theSameElementsAs(expectedResults)
  }

  "requirement" should "return true for minimal batch" in {
    val customerRequirements : Seq[Customer] = List(
      Customer(matteId = Some(1)),
      Customer(glossIds = List(1,2)),
      Customer(glossIds = List(5)),
      Customer(matteId = Some(4), glossIds = List(5))
    )
    val expectedResults = List(true, true,true, true)

    val batch = Batch(Set(1), 5)
    val results = customerRequirements.map(req =>  RequirementChecker.checkIndividualCustomerRequirements(batch, req))
    results should contain theSameElementsAs(expectedResults)

  }

  "requirement" should "return fail for final case minimal batch" in {
    val customerRequirements : Seq[Customer] = List(
      Customer(matteId = Some(1)),
      Customer(glossIds = List(1,2)),
      Customer(glossIds = List(5)),
      Customer(matteId = Some(4), glossIds = List(5)),
        Customer(matteId = Some(2), glossIds = List(1))
    )
    val expectedResults = List(true, true,true, true, false)

    val batch = Batch(Set(1), 5)
    val results = customerRequirements.map(req =>  RequirementChecker.checkIndividualCustomerRequirements(batch, req))
    results should contain theSameElementsAs(expectedResults)

  }

  "batch create" should "not have higher color ids than num colours"  in {
    an[IllegalArgumentException] shouldBe thrownBy {
      Batch(matteIds = Set(1,2,4,7), 6)
    }
  }

  "all passing requirements" should "return binary string of batch" in {
    val customerRequirements : Seq[Customer] = List(
      Customer(matteId = Some(1), glossIds = List()),
      Customer(matteId = Some(2), glossIds = List(5,6)),
      Customer(matteId = Some(4)),
      Customer(glossIds = List(2, 3))
    )
    val batch = Batch(matteIds = Set(1,2,4), 6)
    val expectedResult = batch.toBinaryRepresentation
    val actualCheckResult = RequirementChecker.checkAllRequirements(batch, requirements = customerRequirements)

    actualCheckResult should equal(expectedResult)
  }

}
