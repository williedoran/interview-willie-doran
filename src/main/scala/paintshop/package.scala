import scala.collection.immutable.BitSet

package object paintshop {

  type PaintId = Int

trait PaintRequirement {
  val paintNumber: PaintId
}

  case class Matte(val paintNumber: PaintId) extends PaintRequirement
  case class Glossy(val paintNumber: PaintId) extends PaintRequirement
  case class Customer(val matteId: Option[PaintId] = None, val glossIds: Seq[PaintId] = Seq.empty){
    require(matteId.isDefined || glossIds.nonEmpty, "you must have at least one colour")
  }

  case class Batch(val matteIds: Set[PaintId], val numColours : Int){
    require(matteIds.max <= numColours)

    val matte = BitSet(matteIds.toList:_*)
    val gloss = invert(matte)

    lazy val toBinaryRepresentation : String = {
      (1 to numColours).map(x => if (matteIds.contains(x)) "1" else "0").mkString(" ")
    }

    private def invert(bitSet: BitSet) : BitSet = {
      BitSet(1 to numColours:_*) -- bitSet
    }

  }


}
