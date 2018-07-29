package by.prostrmk.test.servlet;


import by.prostrmk.controller.MainController;
import org.junit.Before;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.mockito.Mockito.*;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class MainServletTest {

    private MainController servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private StringWriter writer;
    private Map<String, String> parameters;

    @Before
    public void setUp() throws Exception{
        parameters = new HashMap<>();
        servlet = new MainController();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        writer = new StringWriter();
        when(request.getParameter(anyString())).thenAnswer((Answer<String>) invocationOnMock -> parameters.get(invocationOnMock.getArguments()[0]));
        when(response.getWriter()).thenReturn(new PrintWriter(writer));
    }



}
