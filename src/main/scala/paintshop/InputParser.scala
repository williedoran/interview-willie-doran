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


  def parse(singleCaseInputLines: Seq[String]) : (Int, Int, Seq[Customer]) = {
    //validate string
    val numColours :: numCustomers :: customers = singleCaseInputLines
    (numColours.toInt, numCustomers.toInt, customers.map(extractCustomerRequirements))
  }

  def extractCustomerRequirements(customer: String) : Customer = {
    //assert constraints that only one matte and that num colors etc are all match
    val numPaints :: tail = customer.split(" ").toList
    convertPaintStringToCustomerList(tail)
  }

  def convertPaintStringToCustomerList(paints: Seq[String]) : Customer ={
    val paintRequirements = paints.grouped(2).collect{
      case List(a, "1") => Matte(a.toInt)
      case List(a, "0") => Glossy(a.toInt)
    }.toList
    //should probably just do a group by key here

    val (mattes, glosses) = paintRequirements.partition(req => req match {
      case Matte(_) => true
      case _ => false
    })
    val matteId = mattes match {
      case Nil => None
      case x :: Nil => Some(x.paintNumber)
    }
    Customer(matteId = matteId ,glossIds = glosses.map(_.paintNumber))

  }

}
