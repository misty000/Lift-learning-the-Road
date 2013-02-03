# 附录 A. 使用Sbt

使用Scala编程，sbt是应该学并使用的。就像Java的Maven一样。但它远比Maven强大、灵活及好用！


# 安装sbt

下载 [sbt-launch.jar][sbt-launch.jar] 并放到 ~/bin 目录中，创建sbt12启动文件。
在sbt文件内添加如下内容：

    java -Xms512M -Xmx1024M -Xss1M -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=384M -Dfile.encoding=UTF-8 -jar `dirname $0`/sbt-launch.jar "$@"

为新添加的脚本添加可执行权限：

    $ chmod +x ~/bin/sbt12


Sbt奉行“约定优于配置”原则且沿用了Maven的目录结构。

    ├── src
    │   ├── main
    │   │   ├── java
    │   │   ├── resources
    │   │   ├── scala
    │   └── test
    │       ├── java
    │       ├── resources
    │       └── scala


# 建立项目

现在，sbt就已经装好了。接下来我们开始建立第一个sbt项目（完整的项目代码在book/examples/sbt-project）。
首先来看看整个项目组织结构：

    ├── project
    │   └── Build.scala
    └── src
        └── main
            └── scala
                └── learn
                    └── HelloWorld.scala
    
    5 directories, 2 files

如上目录，在 `project/Build.scala` 中进行项目属性配置。如：`scala` 版本，依赖库，文件结构等。而 `src` 目
录刚是标准的MAVEN式目录结构，在此就不铸更多说明。让我们来简单分析下 `project/Build.scala` 的内容：

      lazy val root = Project(
        id = "sbt-project",
        base = file("."),
        settings = Project.defaultSettings ++ basicSettings)

这就是我们需要在Build.scala里面写的项目配置内容了。base 告诉sbt，这个项目在哪个目录。这里配置的是当前
目录，就是包含project目录的目录。而 `settings` 可以对sbt项目的属性进行定制。

sbt的更深入讲解请参考 [使用SBT构建Scala应用](https://github.com/fujohnwang/real_world_scala/blob/master/02_sbt.markdown)

运行 `HelloWorld` 是在sbt console中，使用如下命令：

    $ sbt
    > run
    [info] Running learn.HelloWorld 
    您好，羊八井！
    [success] Total time: 0 s, completed 2013-2-2 20:55:18


# 添加Web支持（使用Jetty）

上一节，我们已经可以使用sbt开发scala应用了。但是很多时候我们需要写Web程序，这就需要sbt支持web容器。现在
我们为项目添加Web支持，使用Jetty做为Web容器（完整的代码在 `book/examples/web-project` ）。

**添加 `xsbt-web-plugin` 插件**

编写 `project/plugins.sbt` 文件，添加如下配置：

    libraryDependencies += "com.github.siasia" %% "xsbt-web-plugin" % "0.12.0-0.2.11.1"

再修改 `project/Build.scala` 文件，添加Web支持。  

    .settings(WebPlugin.webSettings: _*) // 这里设置WebPlugin
    .settings(PluginKeys.port in WebPlugin.container.Configuration := 8081)

我们可以在 `Project(....)` 的配置后使用 `.settings(....)` 方法继续对sbt进行定制。在这里，首先导入
xsbt-web-plugin，再设置Jetty容器绑定的端口为8081。

现在，我们就可以在sbt console中使用 `container:start` 命令来启动jetty了，并可以在
 `http://localhost:8081/HelloWorld` 中看到Servlet的输出（代码请看示例，在此略）。

    > container:start

# 建立子项目的Sbt

之前介绍了单项目的sbt配置方法，但是在我们实际的开发过程中。把所有代码和模块都放在一个项目里是黑不科
学的！我们需要把不同模块或功能的代码放到不同的项目里，sbt对此提供了完美的支持。首先让我们来看一下一
个子项目的sbt会是什么样子：

    ├── core
    │   ├── common
    │   │   ├── src
    │   │   │   ├── main
    │   │   │   └── test
    │   ├── persistence
    │   │   ├── src
    │   │   │   ├── main
    │   │   │   └── test
    │   └── util
    │       ├── src
    │       │   ├── main
    │       │   └── test
    ├── app
    │   └── image-share
    │       ├── src
    │       │   ├── main
    │       │   └── test
    │       │   └── webapp

在这里，可以看到整个工程也很清晰、干净的方式呈现出来。 `core` 目录下放在是所有项目共用的模块（一个模块
为一个项目）。而在 `app` 目录下，放着的是实际需要打包为 `war` 的代码。

    lazy val root = Project("root", file("."))
      .aggregate(
           learnLiftBlank,
           appImageShare,
           yangbajingCommon, yangbajingUtil, yangbajingPersistence)
    
    lazy val appImageShare = webProject("app_image-share", file("app/image-share"), 48101)
      .dependsOn(yangbajingPersistence, yangbajingUtil, yangbajingCommon)
      .settings(
        name := "app_image-share",
        description := "羊八井图片分享网")
    
    private def webProject(id: String, base: File, iPort: Int): Project = {
      import com.github.siasia.{ WebPlugin, PluginKeys }
      yangbajingProject(id, base)
        .settings(WebPlugin.webSettings: _*)
        .settings(
	        PluginKeys.port in WebPlugin.container.Configuration := iPort,
        	libraryDependencies ++= (
            dependentRange(_servlet30, "container,test") ++ 
            dependentRange(_jetty, "container,test")))
    }
    
    private def yangbajingProject(id: String, base: File): Project =
        Project(id, base)
        .settings(basicSettings: _*)

上面是一新配置片段（注：详细配置请看 `project/Build.scala` ）。

在root项目的配置中，使用aggregate方法来聚集多个子项目。而我们可以看到在appImageShare项目的配置中设置
 `file` 参数时指定了 `learn/lift_blank` 目录，这样就可以指定子项目的目录位置了。


# 添加JRebel支持

可以到[这里](https://my.jrebel.com/plans/)申请免费的scala使用授权。在这里，修改 `~/bin/sbt12` 内容为：

    #!/bin/sh
    java -Xms1024M -Xmx1536M -Xss1M -XX:MaxPermSize=384M -Dfile.encoding=UTF-8 \
    -XX:+CMSClassUnloadingEnabled -javaagent:/data/local/ZeroTurnaround/jrebel/jrebel.jar \
    -noverify -Drebel.lift_plugin=true -jar /data/java/lib/sbt/sbt-launch-0.12.2.jar "$@"

并为sbt添加 `PluginKeys.scanDirectories in Compile := Nil` 配置（注：配置内容在 `project/BuildSettings.scala` ）。

现在，你可以在sbt console中输入 `~ compile` 来享受修改代码而不需要重启的幸福了，您幸福吗？

