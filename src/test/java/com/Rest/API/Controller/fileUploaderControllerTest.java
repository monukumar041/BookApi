package com.Rest.API.Controller;

import com.Rest.API.helper.FileUploaderHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class fileUploaderControllerTest {


    @Mock
    private FileUploaderHelper fileUploaderHelper;
    private fileUploaderController fUploaderController;

    @BeforeEach
    void setUp() {
        this.fUploaderController=new fileUploaderController(this.fileUploaderHelper);
    }


}