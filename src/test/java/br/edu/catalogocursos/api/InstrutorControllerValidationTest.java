package br.edu.catalogocursos.api;

import br.edu.catalogocursos.api.controller.InstrutorController;
import br.edu.catalogocursos.api.exception.ApiExceptionHandler;
import jakarta.validation.Validation;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class InstrutorControllerValidationTest {

    @Test
    void cadastroHttpDeInstrutorInvalidoRetorna400() throws Exception {
        var validator = new SpringValidatorAdapter(
                Validation.buildDefaultValidatorFactory().getValidator());

        MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(new InstrutorController(null))
                .setControllerAdvice(new ApiExceptionHandler())
                .setValidator(validator)
                .build();

        mockMvc.perform(post("/api/instrutores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "nome": "",
                                  "email": "email-invalido"
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.fields.nome").exists())
                .andExpect(jsonPath("$.fields.email").exists());
    }
}
