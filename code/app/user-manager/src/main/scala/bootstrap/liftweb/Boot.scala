package bootstrap.liftweb

import net.liftweb._
import util._
import Helpers._

import common._
import http._
import sitemap._
import Loc._
import net.liftmodules.JQueryModule
import net.liftweb.http.js.jquery._

class Boot {
  def boot {
    LiftRules.addToPackages("code")

    // Build SiteMap
    val entries = List(
      Menu.i("Home") / "index", // the simple way to declare a menu

      Menu(Loc("Static", Link(List("static"), true, "/static/index"),
        "Static Content")))

    LiftRules.setSiteMap(SiteMap(entries: _*))

    LiftRules.ajaxStart =
      Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)

    LiftRules.ajaxEnd =
      Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

    LiftRules.htmlProperties.default.set((r: Req) =>
      new Html5Properties(r.userAgent))

    LiftRules.jsArtifacts = JQueryArtifacts
    JQueryModule.InitParam.JQuery = JQueryModule.JQuery182
    JQueryModule.init()

  }

}
