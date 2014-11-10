package controllers

import play.api._
import play.api.mvc._
import models._
import play.api.libs.json._
import play.api.db.slick._

object Application extends Controller {
  import Revelations.RevelationReads
  import Revelations.RevelationWrites

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def pray = DBAction { implicit request =>
      Ok(views.html.json(Json.stringify(Json.toJson(Revelations.findById(1)))))
  }
}
