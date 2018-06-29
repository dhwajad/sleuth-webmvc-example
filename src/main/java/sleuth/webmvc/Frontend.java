package sleuth.webmvc;

import brave.Span;
import brave.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

@SpringBootApplication
@RestController
@CrossOrigin
public class Frontend {

  @Autowired
  Tracer tracer;

  @RequestMapping("/")
  public String printTraceIds() {
      Span currentSpan = tracer.currentSpan();
      Span newTrace = tracer.newTrace();
    return "Current Trace/Span/ParentSpan/Sampled: "
            + currentSpan.context().toString()
            + "/"
            + currentSpan.context().parentId()
            + "/"
            + currentSpan.context().sampled()
            + "<br/><br/> New Trace/Span/ParentSpan/Sampled: "
            + newTrace.context().toString()
            + "/"
            + newTrace.context().parentId()
            + "/"
            + newTrace.context().sampled();

  }

  public static void main(String[] args) {
    SpringApplication.run(Frontend.class,
        "--spring.application.name=frontend",
        "--server.port=8080"
    );
  }

}
