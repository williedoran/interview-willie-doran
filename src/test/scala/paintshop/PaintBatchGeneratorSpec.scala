package paintshop

import org.scalatest.{FlatSpec, Matchers}

class PaintBatchGeneratorSpec extends FlatSpec with Matchers {

  "batch generator" should "create batch from requirements" in {
    val customerRequirements : Seq[Customer] = List(
      Customer(matteId = Some(1)),
      Customer(glossIds = List(1,2)),
      Customer(glossIds = List(5)),
      Customer(matteId = Some(1)),
      Customer(matteId = Some(5))
    )

    val expectedBatch = Batch(Set(1,5), 5)
    val actualBatch = PaintBatchGenerator.generate(customerRequirements, 5)

    actualBatch should equal(expectedBatch)

  }

  "batch generator" should "create output binary string" in {
    val customerRequirements : Seq[Customer] = List(
      Customer(matteId = Some(1)),
      Customer(glossIds = List(1,2)),
      Customer(glossIds = List(5)),
      Customer(matteId = Some(1)),
      Customer(matteId = Some(5))
    )
    val expectedBatchString = "1 0 0 0 1"
    val actualBatch = PaintBatchGenerator.generate(customerRequirements, 5)

    actualBatch.toBinaryRepresentation() should equal(expectedBatchString)

  }

}
