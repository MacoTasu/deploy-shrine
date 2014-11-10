package models

import play.api.db.slick.Config.driver.simple._
import play.api.libs.json._

case class Revelation(id: Option[Int], rokuyou: Short, word: String)

class Revelations(tag: Tag) extends Table[Revelation](tag,"revelation") {
  def id      = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def rokuyou = column[Short]("rokuyou",O.NotNull)
  def word    = column[String]("word",O.NotNull)

  def * = (id.?,rokuyou,word) <> ((Revelation.apply _).tupled, Revelation.unapply _)
}

object Revelations extends DAO {
  implicit val RevelationReads  = Json.reads[Revelation]
  implicit val RevelationWrites = Json.writes[Revelation]

  def findById(id: Int)(implicit s: Session): Option[Revelation] = {
    Revelations.filter(_.id === id).firstOption
  }

  def insert(revelation: Revelation)(implicit s: Session) {
    Revelations += revelation
  }
}
