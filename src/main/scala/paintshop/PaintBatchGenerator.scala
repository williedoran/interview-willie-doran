package paintshop

import scala.collection.immutable.BitSet
import scala.collection.mutable

object PaintBatchGenerator {
  def generateInitialBatch(customerRequirements: Seq[Customer], numColours : Int) : Batch = {
     val singleMatteRequirements: Seq[PaintId] = customerRequirements
       .filter(c => c.matteId.isDefined && c.glossIds.isEmpty)
       .map(_.matteId.get)

     val matteBits = BitSet(singleMatteRequirements:_*)
     Batch(matteBits, numColours)
  }

}
