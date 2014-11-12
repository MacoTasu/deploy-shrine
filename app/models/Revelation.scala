package models

import play.api.db.slick.Config.driver.simple._
import play.api.libs.json._

case class Revelation(id: Option[Int], rokuyou: Short, name: Option[String], explanation: String)

class Revelations(tag: Tag) extends Table[Revelation](tag,"revelation") {
  def id          = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def rokuyou     = column[Short]("rokuyou",O.NotNull)
  def name        = column[String]("name",O.NotNull)
  def explanation = column[String]("explanation",O.NotNull)

  def * = (id.?,rokuyou,name.?,explanation) <> ((Revelation.apply _).tupled, Revelation.unapply _)
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

  def findRandomByRokuyou(rokuyou: Short)(implicit s: Session): Option[Revelation] = {
    Revelations.filter(_.rokuyou === rokuyou).firstOption
  }

  def rokuyouType(rokuyou: String): Int = {
    if (rokuyou == "大安") {
      return 1;
    } else if (rokuyou == "赤口") {
      return 2;
    } else if (rokuyou == "先勝") {
      return 3;
    } else if (rokuyou == "友引") {
      return 4;
    } else if (rokuyou == "先負") {
      return 5;
    } else if (rokuyou == "仏滅") {
      return 6;
    } else {
      return 0;
    }
  }

  def rokuyouText(number: Int): String = {
    if (number == 1) {
      return "大安";
    } else if (number == 2) {
      return "赤口";
    } else if (number == 3) {
      return "先勝";
    } else if (number == 4) {
      return "友引";
    } else if (number == 5) {
      return "先負";
    } else if (number == 6) {
      return "仏滅";
    } else {
      return "We--i";
    }
  }
}
