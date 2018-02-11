package paintshop

import scala.collection.mutable

object PaintBatchGenerator {
  def generate(customerRequirements: Seq[Customer], numColours : Int) : Batch = {
     val bitVector = mutable.BitSet()
     customerRequirements.flatMap(_.matteId).flatMap(matteId => bitVector += matteId)
     Batch(bitVector.toSet, numColours)
  }

}
