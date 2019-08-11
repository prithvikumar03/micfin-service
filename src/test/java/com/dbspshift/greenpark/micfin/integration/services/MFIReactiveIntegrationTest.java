package com.dbspshift.greenpark.micfin.integration.services;

import com.dbspshift.greenpark.micfin.beans.MFI;
import com.dbspshift.greenpark.micfin.integration.repository.MFIRepository;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@AutoConfigureWebTestClient
@Disabled
public class MFIReactiveIntegrationTest {

    @Autowired
    public WebTestClient webTestClient;

    @Autowired
    MFIRepository mfiRepository;

    @Test
    public void testRegisterMFI() {
        MFI mfi = new MFI("DBS", "Digital Bank of Singapore");
        webTestClient.post().uri("/mfi")
                //.contentType(MediaType.JSON_UTF_8)
                .body(Mono.just(mfi), MFI.class)
                .exchange()
                .expectStatus().isOk()
                //.expectHeader().contentType(MediaType.JSON_UTF_8)
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.name").isEqualTo("DBS")
                .jsonPath("$.fullName").isEqualTo("Digital Bank of Singapore");
    }
}
