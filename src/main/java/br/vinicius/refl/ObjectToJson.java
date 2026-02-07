package br.vinicius.refl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ObjectToJson {

    public String transform(Object object){

        String result = null;

        /*
        Precisamos fazer um objectMapper.enable(). Entre os parênteses,
        vamos definir a serialização do objeto. Selecionaremos a opção
        SerializationFeature.INDENT_OUTPUT.
        Com esse INDENT_OUTPUT, indicamos que queremos indentar
        no formato JSON o output do objeto.
        Então, quando formos fazer o output no formato JSON, ele estará
        indentado como se espera de um JSON.
         */
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        Map<String, Object> mapper = new HashMap<>();
        Class<?> classToBeTransformed = object.getClass();

        Arrays.stream(classToBeTransformed.getDeclaredFields()).toList().forEach(
                field -> {
                    field.setAccessible(true);
                    String key = field.getName();
                    Object value = null;
                    try {
                        value = field.get(object);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    mapper.put(key, value);
                }

        );

        try {
            result = objectMapper.writeValueAsString(mapper);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return result;
    }

}
