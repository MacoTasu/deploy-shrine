package controllers

import play.api._
import play.api.mvc._
import models._
import com.google.gson._
import play.api.db.slick._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  val gson:Gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create // 日付のフォーマットをしておく
  def pray = DBAction { implicit request =>
      Ok(views.html.json(gson.toJson(Revelations.findById(1))))
  }
}
