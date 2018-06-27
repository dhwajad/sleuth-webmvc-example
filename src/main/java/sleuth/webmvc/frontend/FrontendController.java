package sleuth.webmvc.frontend;

import brave.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@CrossOrigin // So that javascript can be hosted elsewhere
public class FrontendController {

    BackendRepository backendRepository;

    Tracer tracer;

    public FrontendController(BackendRepository backendRepository, Tracer tracer) {
        this.backendRepository = backendRepository;
        this.tracer = tracer;
    }

    @RequestMapping("/")
    public String callBackend() { //throws ExecutionException, InterruptedException {
        Future<String> result1 = backendRepository.callingBackendAsync();
        Future<String> result2 = backendRepository.callingBackendAsync();
        Future<String> result3 = backendRepository.callingBackendAsync();
        //return result1.get() + "<br />" + result2.get() + "<br />" + result3.get();
        return "Current Trace/Span: " + tracer.currentSpan().context().toString() + "<br/><br/> New Trace/Span: " + tracer.newTrace().context().toString();

    }

}
