package object paintshop {

  type PaintId = Int

trait PaintRequirement {
  val paintNumber: PaintId
}

  case class Matte(val paintNumber: PaintId) extends PaintRequirement
  case class Glossy(val paintNumber: PaintId) extends PaintRequirement
  case class Customer(val matteId: Option[PaintId] = None, val glossIds: Seq[PaintId] = Seq.empty)

  case class Batch(val matteIds: Set[PaintId], val numColours : Int){
    def toBinaryRepresentation() : String = {
      (1 to numColours).map(x => if (matteIds.contains(x)) "1" else "0").mkString(" ")
    }
  }


}
