package paintshop

object PaintShopApp extends App {
  def run(fileName: String): List[String] = {

    InputFileReader
      .readCases(fileName)
      .zipWithIndex
      .map {
        case (singleCase, index) => {
          val (numColours, customers) = InputParser.parse(singleCase)
          val minimalBatch = PaintBatchGenerator.generateInitialBatch(customers, numColours)
          PaintBatchGenerator.extractNonIsolatedMatteRequests(customers)
          s"Case #${index + 1}: ${formatResult(RequirementChecker.checkAllRequirements(minimalBatch, customers), minimalBatch)}"
        }
      }
  }

  def formatResult(result :Boolean, paintBatch: Batch) = result match
  {
    case true => paintBatch.toBinaryRepresentation
    case false => "IMPOSSIBLE"
  }

      override def main(args: Array[String]): Unit = {
    PaintShopApp.run(args.head).foreach(println(_))
  }
}
