package controllers

import play.api._
import play.api.mvc._
import models._
import play.api.libs.json._
import play.api.db.slick._
import java.util.Date
import play.api.libs.ws._
import play.api.Play.current
import play.api.libs.ws.WS.url
import play.api.libs.ws.Response
import scala.concurrent._
import scala.concurrent.Await
import scala.concurrent.duration._
import ExecutionContext.Implicits.global

object Application extends Controller {
  import Revelations.RevelationReads
  import Revelations.RevelationWrites

  def pray = DBAction { implicit request =>
      // http://kayosystem.blogspot.jp/2010/07/web.html
      // こちらのAPIを利用しています
      val date = "%tY/%<tm/%<td" format new Date
      val future: Future[WS.Response] = {
        WS.url("http://jqreki.appspot.com/qreki/" + date).get()
      }
      val response     = Await.result(future, Duration.Inf).body
      val json         = Json.parse(response)
      val rokuyou_text = (json \ "rokuyo").as[String]
      Ok(views.html.json(Json.stringify(Json.toJson(Revelations.findById(1)))))
  }
}
