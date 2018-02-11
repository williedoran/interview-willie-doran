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


  def parse(singleCaseInputLines: Seq[String]) : (Int, Int, Seq[Seq[CustomerRequirement]]) = {
    //validate string
    val numColours :: numCustomers :: customers = singleCaseInputLines
    (numColours.toInt, numCustomers.toInt, customers.map(extractCustomerRequirements))
  }

  def extractCustomerRequirements(customer: String) : Seq[CustomerRequirement]= {
    //assert constraints that only one matte and that num colors etc are all match

    val numPaints :: tail = customer.split(" ").toList
    tail.grouped(2).collect{
      case List(a, "1") => Matte(a.toInt)
      case List(a, "0") => Glossy(a.toInt)
    }.toSeq
  }

}
