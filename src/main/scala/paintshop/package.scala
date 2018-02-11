package object paintshop {

  type PaintId = Int

trait PaintRequirement {
  val paintNumber: PaintId
}

  case class Matte(val paintNumber: PaintId) extends PaintRequirement
  case class Glossy(val paintNumber: PaintId) extends PaintRequirement
  case class Customer(val matteId: Option[PaintId] = None, val glossIds: Seq[PaintId] = Seq.empty)


}
