import play.api.Application
import play.api.mvc.WithFilters
import play.modules.statsd.api.{Statsd, StatsdFilter}

object Global extends WithFilters(new StatsdFilter) {
  override def onStart(app: Application) = {
    Statsd.init(app.configuration)
    super.onStart(app)
  }
}
