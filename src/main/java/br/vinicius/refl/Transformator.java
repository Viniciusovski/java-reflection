// Generic Types: https://docs.oracle.com/javase/tutorial/java/generics/types.html
package br.vinicius.refl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

// A classe Transformator foi criada para tentar "transformar" um objeto
// em outro tipo de objeto, possivelmente sua versão DTO (Data Transfer Object).
public class Transformator {

    public <I, O> O transform(I input) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Class<?> source = input.getClass();
        Class<?> target = Class.forName(source.getName() + "DTO");

//      O método getDeclaredConstructor() é usado para acessar um construtor da classe,
//      e newInstance() é utilizado para criar a instância, respeitando as práticas de segurança
//      ao não utilizar o construtor padrão potencialmente acessível por outras vias.
        O targetClass = (O) target.getDeclaredConstructor().newInstance();

        // Pega os campos das classes
        Field[] sourceFields = source.getDeclaredFields();
        Field[] targetFields = target.getDeclaredFields();

        Arrays.stream(sourceFields).forEach(sourceField ->
                Arrays.stream(targetFields).forEach(targetField -> {
                    validate(sourceField, targetField);
                    try {
                        // - pega o valor do campo no objeto original "(sourceField.get(input)" e coloca no campo correspondente do DTO.
                        targetField.set(targetClass, sourceField.get(input));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }));

        return targetClass;
    }

    private void validate(Field sourceField, Field targetField) {
        if (sourceField.getName().equals(targetField.getName())
                && sourceField.getType().equals(targetField.getType())){
            // Libera acesso a campos privados
            sourceField.setAccessible(true);
            targetField.setAccessible(true);
        }
    }
}
