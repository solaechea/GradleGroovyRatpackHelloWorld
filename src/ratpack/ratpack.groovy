import static ratpack.groovy.Groovy.ratpack
import java.nio.file.Paths

ratpack {
	handlers {
		get {
			render "Hello World!"
		}
		get("json") {
			render new File(Thread.currentThread().getContextClassLoader().getResource("some.json").toURI()).text
		}
		get(":name") {
			render "Hello $pathTokens.name!"
		}
	}
}
