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

  def setup = DBAction { implicit request =>
    Revelations.insert(Revelation(Option.apply(1),1,Option.apply("大安"),"今日は大安です。思い切ってdeployしちゃいましょう。"))
    Revelations.insert(Revelation(Option.apply(2),2,Option.apply("赤口"),"今日は赤口です。実は仏滅よりもやばいです。deployしたらあかん..."))
    Revelations.insert(Revelation(Option.apply(3),3,Option.apply("先勝"),"今日は先勝です。deployは午前中に済ませましょう。"))
    Revelations.insert(Revelation(Option.apply(4),4,Option.apply("友引"),"今日は友引です。昼のdeployはさけましょう。するなら朝晩が吉です。"))
    Revelations.insert(Revelation(Option.apply(5),5,Option.apply("先負"),"今日は先負です。deployは午後からが吉でしょう。"))
    Revelations.insert(Revelation(Option.apply(6),6,Option.apply("仏滅"),"今日は仏滅です。仏滅deployとか事故のもとですよ。"))

    Ok("setup finished")
  }

  def pray = DBAction { implicit request =>
      // http://kayosystem.blogspot.jp/2010/07/web.html
      // こちらのAPIを利用しています
      val date = "%tY/%<tm/%<td" format new Date
      val future: Future[play.api.libs.ws.Response] = {
        WS.url("http://jqreki.appspot.com/qreki/" + date).get()
      }
      val response     = Await.result(future, Duration.Inf).body
      val json         = Json.parse(response)
      val rokuyou_text = (json \ "rokuyo").as[String]
      Ok(views.html.json(Json.stringify(Json.toJson(Revelations.findById(1)))))
  }
}
