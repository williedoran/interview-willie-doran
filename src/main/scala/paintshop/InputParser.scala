package paintshop

object InputParser {
  /*
      val singleCaseInputLines =
      """
        |5
        |3
        |1 1 1
        |2 1 0 2 0
        |1 5 0
      """.stripMargin.split("\n").toList
   */


  def parse(singleCaseInputLines: List[String]) : (Int, Int, List[String]) = {
    //validate string
    val numColours :: numCustomers :: customers = singleCaseInputLines
    (numColours.toInt, numCustomers.toInt, customers.map(cust => cust.tail.trim))
  }

}
