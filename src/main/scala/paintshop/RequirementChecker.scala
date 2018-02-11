package paintshop

import scala.collection.immutable.BitSet

object RequirementChecker {
  def checkRequirementsHold(batch: Batch, customerRequirements: Customer) : Boolean = {

    customerRequirements match {
      case Customer(Some(matteId), _) => (batch.matte & BitSet(matteId)) == BitSet(matteId)
      case Customer(None, glossIds) => (batch.gloss & BitSet(glossIds:_*)) != BitSet.empty
    }

  }
}
