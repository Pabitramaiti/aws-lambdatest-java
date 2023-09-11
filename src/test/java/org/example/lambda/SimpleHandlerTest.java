package org.example.lambda;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.Context;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class SimpleHandlerTest {

    private SimpleHandler simpleHandler;

    @Mock
    Context context;
    @Mock
    LambdaLogger logger;

    @BeforeEach
    public void setup(){
        when(context.getLogger()).thenReturn(logger);

        doAnswer(call -> {System.out.println((String)call.getArgument(0));
        return null;
        }).when(logger).log(anyString());

        simpleHandler = new SimpleHandler();
    }
    @Test
    public void shouldReturnUppercaseOfInput(){
        //var sut = new SimpleHandler();
        //Assertion.assertEquals("HELLO, WORLD!", sut.handleRequest("hello, world!",@NotNull));
        when(context.getFunctionName()).thenReturn("handleRequest");
        Assertions.assertEquals("HELLO, WORLD!", simpleHandler.handleRequest("hello, world!",context));
    }
}