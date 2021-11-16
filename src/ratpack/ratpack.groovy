import static ratpack.groovy.Groovy.ratpack
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import org.slf4j.Logger
import org.slf4j.LoggerFactory

Logger logger = LoggerFactory.getLogger(this.class)

ratpack {
	handlers {
		get {
			render "Hello World!"
		}
		get("json") {
			render new File(Thread.currentThread().getContextClassLoader().getResource("some.json").toURI()).text
		}
		get("csv/:name") {
			response.contentType( "text/csv" )

			String basePath = System.getProperty("ratpackpull_csv_path")
			Path path = Paths.get(basePath + java.io.File.separator + pathTokens.get("name"))

			if(! Files.exists(path)){
				logger.error "CSV source file not found at path: " + path
			}

			render(path)
		}
		get(":name") {
			render "Hello $pathTokens.name!"
		}
	}
}
