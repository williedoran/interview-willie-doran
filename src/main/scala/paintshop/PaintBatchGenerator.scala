package paintshop

import scala.collection.immutable.BitSet

object PaintBatchGenerator {
  def generateInitialBatch(customerRequirements: Seq[Customer], numColours : Int) : Batch = {
     val singleMatteRequirements: Seq[PaintId] = extractIsolatedMatteRequirements(customerRequirements)
       .map(_.matteId.get)

     val matteBits = BitSet(singleMatteRequirements:_*)
     Batch(matteBits, numColours)
  }

  def extractIsolatedMatteRequirements(customerRequirements: Seq[Customer]) = {
    customerRequirements
      .filter(c => c.matteId.isDefined && c.glossIds.isEmpty)
  }

  def extractNonIsolatedMatteRequests(customerRequirements: Seq[Customer]) = {
    customerRequirements
      .filter(c => c.matteId.isDefined && c.glossIds.nonEmpty)
  }

}
