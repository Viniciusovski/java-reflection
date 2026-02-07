package br.vinicius;

import br.vinicius.refl.ObjectToJson;

public class Main {
    public static void main(String[] args) {

        Pessoa pessoa = new Pessoa(1, "João", "12345");
        ObjectToJson objectToJson = new ObjectToJson();


        System.out.println(objectToJson.transform(pessoa));

    }
}