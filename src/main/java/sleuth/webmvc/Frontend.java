package sleuth.webmvc;

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
    return "Current Trace/Span: " + tracer.currentSpan().context().toString() + "<br/><br/> New Trace/Span: " + tracer.newTrace().context().toString();

  }

  public static void main(String[] args) {
    SpringApplication.run(Frontend.class,
        "--spring.application.name=frontend",
        "--server.port=8080"
    );
  }

}
