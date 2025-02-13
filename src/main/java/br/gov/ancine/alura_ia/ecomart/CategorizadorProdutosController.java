package br.gov.ancine.alura_ia.ecomart;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("categorizador")
public class CategorizadorProdutosController {

    private final ChatClient chatClient;

    public CategorizadorProdutosController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/ai/generate")
    public String generate(
            @RequestParam(value = "produto", defaultValue = "VS Code") String produto) {
        String system = "Você é um categorizador de produtos e deve responder apenas o nome da categoria do produto informado";
        return this.chatClient.prompt().system(system).user(produto)
                .options(ChatOptions.builder().temperature(0.85).build()).call().content();
    }

}
