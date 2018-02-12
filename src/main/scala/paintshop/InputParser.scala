package paintshop

object InputParser {


  def parse(singleCaseInputLines: Seq[String]) : (Int, Seq[Customer]) = {
    val numColours :: numCustomers :: customers = singleCaseInputLines
    require(numCustomers.toInt == customers.size, s"${customers.size} number of customers should be ${numCustomers}")
    (numColours.toInt, customers.map(extractCustomerRequirements))
  }

  def extractCustomerRequirements(customer: String) : Customer = {
    val numPaints :: paintPairString = customer.split(" ").toList
    require(numPaints.toInt == paintPairString.size / 2, "expected number of customer paints is inconsistent with actual")
    convertPaintStringToCustomerRequirement(paintPairString)
  }

  def convertPaintStringToCustomerRequirement(paints: Seq[String]) : Customer ={
    val (mattes, glosses) = paints
        .grouped(2)
        .collect{
          case List(matteIndex, "1") => ("1", matteIndex.toInt)
          case List(glossIndex, "0") => ("0", glossIndex.toInt)
        }
        .partition(x => x._1 == "1")

    val matte = mattes.map(_._2).toList match {
      case Nil => None
      case x :: Nil => Some(x)
      case _ => throw new RuntimeException("There should only be max one matte per customer")
    }
    Customer(matteId = matte ,glossIds = glosses.map(_._2).toList)

  }

}
