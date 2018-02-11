package paintshop

import org.scalatest.{FlatSpec, Matchers}

class PaintshopAppSpec extends FlatSpec with Matchers {

  "paintshop app" should "read input, work out requirments and print reults" in {
    val fileName = "/multi-test.txt"
    val exectedResults = List(
      "Case #1: 1 0 0 0 0",
      "Case #2: IMPOSSIBLE"
    )
    val results: List[String] = PaintShop.run(fileName)
    results should contain theSameElementsInOrderAs(exectedResults)
  }

}
