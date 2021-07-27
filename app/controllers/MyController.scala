package controllers

import model.PostExample

import javax.inject._
import play.api.mvc._
import play.api.i18n.I18nSupport

import scala.concurrent.Future

@Singleton
class MyController @Inject() (val cc: ControllerComponents) extends AbstractController(cc) with I18nSupport {
  def index: Action[AnyContent] = Action { implicit request =>
    Ok(views.html.index())
  }

  def examplePost: Action[PostExample] = Action.async(bodyParser = parse.json[PostExample]) { implicit request =>
    val body: String = request.body.toString
    Future.successful(Ok(s"You posted: [ $body ]"))
  }
}
