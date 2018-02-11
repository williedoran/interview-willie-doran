package object paintshop {
  type PaintId = Int
trait CustomerRequirement

  case class Matte(val paintNumber: PaintId) extends CustomerRequirement
  case class Glossy(val paintNumber: PaintId) extends CustomerRequirement

}
