package controllers
import org.scalatestplus.play._
import play.api.mvc._
import play.api.test._
import play.api.test.Helpers._
import play.twirl.api.Html

//import scala.collection.mutable
import scala.concurrent.Future

class MyControllerSpec extends PlaySpec with Results {

  "types of testing" should {
    // Testing a controller
    "should be valid" in {
      val controller = new MyController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.index().apply(FakeRequest())
      val bodyText: String = contentAsString(result)
      bodyText must include("<body>")
    }

    //    "Testing a template"
    "render index template" in new App {
      val html = views.html.main("Title")(Html("<p>I AM SOME HTML</p>"))
      contentAsString(html) must include("Hello Coco")
    }

  }

}
