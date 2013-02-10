package bootstrap.liftweb

import net.liftweb.common.Full
import net.liftweb.http._
import net.liftweb.util.Helpers._
import net.liftweb.sitemap._
import net.liftmodules.JQueryModule


/**
 * 这就是你配置lift环境的地方，将在每次应用启动时执行
 */
class Boot {
  def boot {
    // 在哪个包下面搜索 snippet, comet, view
    LiftRules.addToPackages("code")

    // 构建 SiteMap
    val entries = List(
      Menu.i("Home") / "index", // 使用简单的方式来声明一个菜单

      // 复杂的方式，使 /static/index 静态路径是可见的
      Menu(Loc("Static", Loc.Link(List("static"), true, "/static/index"),
        "Static Content")))

    // 设置 sitemap
    // 注意：若你不想启用对每个页面的访问控制，请注释这行代码
    LiftRules.setSiteMap(SiteMap(entries: _*))

    // Ajax调用开始时显示的图片
    LiftRules.ajaxStart =
      Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)

    // Ajax调用结束时显示的图片
    LiftRules.ajaxEnd =
      Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    // 设置每个请求字符集为 UTF-8
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

    // 使用 HTML5 的标签形式进行页面渲染
    LiftRules.htmlProperties.default.set((r: Req) =>
      new Html5Properties(r.userAgent))

    // 初始化 jQuery 模块, 从 http://liftweb.net/jquery 了解更多信息
    JQueryModule.InitParam.JQuery = JQueryModule.JQuery182
    JQueryModule.init()

  }
}
