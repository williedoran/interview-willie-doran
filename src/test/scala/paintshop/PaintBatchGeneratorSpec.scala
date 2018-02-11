package paintshop

import org.scalatest.{FlatSpec, Matchers}

class PaintBatchGeneratorSpec extends FlatSpec with Matchers {

  "batch generator" should "create batch from requirements" in {
    val customerRequirements : Seq[Customer] = List(
      Customer(matteId = Some(1)),
      Customer(glossIds = List(1,2)),
      Customer(glossIds = List(5))
    )


  }

}
