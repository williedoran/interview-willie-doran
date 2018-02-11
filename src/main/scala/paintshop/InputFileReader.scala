package paintshop

import scala.collection.mutable.ListBuffer
import scala.io.Source

object InputFileReader {
  def readCases(fileName: String): List[List[String]] = {
    val lines = Source.fromURL(getClass.getResource(fileName)).getLines()
    val numOfCases = lines.next().toInt
    val cases = ListBuffer[List[String]]()
    while(lines.hasNext) {
      val numColours = lines.next()
      val numOfCustomers = lines.next()
      val customers = lines.take(numOfCustomers.toInt).toList
      cases.append(numColours :: numOfCustomers :: customers)
    }
    require(numOfCases == cases.size, s"amount of cases: ${cases.size}  not what is expected $numOfCases")
    cases.toList
  }

}
