resolvers += Classpaths.typesafeResolver

// Jetty 支持
libraryDependencies += "com.github.siasia" %% "xsbt-web-plugin" % "0.12.0-0.2.11.1"

// Emacs ensime plugin
addSbtPlugin("org.ensime" % "ensime-sbt-cmd" % "0.1.1")

// Eclipse plugin
addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.1.1")

// Idea plugin
addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.2.0")

