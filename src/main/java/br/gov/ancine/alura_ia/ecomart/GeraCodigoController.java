package br.gov.ancine.alura_ia.ecomart;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("desenvolvedor")
public class GeraCodigoController {

    private final ChatClient chatClient;

    private final static String DEEPSEEK_CODER_MODEL = "deepseek-coder-v2";

    public GeraCodigoController(ChatClient.Builder chatClientBuilder) {
        // é possível setar o modelo default aqui também na hora da chamada como pode
        // ser visto no método generate
        // além disso, também pode ser setado o modelo default no arquivo
        // application.yaml
        this.chatClient = chatClientBuilder.defaultOptions(ChatOptions.builder().model(DEEPSEEK_CODER_MODEL).build())
                .build();
    }

    @GetMapping("/ai/generate")
    public String generate(
            @RequestParam(value = "message", defaultValue = "Gere um clássico exemplo Hello World") String solicitacaoCodigo) {
        String system = "Você é um desenvolvedor fullstack Sênior especialista em Java Sprint Boot e Angular";
        return this.chatClient.prompt().system(system).user(solicitacaoCodigo)
                .options(ChatOptions.builder().model(DEEPSEEK_CODER_MODEL).temperature(0.85).build()).call().content();
    }

}
