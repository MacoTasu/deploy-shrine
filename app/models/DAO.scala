package models

import scala.slick.lifted.TableQuery

private[models] trait DAO {
  val Revelations = TableQuery[Revelations]
}
