package org.example.moviestorylinehelper.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.moviestorylinehelper.model.dto.BaseLLMResponse;
import org.example.moviestorylinehelper.model.dto.ImageLLMResponse;
import org.example.moviestorylinehelper.model.dto.ModelType;
import org.example.moviestorylinehelper.model.dto.TogetherAPIParam;
import org.example.moviestorylinehelper.model.repository.TogetherRepository;

public class TogetherService {
    private TogetherService() {}
    private static final TogetherService instance = new TogetherService();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final TogetherRepository repository = TogetherRepository.getInstance();
    public static TogetherService getInstance() {
        return instance;
    }

    public String useBaseModel(String genre, String idea) throws JsonProcessingException {
        String promptPreProcessing = """
                당신은 창의적인 영화 시나리오 작가입니다. \\n
                사용자가 입력한 %s와 %s를 기반으로 영화 제목과 줄거리를 작성하세요.\\n
                \\n
                조건:\\n
                1. **제목**: 영화 제목을 창의적으로 정하세요.\\n
                2. **줄거리**: 3~5문장으로 흥미롭게 설명하세요.\\n
                3. **장르**: %s (사용자가 선택한 장르)\\n
                4. **아이디어**: %s (사용자가 입력한 아이디어)\\n
                \\n
                예시\\n
                - 장르: SF\\n
                - 아이디어: 외계인이 지구에서 인간과 함께 살며 비밀을 숨기고 있다.\\n
                결과:\\n
                - **제목**: 숨겨진 별\\n
                - **줄거리**: 2145년, 인류는 외계 생명체와 공존하는 시대를 맞이한다. 하지만 ‘베가’라는 이름의 외계인은 자신의 정체를 숨긴 채 평범한 인간처럼 살아가고 있다. 어느 날, 비밀을 알게 된 소녀가 그의 운명을 바꾸는 사건에 휘말리게 되는데…\\n
                \\n
                이제 사용자의 입력을 반영하여 새로운 영화 제목과 줄거리를 한국어로 생성하세요. 한자단어나 중국어는 반드시 제외해주세요.\\n
                """.formatted(genre, idea, genre, idea);
        String responseText = repository.callAPI(new TogetherAPIParam(
                promptPreProcessing,
                ModelType.BASE
        ));
        System.out.println(responseText);
        return objectMapper.readValue(responseText, BaseLLMResponse.class).choices().get(0).message().content();
    }

    public String useReasoning(String prompt) throws JsonProcessingException {
        String promptPreProcessing = """
                당신은 창의적인 영화 시나리오 작가입니다. 사용자가 제공한 기존의 줄거리를 바탕으로, 플롯을 더욱 자세하고 깊이 있는 이야기로 확장하고 발전시켜주세요.
                기존 줄거리:
                  %s 
                조건:
                  캐릭터 개발: 주인공과 주요 인물들의 감정, 내적 갈등, 변화에 대해 더 상세하게 서술해주세요. 그들의 심리적 변화와 결단의 순간을 추가해 주세요.
                  세계관 구축: 이야기가 진행되는 공간인 식물원에 대해 더 자세하게 설명해주세요. 그곳에서 일어나는 사건들이 어떻게 펼쳐지는지 구체적으로 묘사해 주세요.
                  갈등과 클라이맥스: 이야기의 중심 갈등을 강조하고, 클라이맥스를 더 강렬하고 긴장감 있게 그려주세요. 관객이 몰입할 수 있도록 세밀하게 표현해주세요.
                  결말: 결말을 더욱 충격적이고 기억에 남도록 만드세요. 등장인물들의 운명이 어떻게 마무리되는지 설명하고, 그들이 겪은 사건들이 가지는 의미를 설명해주세요.
                  기존 줄거리를 바탕으로 위 조건을 충족하는 자세한 플롯을 작성해주세요. 영화가 감동적이고 몰입감 있는 이야기로 발전하도록 만들어주세요.
                """.formatted(prompt);
        String responseText = repository.callAPI(new TogetherAPIParam(
                promptPreProcessing,
                ModelType.REASONING
        ));
        System.out.println(responseText);
        return objectMapper.readValue(responseText, BaseLLMResponse.class).choices().get(0).message().content();
    }

    public String useImage(String prompt) throws JsonProcessingException {
        String promptPreProcessing = "I need Just plain Image or plain Picture that explain {%s}. .".formatted(prompt);
        String responseText = repository.callAPI(new TogetherAPIParam(
                promptPreProcessing,
                ModelType.IMAGE
        ));
        return objectMapper.readValue(responseText, ImageLLMResponse.class).data().get(0).url();
    }
}
