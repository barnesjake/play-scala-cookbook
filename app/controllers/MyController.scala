package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.i18n.I18nSupport

@Singleton
class MyController @Inject()(val cc: ControllerComponents) extends AbstractController(cc) with I18nSupport {
  def index: Action[AnyContent] = Action { implicit request =>
    Ok(views.html.index())
  }
}