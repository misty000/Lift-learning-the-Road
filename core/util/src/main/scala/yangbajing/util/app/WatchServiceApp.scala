package yangbajing.util.app

import java.nio.file.StandardWatchEventKinds._
import java.nio.file.Paths
import java.nio.file.FileSystems
import java.util.concurrent.TimeUnit
import scala.collection.JavaConverters._
import java.nio.file.WatchEvent
import java.nio.file.Path

object WatchServiceApp {

  def main(args: Array[String]) {
    val path = Paths.get("/data/Temp")
    val watchService = FileSystems.getDefault().newWatchService()

    path.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY)

    @volatile var tag = true

    while (tag) {
      val key = watchService.take()
      for (watchEvent <- key.pollEvents().asScala if watchEvent.kind() != OVERFLOW) {
        val watchEventPath = watchEvent.asInstanceOf[WatchEvent[Path]]
        println(watchEvent.kind() + " -> " + watchEventPath.context())
        println(watchEventPath.context())
      }

      tag = key.reset()
    }

    watchService.close()
  }
}