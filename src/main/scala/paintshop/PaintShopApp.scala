package paintshop

object PaintShopApp extends App {
  def run(fileName: String): List[String] = {

    InputFileReader
      .readCases(fileName)
      .zipWithIndex
      .map {
        case (singleCase, index) => {
          val (numColours, customers) = InputParser.parse(singleCase)
          val paintBatch = PaintBatchGenerator.generateInitialBatch(customers, numColours)
          s"Case #${index + 1}: ${RequirementChecker.checkAllRequirements(paintBatch, customers)}"
        }
      }
  }
  override def main(args: Array[String]): Unit = {
    PaintShopApp.run(args.head).foreach(println(_))
  }
}
