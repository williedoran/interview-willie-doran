package object paintshop {

  type PaintId = Int

trait PaintRequirement {
  val paintNumber: PaintId
}

  case class Matte(val paintNumber: PaintId) extends PaintRequirement
  case class Glossy(val paintNumber: PaintId) extends PaintRequirement
  case class Customer(val matteIds: Seq[PaintId] = Seq.empty, val glossIds: Seq[PaintId] = Seq.empty)


}
