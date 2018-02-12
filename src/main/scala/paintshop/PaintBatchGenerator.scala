package paintshop

import scala.collection.immutable.BitSet
import scala.collection.mutable

object PaintBatchGenerator {
  def generateInitialBatch(customerRequirements: Seq[Customer], numColours : Int) : Batch = {
     val matteOnlyRequirements: Seq[PaintId] = extractMatteOnlyRequirements(customerRequirements)
       .map(_.matteId.get)
     val initialBatch = Batch(BitSet(matteOnlyRequirements: _*), numColours)

     val matteInAdditionToGloss = extractMatteWithGlossRequests(customerRequirements)
     addMatteRequirementIfNoGlossAvailable(matteInAdditionToGloss, initialBatch)
  }

  def extractMatteOnlyRequirements(customerRequirements: Seq[Customer]) = {
    customerRequirements
      .filter(c => c.matteId.isDefined && c.glossIds.isEmpty)
  }

  def extractMatteWithGlossRequests(customerRequirements: Seq[Customer]) = {
    customerRequirements
      .filter(c => c.matteId.isDefined && c.glossIds.nonEmpty)
  }

  def addMatteRequirementIfNoGlossAvailable(requirements: Seq[Customer], currentBatch: Batch) : Batch = {
    val bitMap = mutable.BitSet(currentBatch.matte.toSeq:_*)
     requirements.foreach{ requirement => requirement match {
         case Customer(Some(matteId), glossIds) => currentBatch.checkIfAtLeastOneGlossSatisfied(glossIds) match {
           case false => bitMap += matteId
           case true =>
         }
       }
     }
     Batch(bitMap.toSet, currentBatch.numColours)
  }

}
