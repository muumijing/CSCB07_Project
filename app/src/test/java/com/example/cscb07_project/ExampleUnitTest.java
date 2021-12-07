package com.example.cscb07_project;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {
    @Mock
    MVP_OwnerLogin_Activity view1;

    @Mock
    Model model;

    @Captor
    private ArgumentCaptor<Customer> captor;

    @Test
    public void testPresenter1(){
        // Test log in as owner and failed

        String email = "muumi@mail.com";
        String password = "A123456789123";

        Owner owner = null;

        Presenter presenter = new Presenter(model, view1);

        presenter.ownerLogin(email, password);



        verify(view1, times(0)).loginFailure();
    }
}