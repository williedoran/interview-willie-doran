package paintshop

object PaintShop {
  def run(fileName: String): List[String] = {
    val (numCases, cases) = InputFileReader.readCases(fileName)
    cases.zipWithIndex.map{
      case (singleCase, index) => {
        val (numColours, numCustomers, customers) = InputParser.parse(singleCase)
        val paintBatch = PaintBatchGenerator.generate(customers, numColours)
        val result = customers.forall(RequirementChecker.checkIndividualCustomerRequirements(paintBatch, _)) match {
          case true => paintBatch.toBinaryRepresentation
          case false => "IMPOSSIBLE"
        }
        s"Case #${index+1}: $result"
      }

    }
  }

}
