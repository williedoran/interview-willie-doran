package paintshop

import scala.collection.immutable.BitSet

object RequirementChecker {
  def checkIndividualCustomerRequirements(batch: Batch, customerRequirement: Customer) : Boolean = {

    def checkIfMatteRequestSatisfied(matteId: PaintId) = {
      (batch.matte & BitSet(matteId)) == BitSet(matteId)
    }

    def checkIfAtLeastOneGlossSatisfied(glossIds: Seq[PaintId]) = {
      (batch.gloss & BitSet(glossIds: _*)) != BitSet.empty
    }

    customerRequirement match {
      case Customer(Some(matteId), _) => checkIfMatteRequestSatisfied(matteId)
      case Customer(None, glossIds) => checkIfAtLeastOneGlossSatisfied(glossIds)
    }

  }
  def checkAllRequirements(batch: Batch, requirements: Seq[Customer]) : String = {
    ""
  }

}
